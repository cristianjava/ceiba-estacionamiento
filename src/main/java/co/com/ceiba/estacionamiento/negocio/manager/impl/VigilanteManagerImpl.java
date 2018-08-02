package co.com.ceiba.estacionamiento.negocio.manager.impl;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager;

public class VigilanteManagerImpl implements VigilanteManager {

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager#ingresarVehiculoParqueadero(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public void ingresarVehiculoParqueadero(VehiculoEntity vehiculoEntity) {
		//Se hacen las validaciones se ingresa en VEHICULO PARQUEADO y TIQUETE PAGO
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager#salidaVehiculoParqueado(co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity)
	 */
	@Override
	public void salidaVehiculoParqueado(VehiculoEntity vehiculoEntity) {
		// Se elimina de VEHICULO PARQUEADO y actualiza TIQUETE PAGO para ultima la placa ordenada por fecha desc
		
	}

	
}
