package co.com.ceiba.estacionamiento.negocio.validate.impl;

import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.validate.Validate;
import co.com.ceiba.estacionamiento.negocio.util.Constantes.CantidadMaximaVehiculos;
import co.com.ceiba.estacionamiento.negocio.util.Constantes.TipoVehiculo;

public class ValidateParqueoDisponible implements Validate {

	VehiculoManager vehiculoManager;

	public ValidateParqueoDisponible(VehiculoManager vehiculoManager) {
		super();
		this.vehiculoManager = vehiculoManager;
	}

	@Transactional
	@Override
	public void validar(VehiculoEntity vehiculoEntity) {
		if (vehiculoEntity.getTipoVehiculo().getId() == TipoVehiculo.CARRO) {
			int catidadCarrosParqueados = vehiculoManager.findVehiculosParqueadosTipo(TipoVehiculo.CARRO);
			if (catidadCarrosParqueados >= CantidadMaximaVehiculos.CANTIDAD_MAXIMA_CARROS) {
				throw new EstacionamientoException(Constantes.PARQUEADERO_SIN_CUPO);
			}
		} else if (vehiculoEntity.getTipoVehiculo().getId() == TipoVehiculo.MOTO) {
			int catidadCarrosParqueados = vehiculoManager.findVehiculosParqueadosTipo(TipoVehiculo.MOTO);
			if (catidadCarrosParqueados >= CantidadMaximaVehiculos.CANTIDAD_MAXIMA_MOTOS) {
				throw new EstacionamientoException(Constantes.PARQUEADERO_SIN_CUPO);
			}
		}
	}
	
}
