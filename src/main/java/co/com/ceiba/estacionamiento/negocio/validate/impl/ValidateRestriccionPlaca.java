package co.com.ceiba.estacionamiento.negocio.validate.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.validate.Validate;

public class ValidateRestriccionPlaca implements Validate {

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.validate.Validate#validar(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public void validar(VehiculoEntity vehiculoEntity) {
		LocalDateTime fechaIngreso = vehiculoEntity.getFechaIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		if (vehiculoEntity.getPlaca().startsWith(Constantes.C_A) && !(fechaIngreso.getDayOfWeek() == DayOfWeek.SUNDAY 
				|| fechaIngreso.getDayOfWeek() == DayOfWeek.MONDAY)) {
			throw new EstacionamientoException(Constantes.RESTRICCION_PARQUEO_PLACA);
		}
	}

}
