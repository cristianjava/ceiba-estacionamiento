package co.com.ceiba.estacionamiento.negocio.validate.impl;

import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.service.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.validate.Validate;

public class ValidateParqueoDisponible implements Validate {

	VehiculoService vehiculoService;

	public ValidateParqueoDisponible(VehiculoService vehiculoService) {
		super();
		this.vehiculoService = vehiculoService;
	}

	@Transactional
	@Override
	public void validar(VehiculoEntity vehiculoEntity) {
		if (vehiculoEntity.getTipoVehiculo().getId() == Constantes.TV_CARRO) {
			int catidadCarrosParqueados = vehiculoService.findVehiculosParqueadosTipo(Constantes.TV_CARRO);
			if (catidadCarrosParqueados >= Constantes.CMV_CANTIDAD_MAXIMA_CARROS) {
				throw new EstacionamientoException(Constantes.PARQUEADERO_SIN_CUPO);
			}
		} else if (vehiculoEntity.getTipoVehiculo().getId() == Constantes.TV_MOTO) {
			int catidadCarrosParqueados = vehiculoService.findVehiculosParqueadosTipo(Constantes.TV_MOTO);
			if (catidadCarrosParqueados >= Constantes.CMV_CANTIDAD_MAXIMA_MOTOS) {
				throw new EstacionamientoException(Constantes.PARQUEADERO_SIN_CUPO);
			}
		}
	}
	
}
