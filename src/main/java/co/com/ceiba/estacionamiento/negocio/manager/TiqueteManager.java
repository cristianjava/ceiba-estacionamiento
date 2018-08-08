package co.com.ceiba.estacionamiento.negocio.manager;

import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;

public interface TiqueteManager {

	/**
	 * Metodo para guardar en TIQUETE PARQUEO
	 * 
	 * @param tiqueteEntity
	 */
	void guardar (TiqueteEntity tiqueteEntity);
	
	/**
	 * Metodo para encontrar un tiquete por el id
	 * 
	 * @param long1
	 * @return
	 */
	Object findById(Long long1);
}
