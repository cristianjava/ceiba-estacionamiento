package co.com.ceiba.estacionamiento.negocio.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TARIFA")
public class TarifaEntity implements Serializable{

	private static final long serialVersionUID = -7911547674487456475L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	private String descripcion;

	@NotNull
	private String valor;

	public Integer getIdTarifa() {
		return id;
	}

	public void setIdTarifa(Integer idTarifa) {
		this.id = idTarifa;
	}

	public String getDescripcionTarifa() {
		return descripcion;
	}

	public void setDescripcionTarifa(String descripcionTarifa) {
		this.descripcion = descripcionTarifa;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
