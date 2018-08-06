package co.com.ceiba.estacionamiento.negocio.validate.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.util.Constantes.Comunes;
import co.com.ceiba.estacionamiento.negocio.validate.Validate;

public class ValidateRestriccionPlaca implements Validate {

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.validate.Validate#validar(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public void validar(VehiculoEntity vehiculoEntity) {
		LocalDate fechaHoraActual = LocalDate.now();
		if (vehiculoEntity.getPlaca().startsWith(Comunes.A) && (fechaHoraActual.getDayOfWeek() == DayOfWeek.SUNDAY 
				&& fechaHoraActual.getDayOfWeek() == DayOfWeek.MONDAY)) {
			throw new EstacionamientoException(Constantes.RESTRICCION_PARQUEO_PLACA);
		}
	}

}
