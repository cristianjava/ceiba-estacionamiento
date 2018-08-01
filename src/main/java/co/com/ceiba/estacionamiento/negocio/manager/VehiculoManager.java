package co.com.ceiba.estacionamiento.negocio.manager;

import java.util.List;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;

public interface VehiculoManager {


	/**
	 * Guardar vehiculo
	 */
	void guardar(VehiculoEntity vehiculo);

	/**
	 * Consultar todos los vehiculos
	 */
	List<VehiculoEntity> findAll();

	Integer findVehiculoByTipoVehiculoActivo(Long id);
	
}
