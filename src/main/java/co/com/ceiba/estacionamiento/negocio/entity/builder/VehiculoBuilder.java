package co.com.ceiba.estacionamiento.negocio.entity.builder;

import co.com.ceiba.estacionamiento.negocio.entity.TipoVehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;

public final class VehiculoBuilder {

	private VehiculoBuilder() {
		
	}
	
	public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		TipoVehiculoEntity tipoVehiculoEntity = TipoVehiculoBuilder.convertirAEntity(vehiculo.getTipoVehiculo());
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setFechaIngreso(vehiculo.getFechaIngreso());
		vehiculoEntity.setFechaSalida(vehiculo.getFechaSalida());
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		vehiculoEntity.setCilindraje(vehiculo.getCilindraje());
		vehiculoEntity.setTipoVehiculo(tipoVehiculoEntity);
		return vehiculoEntity;
	}

}
