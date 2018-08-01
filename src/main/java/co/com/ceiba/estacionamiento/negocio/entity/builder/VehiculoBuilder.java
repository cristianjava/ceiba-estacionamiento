package co.com.ceiba.estacionamiento.negocio.entity.builder;

import co.com.ceiba.estacionamiento.negocio.entity.TipoVehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;

public final class VehiculoBuilder {

	private VehiculoBuilder() {
		
	}
	
	public static Vehiculo convertirAObject(VehiculoEntity vehiculoEntity) {
		TipoVehiculo tipoVehiculo = TipoVehiculoBuilder.convertirAObject(vehiculoEntity.getTipoVehiculo());
		Vehiculo vehiculo = null;
		vehiculo = new Vehiculo(vehiculoEntity.getPlaca(), vehiculoEntity.getCilindraje(), tipoVehiculo);
		return vehiculo;
	}
	
	public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		TipoVehiculoEntity tipoVehiculoEntity = TipoVehiculoBuilder.convertirAEntity(vehiculo.getTipoVehiculo());
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		vehiculoEntity.setCilindraje(vehiculo.getCilindraje());
		vehiculoEntity.setTipoVehiculo(tipoVehiculoEntity);
		return vehiculoEntity;
	}

}
