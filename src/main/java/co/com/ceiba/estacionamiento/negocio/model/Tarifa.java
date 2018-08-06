package co.com.ceiba.estacionamiento.negocio.model;

import java.io.Serializable;

public class Tarifa implements Serializable {

	private static final long serialVersionUID = 419739022334932254L;

	private Integer idTarifa;
	private String descripcionTarifa;
	private String valor;
	
	public Integer getIdTarifa() {
		return idTarifa;
	}
	public void setIdTarifa(Integer idTarifa) {
		this.idTarifa = idTarifa;
	}
	public String getDescripcionTarifa() {
		return descripcionTarifa;
	}
	public void setDescripcionTarifa(String descripcionTarifa) {
		this.descripcionTarifa = descripcionTarifa;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
