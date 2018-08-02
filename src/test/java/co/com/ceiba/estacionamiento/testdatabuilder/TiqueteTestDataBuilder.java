package co.com.ceiba.estacionamiento.testdatabuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.negocio.model.Tiquete;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;

public class TiqueteTestDataBuilder {

	ParqueaderoUtil parqueaderoUtil = new ParqueaderoUtil();
	
	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "CVA531";
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final double VALOR_PAGO = 3000;
	private static final int DIAS_ESTADIA = 0;
	private static final int HORAS_ESTADIA = 3;

	private String placa;
	private Date fechaIngreso;
	private Date fechaSalida;
	private double valorPago;
	private int diasEstadia;
	private int horasEstadia;

	public TiqueteTestDataBuilder() throws ParseException {
		this.placa = PLACA;
		this.fechaIngreso = (parqueaderoUtil.convertStringToDate(FECHA_INGRESO));
		this.fechaSalida = FECHA_SALIDA != null ? (parqueaderoUtil.convertStringToDate(FECHA_SALIDA)) : null;
		this.valorPago = VALOR_PAGO;
		this.diasEstadia = DIAS_ESTADIA;
		this.horasEstadia = HORAS_ESTADIA;
	}

	public TiqueteTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public TiqueteTestDataBuilder conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public TiqueteTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public TiqueteTestDataBuilder conValorPago(Double valorPago) {
		this.valorPago = valorPago;
		return this;
	}

	public TiqueteTestDataBuilder conDiasEstadia(int diasEstadia) {
		this.diasEstadia = diasEstadia;
		return this;
	}

	public TiqueteTestDataBuilder conHorasEstadia(int horasEstadia) {
		this.horasEstadia = horasEstadia;
		return this;
	}

	public Tiquete build() {
		return new Tiquete(this.placa, this.fechaIngreso, this.fechaSalida, this.valorPago, this.diasEstadia, this.horasEstadia);
	}
}
