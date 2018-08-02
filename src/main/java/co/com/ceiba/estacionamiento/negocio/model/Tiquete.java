package co.com.ceiba.estacionamiento.negocio.model;

import java.io.Serializable;
import java.util.Date;

public class Tiquete implements Serializable {

	private static final long serialVersionUID = 7052980058096870226L;

	private String placaPojo;
	private Date fechaIngresoPojo;
	private Date fechaSalidaPojo;
	private double valorPagoPojo;
	private int diasEstadia;
	private int horasEstadia;

	public Tiquete() {
		super();
	}

	public Tiquete(String placa, Date fechaIngreso, Date fechaSalida, double valorPago, int diasEstadia, int horasEstadia) {
		super();
		this.placaPojo = placa;
		this.fechaIngresoPojo = fechaIngreso;
		this.fechaSalidaPojo = fechaSalida;
		this.valorPagoPojo = valorPago;
		this.diasEstadia = diasEstadia;
		this.horasEstadia = horasEstadia;
	}

	public String getPlaca() {
		return placaPojo;
	}

	public void setPlaca(String placa) {
		this.placaPojo = placa;
	}

	public Date getFechaIngreso() {
		return fechaIngresoPojo;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngresoPojo = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalidaPojo;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalidaPojo = fechaSalida;
	}

	public double getValorPago() {
		return valorPagoPojo;
	}

	public void setValorPago(double valorPago) {
		this.valorPagoPojo = valorPago;
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
