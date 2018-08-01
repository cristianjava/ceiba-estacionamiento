package co.com.ceiba.estacionamiento.negocio.model;

import java.io.Serializable;

public class Vehiculo implements Serializable {
	
	private static final long serialVersionUID = -6258675662772627477L;
	
	private String placa;
	private int cilindraje;
	private TipoVehiculo tipoVehiculo;
	
	public Vehiculo(String placa, int cilindraje, TipoVehiculo tipoVehiculo) {
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
	}

	public Vehiculo() {
		super();
	}

	public String getPlaca() {
		return placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}
	
}
