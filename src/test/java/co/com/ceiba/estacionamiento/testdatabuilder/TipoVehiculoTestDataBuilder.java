package co.com.ceiba.estacionamiento.testdatabuilder;

import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;

public class TipoVehiculoTestDataBuilder {

	private static final Long ID = 1L;
	private static final String DESCRIPCION = "Carro";
	
	private Long id;
	private String descripcion;
	
	public TipoVehiculoTestDataBuilder() {
		this.id = ID;
		this.descripcion = DESCRIPCION;
	}

	public TipoVehiculoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}

	public TipoVehiculoTestDataBuilder conDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public TipoVehiculo build() {
		return new TipoVehiculo(this.descripcion, this.id);
	}
}
