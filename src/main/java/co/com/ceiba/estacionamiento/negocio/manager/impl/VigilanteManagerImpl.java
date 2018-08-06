package co.com.ceiba.estacionamiento.negocio.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager;
import co.com.ceiba.estacionamiento.negocio.validate.Validate;
import co.com.ceiba.estacionamiento.negocio.validate.impl.ValidateParqueoDisponible;
import co.com.ceiba.estacionamiento.negocio.validate.impl.ValidateRestriccionPlaca;

@Repository
public class VigilanteManagerImpl implements VigilanteManager {

	@Autowired
	VehiculoManager vehiculoManager;

	private static final Logger LOGGER = Logger.getLogger(VigilanteManagerImpl.class.getName());
	
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
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager#salidaVehiculoParqueado(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public void salidaVehiculoParqueado(VehiculoEntity vehiculoEntity) {
		// Se elimina de VEHICULO PARQUEADO y actualiza TIQUETE PAGO para ultima la placa ordenada por fecha desc
		
		// Se consultan las tarifas
		
		
		// Se hace el calculo de cuanto se demoro en el parqueadero con las tarifas 
		
		
		// Si el tiempo de estadia es menor a 9 horas se cobra por horas
		
		
		// Si el tiempo es mayor o igual a 9 horas se hace el caculo para cobrar por dias y horas

		
		// Si el vehiculo es moto y los cc son mayor a 500 se suma la tarifa 5 que pertenece a el sobrecargo cuando el cc es mayor
		
	}

	
}
