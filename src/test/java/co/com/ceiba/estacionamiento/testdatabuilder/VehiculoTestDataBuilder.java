package co.com.ceiba.estacionamiento.testdatabuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;

public class VehiculoTestDataBuilder {

	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "CVA532";
	private static final int CILINDRAJE = 1000;
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final TipoVehiculo TIPO_VEHICULO = new TipoVehiculo("Carro", 1L);
	
	private String placa;
	private int cilindraje;
	private Date fechaIngreso;
	private Date fechaSalida;
	private TipoVehiculo tipoVehiculo;

	public VehiculoTestDataBuilder() {
		this.placa = PLACA;
		this.cilindraje = CILINDRAJE;
		this.fechaIngreso = (ParqueaderoUtil.convertStringToDate(FECHA_INGRESO));
		this.fechaSalida = FECHA_SALIDA != null ? (ParqueaderoUtil.convertStringToDate(FECHA_SALIDA)) : null;
		this.tipoVehiculo = TIPO_VEHICULO;
	}

	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoTestDataBuilder conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public VehiculoTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public VehiculoTestDataBuilder conTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public Vehiculo build() {
		return new Vehiculo(this.placa, this.cilindraje, this.tipoVehiculo, this.fechaIngreso, this.fechaSalida);
	}
}
