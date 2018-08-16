package co.com.ceiba.estacionamiento.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.negocio.dao.VehiculoDao;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.service.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoDao vehiculoDAO;

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager#guardar(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Transactional
	@Override
	public void guardar(VehiculoEntity vehiculo) {
		VehiculoEntity vehiculoParqueado = findByPlaca(vehiculo.getPlaca());
		if (vehiculoParqueado != null) {
			throw new EstacionamientoException(Constantes.EL_VEHICULO_ESTA_PARQUEADO);
		}
		// Se valida que si la placa comienza por A y es diferente de Domingo o Lunes tira la excepcion
		
		vehiculoDAO.save(vehiculo);
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager#eliminar(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public void eliminar(VehiculoEntity vehiculoEntity) {
		VehiculoEntity vehiculoParqueado = findByPlaca(vehiculoEntity.getPlaca());
		if (vehiculoParqueado == null) {
			throw new EstacionamientoException(Constantes.EL_VEHICULO_NO_ESTA_PARQUEADO);
		}
		vehiculoDAO.delete(vehiculoEntity);
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager#findByPlaca(java.lang.String)
	 */
	@Override
	public VehiculoEntity findByPlaca(String placa) {
		VehiculoEntity vehiculoEntity = null;
		vehiculoEntity = vehiculoDAO.findVehiculoByPlaca(placa);
		return vehiculoEntity;
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager#findVehiculosParqueadosTipo(int)
	 */
	@Override
	public int findVehiculosParqueadosTipo(int tipoVehiculo) {
		return vehiculoDAO.findVehiculosParqueadosTipo(tipoVehiculo);
	}

	@Override
	public List<VehiculoEntity> findAll() {
		return vehiculoDAO.findAll();
	}

}
