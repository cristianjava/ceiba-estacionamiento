package co.com.ceiba.estacionamiento.negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.negocio.dao.TiqueteDao;
import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.service.TiqueteService;

@Service
public class TiqueteServiceImpl implements TiqueteService {

	@Autowired
	TiqueteDao tiqueteDao;
	
	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.TiqueteManager#guardar(co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity)
	 */
	@Override
	public void guardar(TiqueteEntity tiqueteEntity) {
		tiqueteDao.save(tiqueteEntity);
	}

	@Override
	public Object findById(Long id) {
		return tiqueteDao.findById(id);
	}

}
