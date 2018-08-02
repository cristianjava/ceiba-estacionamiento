package co.com.ceiba.estacionamiento.negocio.manager;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;

public interface VigilanteManager {

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
	 */
	void salidaVehiculoParqueado (VehiculoEntity vehiculoEntity);
}
