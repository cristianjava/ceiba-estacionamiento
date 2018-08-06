package co.com.ceiba.estacionamiento.negocio.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.negocio.dao.TarifaDao;
import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;
import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.util.Constantes.Comunes;
import co.com.ceiba.estacionamiento.negocio.validate.Validate;
import co.com.ceiba.estacionamiento.negocio.validate.impl.ValidateParqueoDisponible;
import co.com.ceiba.estacionamiento.negocio.validate.impl.ValidateRestriccionPlaca;

@Repository
public class VigilanteManagerImpl implements VigilanteManager {

	@Autowired
	VehiculoManager vehiculoManager;
	
	@Autowired
	TarifaDao tarifaDao;
	
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
			throw new EstacionamientoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager#salidaVehiculoParqueado(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public TiqueteEntity salidaVehiculoParqueado(VehiculoEntity vehiculoEntity) {
		// Se elimina de VEHICULO PARQUEADO y se ingresa en TIQUETE PAGO
		
		try {
			// Se busca el vehiculo parqueado y se calcula el tiempo en dias y horas
			VehiculoEntity vehiculoParqueado = vehiculoManager.findByPlaca(vehiculoEntity.getPlaca());
			vehiculoParqueado.setFechaSalida(vehiculoEntity.getFechaSalida());
			LocalDateTime fechaIngreso = vehiculoParqueado.getFechaIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			LocalDateTime fechaSalida = vehiculoParqueado.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			
			long horasParqueo = Duration.between(fechaIngreso, fechaSalida).toHours();
			long minutosParqueo = Duration.between(fechaIngreso, fechaSalida).toMinutes();

			long minutosRestantesHora = minutosParqueo%60;
			if (minutosRestantesHora > Comunes.TIEMPO_ADICIONAL_HORA_PARQUEO) {
				horasParqueo++;
			}
			
			Long diasParqueado = horasParqueo/24;
			Long horasParqueado = horasParqueo%24;
			
			if (horasParqueado >= 9 && horasParqueado <= 24) {
				diasParqueado++;
				horasParqueado = 0L;
			}

			// Se consultan las tarifas
			List<TarifaEntity> listaTarifas = tarifaDao.findAll();
			
			TiqueteEntity tiqueteEntity = new TiqueteEntity();
			tiqueteEntity.setPlaca(vehiculoEntity.getPlaca());
			tiqueteEntity.setFechaIngreso(vehiculoEntity.getFechaIngreso());
			tiqueteEntity.setFechaSalida(vehiculoEntity.getFechaSalida());
			tiqueteEntity.setDiasParqueo(Integer.parseInt(diasParqueado.toString()));
			tiqueteEntity.setHorasParqueo(Integer.parseInt(horasParqueado.toString()));
			
			// dependiendo del tipo vehiculo se calcula el total a pagar con las tarifas
			if (vehiculoEntity.getTipoVehiculo().getId() == Constantes.TipoVehiculo.CARRO) {
				
			} else if (vehiculoEntity.getTipoVehiculo().getId() == Constantes.TipoVehiculo.MOTO) {
				// Si los cc son mayor a 500 se suma la tarifa 5 que pertenece a el sobrecargo cuando el cc es mayor
				
			}
		} catch (EstacionamientoException e) {
			throw new EstacionamientoException(e.getMessage());
		}

		return null;
	}

	
}
