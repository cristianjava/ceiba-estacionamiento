package co.com.ceiba.estacionamiento.negocio.model;

import java.io.Serializable;
import java.util.Date;

public class Vehiculo implements Serializable {
	
	private static final long serialVersionUID = -6258675662772627477L;
	
	private String placa;
	private int cilindraje;
	private Date fechaIngreso;
	private Date fechaSalida;
	private TipoVehiculo tipoVehiculo;
	
	public Vehiculo(String placa, int cilindraje, TipoVehiculo tipoVehiculo, Date fechaIngreso, Date fechaSalida) {
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
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

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
}
