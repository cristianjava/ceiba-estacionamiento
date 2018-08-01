package co.com.ceiba.estacionamiento.negocio.model;

import java.io.Serializable;

public class TipoVehiculo implements Serializable {

	private static final long serialVersionUID = -7580329721550007159L;
	private Long id;
	private String descripcion;
	
	public TipoVehiculo(String descripcion, Long id) {
		super();
		this.descripcion = descripcion;
		this.id = id;
	}
	
	public TipoVehiculo() {
		super();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
