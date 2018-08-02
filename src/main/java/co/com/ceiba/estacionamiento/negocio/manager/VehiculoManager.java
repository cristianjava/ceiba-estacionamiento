package co.com.ceiba.estacionamiento.negocio.manager;

import java.util.List;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;

public interface VehiculoManager {

	/**
	 * Metodo para guardar en la tabla VEHICULO PARQUEADO
	 * 
	 * @param vehiculo
	 */
	void guardar(VehiculoEntity vehiculoEntity);

	/**
	 * Se elimina un registro de la tabla VEHICULO PARQUEADO
	 * 
	 * @param vehiculoEntity
	 */
	void eliminar(VehiculoEntity vehiculoEntity);
	
	/**
	 * Metodo para buscar el vehiculo de la tabla VEHICULO PARQUEADO por la placa
	 * 
	 * @param placa
	 * @return
	 */
	VehiculoEntity findByPlaca(String placa);
	
	/**
	 * Consultar todos los registros de la tabla VEHICULO PARQUEADO
	 * 
	 * @return
	 */
	List<VehiculoEntity> findAll();

	
}
