package co.com.ceiba.estacionamiento.unitaria;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.negocio.entity.builder.TiqueteBuilder;
import co.com.ceiba.estacionamiento.negocio.model.Tiquete;
import co.com.ceiba.estacionamiento.negocio.service.TiqueteService;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;
import co.com.ceiba.estacionamiento.testdatabuilder.TiqueteTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class TiqueteTest {

	@Autowired
	TiqueteService tiqueteService;
	
	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "CVA531";
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final double VALOR_PAGO = 3000;
	private static final int DIAS_ESTADIA = 0;
	private static final int HORAS_ESTADIA = 3;

	@Test
	public void crearTiqueteTest() {
		
		// arrange
		TiqueteTestDataBuilder tiqueteTestDataBuilder = new TiqueteTestDataBuilder().
				conPlaca(PLACA).
				conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
				conFechaSalida(FECHA_SALIDA != null ? ParqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).
				conValorPago(VALOR_PAGO).
				conDiasEstadia(DIAS_ESTADIA).
				conHorasEstadia(HORAS_ESTADIA);

		// act
		Tiquete tiquete = tiqueteTestDataBuilder.build();

		// assert
		assertEquals(PLACA, tiquete.getPlaca());
		assertEquals(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO), tiquete.getFechaIngreso());
		assertEquals(ParqueaderoUtil.convertStringToDate(FECHA_SALIDA), tiquete.getFechaSalida());
		assertEquals(VALOR_PAGO, tiquete.getValorPago(), 0);
		assertEquals(DIAS_ESTADIA, tiquete.getDiasEstadia());
		assertEquals(HORAS_ESTADIA, tiquete.getHorasEstadia());
	}

	@Test
	public void guardarTiquete() {
		
		// arrange
		TiqueteTestDataBuilder tiqueteTestDataBuilder = new TiqueteTestDataBuilder();
		Tiquete tiquete = tiqueteTestDataBuilder.build();

		// act
		tiqueteService.guardar(TiqueteBuilder.convertirAEntity(tiquete));
		
		// assert
		Assert.assertNotNull(tiquete);
	}
}
