package co.com.ceiba.estacionamiento.negocio.service;

import java.util.List;

import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;

public interface TarifaService {

	/**
	 * Metodo para buscar las tarifas
	 * 
	 * @return
	 */
	List<TarifaEntity> findAll();

}
