package co.com.ceiba.estacionamiento.negocio.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.negocio.dao.TiqueteDao;
import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.manager.TiqueteManager;

public class TiqueteManagerImpl implements TiqueteManager {

	@Autowired
	TiqueteDao tiqueteDao;
	
	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.TiqueteManager#guardar(co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity)
	 */
	@Transactional
	@Override
	public void guardar(TiqueteEntity tiqueteEntity) {
		tiqueteDao.save(tiqueteEntity);
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.TiqueteManager#actualizar(co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity)
	 */
	@Transactional
	@Override
	public void actualizar(TiqueteEntity tiqueteEntity) {
		tiqueteDao.save(tiqueteEntity);
	}

}