package co.com.ceiba.estacionamiento.negocio.manager.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.negocio.dao.TarifaDao;
import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;
import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.manager.TiqueteManager;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.validate.Validate;
import co.com.ceiba.estacionamiento.negocio.validate.impl.ValidateParqueoDisponible;
import co.com.ceiba.estacionamiento.negocio.validate.impl.ValidateRestriccionPlaca;

@Repository
public class VigilanteManagerImpl implements VigilanteManager {

	private static final Logger LOGGER = Logger.getLogger(VigilanteManagerImpl.class.getName());
	
	@Autowired
	VehiculoManager vehiculoManager;
	
	@Autowired
	TarifaDao tarifaDao;
	
	@Autowired
	TiqueteManager tiqueteManager;
	
	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager#ingresarVehiculoParqueadero(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public void ingresarVehiculoParqueadero(VehiculoEntity vehiculoEntity) {
		try {
			List<Validate> validaciones = new ArrayList<>();
			
			// se valida que el parqueadero no tenga los 20 carros y 10 motos
			validaciones.add(new ValidateParqueoDisponible(vehiculoManager));
			
			// se valida que si la placa empieza por A y es Domingo o Lunes permite el parqueo
			validaciones.add(new ValidateRestriccionPlaca());
			
			// Ejecutamos las validaciones
			for (Validate validate : validaciones) {
				validate.validar(vehiculoEntity);
			}
			
			// Guardamos en VEHICULO PARQUEADO
			vehiculoManager.guardar(vehiculoEntity);
		} catch (EstacionamientoException e) {
			LOGGER.info(e.getMessage());
			throw new EstacionamientoException(e.getMessage());
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager#salidaVehiculoParqueado(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public TiqueteEntity salidaVehiculoParqueado(VehiculoEntity vehiculoEntity) {
		// Se elimina de VEHICULO PARQUEADO y se ingresa en TIQUETE PAGO
		TiqueteEntity tiqueteEntity = null;
		// Se busca el vehiculo parqueado y se calcula el tiempo en dias y horas
		VehiculoEntity vehiculoParqueado = vehiculoManager.findByPlaca(vehiculoEntity.getPlaca());
		vehiculoManager.eliminar(vehiculoParqueado);
		vehiculoParqueado.setFechaSalida(vehiculoEntity.getFechaSalida());
		LocalDateTime fechaIngreso = vehiculoParqueado.getFechaIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime fechaSalida = vehiculoParqueado.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		long horasParqueo = Duration.between(fechaIngreso, fechaSalida).toHours();
		long minutosParqueo = Duration.between(fechaIngreso, fechaSalida).toMinutes();

		long minutosRestantesHora = minutosParqueo%60;
		if (minutosRestantesHora > Constantes.C_TIEMPO_ADICIONAL_HORA_PARQUEO) {
			horasParqueo++;
		}
		
		Long diasParqueados = horasParqueo/24;
		Long horasParqueadas = horasParqueo%24;
		
		if (horasParqueadas >= 9 && horasParqueadas <= 24) {
			diasParqueados++;
			horasParqueadas = 0L;
		}

		tiqueteEntity = new TiqueteEntity();
		tiqueteEntity.setPlaca(vehiculoEntity.getPlaca());
		tiqueteEntity.setFechaIngreso(vehiculoEntity.getFechaIngreso());
		tiqueteEntity.setFechaSalida(vehiculoEntity.getFechaSalida());
		tiqueteEntity.setDiasParqueo(Integer.parseInt(diasParqueados.toString()));
		tiqueteEntity.setHorasParqueo(Integer.parseInt(horasParqueadas.toString()));
		this.calcularValor(vehiculoEntity,tiqueteEntity);
		// Guardamos en TIQUETE y borramos VEHICULO_PARQUEADO
		tiqueteManager.guardar(tiqueteEntity);
		return tiqueteEntity;
	}

	/**
	 *  Metodo para calcular el valor a pagar por el parqueo
	 *  
	 * @param tiqueteEntity 
	 * @param vehiculoEntity 
	 */
	private void calcularValor(VehiculoEntity vehiculoEntity, TiqueteEntity tiqueteEntity) {

		// Se consultan las tarifas
		List<TarifaEntity> listaTarifas = tarifaDao.findAll();
		
		// dependiendo del tipo vehiculo se calcula el total a pagar con las tarifas
		if (vehiculoEntity.getTipoVehiculo().getId() == Constantes.TV_CARRO) {
			if (tiqueteEntity.getDiasParqueo() != 0) {
				double valorDiasCarro = Double.parseDouble(listaTarifas.get(Constantes.TT_DIA_CARRO).getValor()) * tiqueteEntity.getDiasParqueo();
				tiqueteEntity.setValorPago(tiqueteEntity.getValorPago() + valorDiasCarro);
			}
			if (tiqueteEntity.getHorasParqueo() != 0) {
				double valorHoras = Double.parseDouble(listaTarifas.get(Constantes.TT_HORA_CARRO).getValor()) * tiqueteEntity.getHorasParqueo();
				tiqueteEntity.setValorPago(tiqueteEntity.getValorPago() + valorHoras);
			}
		} else if (vehiculoEntity.getTipoVehiculo().getId() == Constantes.TV_MOTO) {
			if (tiqueteEntity.getDiasParqueo() != 0) {
				double valorDiasMoto = Double.parseDouble(listaTarifas.get(Constantes.TT_DIA_MOTO).getValor()) * tiqueteEntity.getDiasParqueo();
				tiqueteEntity.setValorPago(tiqueteEntity.getValorPago() + valorDiasMoto);
			}
			if (tiqueteEntity.getHorasParqueo() != 0) {
				double valorHorasMoto = Double.parseDouble(listaTarifas.get(Constantes.TT_HORA_MOTO).getValor()) * tiqueteEntity.getHorasParqueo();
				tiqueteEntity.setValorPago(tiqueteEntity.getValorPago() + valorHorasMoto);
			}
			// Si los cc son mayor a 500 se suma la tarifa 5 que pertenece a el sobrecargo cuando el cc es mayor
			if (vehiculoEntity.getCilindraje() >= 500) {
				double nuevoValorPorCC = Double.parseDouble(listaTarifas.get(Constantes.TT_CC_MAYOR_500).getValor());
				tiqueteEntity.setValorPago(tiqueteEntity.getValorPago() + nuevoValorPorCC);
			}
		}
	}

	
}
