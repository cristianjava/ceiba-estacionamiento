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

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager#guardar(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Transactional
	@Override
	public void guardar(VehiculoEntity vehiculo) {
		vehiculoDAO.save(vehiculo);
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager#findAll()
	 */
	@Transactional
	@Override
	public List<VehiculoEntity> findAll() {
		return vehiculoDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager#eliminar(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Transactional
	@Override
	public void eliminar(VehiculoEntity vehiculoEntity) {
		vehiculoDAO.delete(vehiculoEntity);
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager#findByPlaca(java.lang.String)
	 */
	@Transactional
	@Override
	public VehiculoEntity findByPlaca(String placa) {
		VehiculoEntity vehiculoEntity = null;
		vehiculoEntity = vehiculoDAO.findVehiculoByPlaca(placa);
		return vehiculoEntity;
	}

}
