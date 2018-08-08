package co.com.ceiba.estacionamiento.negocio.model;

import java.io.Serializable;

public class ResponseService implements Serializable {

	private static final long serialVersionUID = 2133039228894109984L;

	private String codigo;
	private String mensaje;
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
