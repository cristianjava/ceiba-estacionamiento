package co.com.ceiba.estacionamiento.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import co.com.ceiba.estacionamiento.negocio.model.Tiquete;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;
import co.com.ceiba.estacionamiento.testdatabuilder.TiqueteTestDataBuilder;

public class TiqueteTest {

	ParqueaderoUtil parqueaderoUtil= new ParqueaderoUtil();
	
	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "CVA531";
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final double VALOR_PAGO = 3000;
	private static final int DIAS_ESTADIA = 0;
	private static final int HORAS_ESTADIA = 3;

	@Test
	public void crearTiqueteTest() throws ParseException {
		
		// arrange
		TiqueteTestDataBuilder tiqueteTestDataBuilder = new TiqueteTestDataBuilder().
				conPlaca(PLACA).
				conFechaIngreso(parqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
				conFechaSalida(FECHA_SALIDA != null ? parqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).
				conValorPago(VALOR_PAGO).
				conDiasEstadia(DIAS_ESTADIA).
				conHorasEstadia(HORAS_ESTADIA);

		// act
		Tiquete tiquete = tiqueteTestDataBuilder.build();

		// assert
		assertEquals(PLACA, tiquete.getPlaca());
		assertEquals(parqueaderoUtil.convertStringToDate(FECHA_INGRESO), tiquete.getFechaIngreso());
		assertEquals(parqueaderoUtil.convertStringToDate(FECHA_SALIDA), tiquete.getFechaSalida());
		assertEquals(VALOR_PAGO, tiquete.getValorPago(), 0);
		assertEquals(DIAS_ESTADIA, tiquete.getDiasEstadia());
		assertEquals(HORAS_ESTADIA, tiquete.getHorasEstadia());
	}

	@Test
	public void guardarTiquete() {
		
		// arrange
		
		// act
		
		// assert
		assertTrue(true);
	}
}
