package co.com.ceiba.estacionamiento.negocio.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.negocio.dao.VehiculoDao;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;

@Repository
public class VehiculoManagerImpl implements VehiculoManager {

	@Autowired
	private VehiculoDao vehiculoDAO;

	@Transactional
	@Override
	public void guardar(VehiculoEntity vehiculo) {
		vehiculoDAO.save(vehiculo);
		
	}

	@Transactional
	@Override
	public List<VehiculoEntity> findAll() {
		return vehiculoDAO.findAll();
	}

	@Transactional
	@Override
	public Integer findVehiculoByTipoVehiculoActivo(Long id) {
		return vehiculoDAO.findVehiculoByTipoVehiculoActivo(id);
		
	}
	
}
