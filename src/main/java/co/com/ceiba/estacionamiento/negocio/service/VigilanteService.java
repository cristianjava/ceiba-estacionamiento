package co.com.ceiba.estacionamiento.negocio.service;

import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;

public interface VigilanteService {

	/**
	 * Metodo para ingresar un vehiculo en la tabla VEHICULO PARQUEADO y TIQUETE PAGO
	 * 
	 * @param vehiculoEntity
	 */
	void ingresarVehiculoParqueadero (VehiculoEntity vehiculoEntity);

	/**
	 * Metodo para eliminar el vehiculo de VEHICULO PARQUEADO por la placa y actualiza TIQUETE PAGO por el ultimo
	 * registro para la placa siendo validado por la fecha
	 * 
	 * @param vehiculoEntity
	 * @return 
	 */
	TiqueteEntity salidaVehiculoParqueado (VehiculoEntity vehiculoEntity);
}
