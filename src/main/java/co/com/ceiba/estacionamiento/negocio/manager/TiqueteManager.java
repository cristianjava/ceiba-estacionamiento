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
	 * Metodo para actualizar TIQUETE PARQUEO despues que el vehiculo sale del parqueadero
	 * 
	 * @param tiqueteEntity
	 */
	void actualizar (TiqueteEntity tiqueteEntity);
}
