package co.com.ceiba.estacionamiento.negocio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "TIQUETE")
public class TiqueteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTiquete;

	@NotNull
	private String placa;

	@NotNull
	private Date fechaIngreso;

	@Null
	private Date fechaSalida;

	@Null
	private double valorPago;

	@Null
	private int diasParqueo;

	@Null
	private int horasParqueo;

	public Long getIdTiquete() {
		return idTiquete;
	}

	public void setIdTiquete(Long idTiquete) {
		this.idTiquete = idTiquete;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public int getDiasParqueo() {
		return diasParqueo;
	}

	public void setDiasParqueo(int diasParqueo) {
		this.diasParqueo = diasParqueo;
	}

	public int getHorasParqueo() {
		return horasParqueo;
	}

	public void setHorasParqueo(int horasParqueo) {
		this.horasParqueo = horasParqueo;
	}

}
