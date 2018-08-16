package co.com.ceiba.estacionamiento.negocio.service;

import java.util.List;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;

public interface VehiculoService {

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
	 * Metodo para consultar el numero de vehiculos parqueados por tipo
	 * 
	 * @param carro
	 * @return
	 */
	int findVehiculosParqueadosTipo(int tipoVehiculo);

	List<VehiculoEntity> findAll();
	
}
