package co.com.ceiba.estacionamiento.negocio.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.negocio.dao.TarifaDao;
import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;
import co.com.ceiba.estacionamiento.negocio.manager.TarifaManager;

public class TarifaManagerImpl implements TarifaManager{

	@Autowired
	TarifaDao tarifaDao;
	
	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.TarifaManager#findByPK(java.lang.Integer)
	 */
	@Override
	public List<TarifaEntity> findAll() {
		return tarifaDao.findAll();
	}
}
