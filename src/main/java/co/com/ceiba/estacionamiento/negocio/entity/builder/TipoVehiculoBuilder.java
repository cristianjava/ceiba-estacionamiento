package co.com.ceiba.estacionamiento.negocio.entity.builder;

import java.io.Serializable;

import co.com.ceiba.estacionamiento.negocio.entity.TipoVehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;

public final class TipoVehiculoBuilder implements Serializable{

	private static final long serialVersionUID = -4851793111134696040L;

	private TipoVehiculoBuilder() {}
	
	public static TipoVehiculoEntity convertirAEntity(TipoVehiculo tipoVehiculo) {
		TipoVehiculoEntity tipoVehiculoEntity = new TipoVehiculoEntity();
		tipoVehiculoEntity.setId(tipoVehiculo != null && tipoVehiculo.getId() != null ? tipoVehiculo.getId() : null);
		tipoVehiculoEntity.setDescripcion(tipoVehiculo != null && tipoVehiculo.getDescripcion() != null ? tipoVehiculo.getDescripcion() : null);
		return tipoVehiculoEntity;
	}
}
