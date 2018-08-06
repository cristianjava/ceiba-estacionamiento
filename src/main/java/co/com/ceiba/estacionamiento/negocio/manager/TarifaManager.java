package co.com.ceiba.estacionamiento.negocio.manager;

import java.util.List;

import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;

public interface TarifaManager {

	/**
	 * Metodo para buscar las tarifas
	 * 
	 * @return
	 */
	List<TarifaEntity> findAll();

}
