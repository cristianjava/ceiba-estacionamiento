package co.com.ceiba.estacionamiento.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;
//import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoTest {

	ParqueaderoUtil parqueaderoUtil= new ParqueaderoUtil();
	
	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "CVA531";
	private static final int CILINDRAJE = 1200;
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final TipoVehiculo TIPO_VEHICULO = new TipoVehiculo("Carro", 1L);

	@Test
	public void crearTiqueteTest() throws ParseException {
		
		// arrange
//		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
//				conPlaca(PLACA).
//				conCilindraje(CILINDRAJE).
//				conFechaIngreso(parqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
//				conFechaSalida(FECHA_SALIDA != null ? parqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).
//				conTipoVehiculo(TIPO_VEHICULO);
//
//		// act
//		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
//
//		// assert
//		assertEquals(PLACA, vehiculo.getPlaca());
//		assertEquals(CILINDRAJE, vehiculo.getCilindraje());
//		assertEquals(parqueaderoUtil.convertStringToDate(FECHA_INGRESO), vehiculo.getFechaIngreso());
//		assertEquals(parqueaderoUtil.convertStringToDate(FECHA_SALIDA), vehiculo.getFechaSalida());
//		assertEquals(TIPO_VEHICULO, vehiculo.getTipoVehiculo());
	}

	@Test
	public void guardarVehiculoParqueadero() {
		
		// arrange
		
		// act
		
		// assert
		assertTrue(true);
	}

	@Test
	public void buscarVehiculosParqueadero() {
		
		// arrange
		
		// act
		
		// assert
		assertTrue(true);
	}

	@Test
	public void BuscarUnVehiculoParqueadero() {
		
		// arrange
		
		// act
		
		// assert
		assertTrue(true);
	}

	@Test
	public void eliminarVehiculoParqueadero() {
		
		// arrange
		
		// act
		
		// assert
		assertTrue(true);
	}
}
