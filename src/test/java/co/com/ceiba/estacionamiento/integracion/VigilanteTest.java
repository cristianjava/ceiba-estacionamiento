package co.com.ceiba.estacionamiento.integracion;

import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.service.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.service.VigilanteService;
import co.com.ceiba.estacionamiento.negocio.service.impl.VigilanteServiceImpl;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class VigilanteTest {

	private static final Logger LOGGER = Logger.getLogger(VigilanteServiceImpl.class.getName());
	
	// datos de prueba para una moto
	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "WIK64A";
	private static final int CILINDRAJE = 650;
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final TipoVehiculo TIPO_VEHICULO = new TipoVehiculo("Moto", 2L);
	private static final TipoVehiculo TIPO_VEHICULO_CARRO = new TipoVehiculo("Carro", 1L);

	private static final String LETRAS_PLACA_MOTO = "INI";
	private static final String LETRAFIN_PLACA_MOTO = "A";
	private static final int NUMEROS_PLACA_MOTO = 10;

	// datos de prueba para un carro
	private static final String LETRAS_PLACA_CARRO = "INI";
	private static final int NUMEROS_PLACA_CARRO = 100;
	
	// datos de prueba con restricciones
	private static final String PLACA_RESTRICCION = "AIK64A";
	private static final String FECHA_MARTES = "18:25:00 07/08/2018";
	private static final int CILINDRAJE_MENOR = 160;
	
	// datos pruebas fechas
	private static final String FECHA_SALIDA_CARRO_TEST = "11:50:00 09/08/2018";
	private static final String FECHA_SALIDA_MOTO_TEST = "20:25:00 08/08/2018";
	private static final String FECHA_INGRESO_TEST_DOS = "10:00:00 08/08/2018";
	private static final String FECHA_SALIDA_TEST_DOS = "10:30:00 08/08/2018";
	private static final String FECHA_INGRESO_TEST_TRES = "10:00:00 08/08/2018";
	private static final String FECHA_SALIDA_TEST_TRES = "20:00:00 08/08/2018";
	private static final String FECHA_INGRESO_TEST_CUATRO = "10:00:00 08/08/2018";
	private static final String FECHA_SALIDA_TEST_CUATRO = "10:14:00 08/08/2018";
	
	@Autowired
	VigilanteService vigilanteService;
	
	@Autowired
	VehiculoService vehiculoService;

	@Test
	public void parquearVehiculoCarro() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
		
		// act
		vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoCarro));
		
		// assert
		Assert.assertNotNull(vehiculoService.findByPlaca(vehiculoCarro.getPlaca()));
	}

	@Test
	public void parquearVehiculoMoto() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).
				conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
				conFechaSalida(FECHA_SALIDA != null ? ParqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).conTipoVehiculo(TIPO_VEHICULO);

		Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
		
		// act
		vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));

		// assert
		Assert.assertNotNull(vehiculoService.findByPlaca(vehiculoMoto.getPlaca()));
	}

	@Test
	public void parquearVehiculoRestriccionPlaca() {
		
		// arrange
		String mensajeError = null;
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA_RESTRICCION).conCilindraje(CILINDRAJE).
				conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_MARTES)).
				conFechaSalida(FECHA_SALIDA != null ? ParqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).conTipoVehiculo(TIPO_VEHICULO);

		Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
		try {
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
		} catch (EstacionamientoException e) {
			mensajeError = e.getMessage();
		}
		// assert
		Assert.assertEquals(Constantes.RESTRICCION_PARQUEO_PLACA, mensajeError);
	}

	@Test
	public void parquearVehiculoCarroExistente() {
		
		// arrange
		String mensajeError = null;
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		// act
		vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
		try {
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
			fail();
		} catch (EstacionamientoException e) {
			mensajeError = e.getMessage();
		}
		// assert
		Assert.assertEquals(Constantes.EL_VEHICULO_ESTA_PARQUEADO, mensajeError);
	}

	@Test
	public void parquearVehiculoMotoExistente() {
		
		// arrange
		String mensajeError = null;
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).
				conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
				conFechaSalida(FECHA_SALIDA != null ? ParqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).conTipoVehiculo(TIPO_VEHICULO);

		Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
		
		// act
		vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
		try {
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			fail();
		} catch (EstacionamientoException e) {
			mensajeError = e.getMessage();
		}
		// assert
		Assert.assertEquals(Constantes.EL_VEHICULO_ESTA_PARQUEADO, mensajeError);
	}

	@Test
	public void parquearMotoCantidadMaxima() {

		// arrange
		String mensajeError = null;
		int consecutivoPlacaMoto = NUMEROS_PLACA_MOTO;
		for (int i = 0; i < Constantes.CMV_CANTIDAD_MAXIMA_MOTOS; i++) {
			String placa = LETRAS_PLACA_MOTO + String.valueOf(consecutivoPlacaMoto) + LETRAFIN_PLACA_MOTO;
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(placa).conCilindraje(CILINDRAJE).
					conTipoVehiculo(TIPO_VEHICULO);
			Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			consecutivoPlacaMoto++;
		}
		VehiculoTestDataBuilder vehiculoTestDataBuilderIngreso = new VehiculoTestDataBuilder().conCilindraje(CILINDRAJE).
				conTipoVehiculo(TIPO_VEHICULO);
		Vehiculo vehiculoMotoIngreso = vehiculoTestDataBuilderIngreso.build();
		try {
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMotoIngreso));
		} catch (EstacionamientoException e) {
			mensajeError = e.getMessage();
		}
		// assert
		Assert.assertEquals(Constantes.PARQUEADERO_SIN_CUPO, mensajeError);
	}

	@Test
	public void parquearCarroCantidadMaxima() {
		
		// arrange
		String mensajeError = null;
		int consecutivoPlaca = NUMEROS_PLACA_CARRO;
		for (int i = 0; i < Constantes.CMV_CANTIDAD_MAXIMA_CARROS; i++) {
			String placa = LETRAS_PLACA_CARRO + String.valueOf(consecutivoPlaca);
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(placa);
			Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoCarro));
			consecutivoPlaca++;
		}
		VehiculoTestDataBuilder vehiculoTestDataBuilderIngreso = new VehiculoTestDataBuilder();
		Vehiculo vehiculoCarroIngreso = vehiculoTestDataBuilderIngreso.build();
		try {
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoCarroIngreso));
		} catch (EstacionamientoException e) {
			mensajeError = e.getMessage();
		}
		// assert
		Assert.assertEquals(Constantes.PARQUEADERO_SIN_CUPO, mensajeError);
	}

	@Test
	public void desparqueoVehiculoCarroParqueado() {

		try {
			// arrange
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
			Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
			
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoCarro));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechaFinal = dateFormat.parse(FECHA_SALIDA_CARRO_TEST);
			vehiculoCarro.setFechaSalida(fechaFinal);
			TiqueteEntity tiqueteEntity = vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoCarro));
			
			// assert
			Assert.assertNull(vehiculoService.findByPlaca(tiqueteEntity.getPlaca()));
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Test
	public void desparqueoVehiculoMotoParqueado() {

		try {
			// arrange
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).
					conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
					conFechaSalida(FECHA_SALIDA != null ? ParqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).conTipoVehiculo(TIPO_VEHICULO);

			Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
			
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechaFinal = dateFormat.parse(FECHA_SALIDA_MOTO_TEST);
			vehiculoMoto.setFechaSalida(fechaFinal);
			TiqueteEntity tiqueteEntity = vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			// assert
			Assert.assertNull(vehiculoService.findByPlaca(tiqueteEntity.getPlaca()));
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Test
	public void desparqueoVehiculoPruebaDos() {

		try {
			// arrange
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).
					conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO_TEST_DOS)).
					conFechaSalida(ParqueaderoUtil.convertStringToDate(FECHA_SALIDA_TEST_DOS)).conTipoVehiculo(TIPO_VEHICULO);

			Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
			
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechaFinal = dateFormat.parse(FECHA_SALIDA_TEST_DOS);
			vehiculoMoto.setFechaSalida(fechaFinal);
			TiqueteEntity tiqueteEntity = vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			// assert
			Assert.assertNull(vehiculoService.findByPlaca(tiqueteEntity.getPlaca()));
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Test
	public void desparqueoVehiculoPruebaTres() {

		try {
			// arrange
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).
					conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO_TEST_TRES)).
					conFechaSalida(ParqueaderoUtil.convertStringToDate(FECHA_SALIDA_TEST_TRES)).conTipoVehiculo(TIPO_VEHICULO);

			Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
			
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechaFinal = dateFormat.parse(FECHA_SALIDA_TEST_TRES);
			vehiculoMoto.setFechaSalida(fechaFinal);
			TiqueteEntity tiqueteEntity = vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			// assert
			Assert.assertNull(vehiculoService.findByPlaca(tiqueteEntity.getPlaca()));
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Test
	public void desparqueoVehiculoPruebaCuatro() {

		try {
			// arrange
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).
					conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO_TEST_CUATRO)).
					conFechaSalida(ParqueaderoUtil.convertStringToDate(FECHA_SALIDA_TEST_CUATRO)).conTipoVehiculo(TIPO_VEHICULO);

			Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
			
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechaFinal = dateFormat.parse(FECHA_SALIDA_TEST_CUATRO);
			vehiculoMoto.setFechaSalida(fechaFinal);
			TiqueteEntity tiqueteEntity = vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			// assert
			Assert.assertNull(vehiculoService.findByPlaca(tiqueteEntity.getPlaca()));
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Test
	public void desparqueoVehiculoPruebaCinco() {

		try {
			// arrange
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
					conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO_TEST_TRES)).
					conFechaSalida(ParqueaderoUtil.convertStringToDate(FECHA_SALIDA_TEST_TRES)).conTipoVehiculo(TIPO_VEHICULO_CARRO);

			Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
			
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechaFinal = dateFormat.parse(FECHA_SALIDA_TEST_TRES);
			vehiculoMoto.setFechaSalida(fechaFinal);
			TiqueteEntity tiqueteEntity = vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			// assert
			Assert.assertNull(vehiculoService.findByPlaca(tiqueteEntity.getPlaca()));
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Test
	public void desparqueoVehiculoPruebaSeis() {

		try {
			// arrange
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE_MENOR).
					conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO_TEST_CUATRO)).
					conFechaSalida(ParqueaderoUtil.convertStringToDate(FECHA_SALIDA_TEST_CUATRO)).conTipoVehiculo(TIPO_VEHICULO);

			Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
			
			// act
			vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechaFinal = dateFormat.parse(FECHA_SALIDA_TEST_CUATRO);
			vehiculoMoto.setFechaSalida(fechaFinal);
			TiqueteEntity tiqueteEntity = vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			
			// assert
			Assert.assertNull(vehiculoService.findByPlaca(tiqueteEntity.getPlaca()));
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
	}

}
