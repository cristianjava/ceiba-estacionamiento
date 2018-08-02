package co.com.ceiba.estacionamiento.negocio.model;

import java.io.Serializable;
import java.util.Date;

public class Tiquete implements Serializable {

	private static final long serialVersionUID = 7052980058096870226L;

	private String placa;
	private Date fechaIngreso;
	private Date fechaSalida;
	private double valorPago;
	private int diasEstadia;
	private int horasEstadia;

	public Tiquete() {
		super();
	}

	public Tiquete(String placa, Date fechaIngreso, Date fechaSalida, double valorPago, int diasEstadia, int horasEstadia) {
		super();
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorPago = valorPago;
		this.diasEstadia = diasEstadia;
		this.horasEstadia = horasEstadia;
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

	public int getDiasEstadia() {
		return diasEstadia;
	}

	public void setDiasEstadia(int diasEstadia) {
		this.diasEstadia = diasEstadia;
	}

	public int getHorasEstadia() {
		return horasEstadia;
	}

	public void setHorasEstadia(int horasEstadia) {
		this.horasEstadia = horasEstadia;
	}

}
