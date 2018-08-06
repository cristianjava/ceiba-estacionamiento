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
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager;
import co.com.ceiba.estacionamiento.negocio.manager.impl.VigilanteManagerImpl;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.util.Constantes.CantidadMaximaVehiculos;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class VigilanteTest {

	private static final Logger LOGGER = Logger.getLogger(VigilanteManagerImpl.class.getName());
	
	// datos de prueba para una moto
	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "WIK64A";
	private static final int CILINDRAJE = 100;
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final TipoVehiculo TIPO_VEHICULO = new TipoVehiculo("Moto", 2L);

	private static final String LETRAS_PLACA_MOTO = "INI";
	private static final String LETRAFIN_PLACA_MOTO = "A";
	private static final int NUMEROS_PLACA_MOTO = 10;

	// datos de prueba para un carro
	private static final String LETRAS_PLACA_CARRO = "INI";
	private static final int NUMEROS_PLACA_CARRO = 100;
	
	// datos de prueba con restricciones
	private static final String PLACA_RESTRICCION = "AIK64A";
	
	@Autowired
	VigilanteManager vigilanteManager;
	
	@Autowired
	VehiculoManager vehiculoManager;

	@Test
	public void parquearVehiculoCarro() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
		
		// act
		vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoCarro));
		
		// assert
		Assert.assertNotNull(vehiculoManager.findByPlaca(vehiculoCarro.getPlaca()));
	}

	@Test
	public void parquearVehiculoMoto() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).
				conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
				conFechaSalida(FECHA_SALIDA != null ? ParqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).conTipoVehiculo(TIPO_VEHICULO);

		Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
		
		// act
		vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));

		// assert
		Assert.assertNotNull(vehiculoManager.findByPlaca(vehiculoMoto.getPlaca()));
	}

	@Test
	public void parquearVehiculoCarroExistente() {
		
		// arrange
		String mensajeError = null;
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		// act
		vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
		try {
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
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
		vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
		try {
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
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
		for (int i = 0; i < CantidadMaximaVehiculos.CANTIDAD_MAXIMA_MOTOS; i++) {
			String placa = LETRAS_PLACA_MOTO + String.valueOf(consecutivoPlacaMoto) + LETRAFIN_PLACA_MOTO;
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(placa).conCilindraje(CILINDRAJE).
					conTipoVehiculo(TIPO_VEHICULO);
			Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
			consecutivoPlacaMoto++;
		}
		VehiculoTestDataBuilder vehiculoTestDataBuilderIngreso = new VehiculoTestDataBuilder().conCilindraje(CILINDRAJE).
				conTipoVehiculo(TIPO_VEHICULO);
		Vehiculo vehiculoMotoIngreso = vehiculoTestDataBuilderIngreso.build();
		try {
			// act
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMotoIngreso));
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
		for (int i = 0; i < CantidadMaximaVehiculos.CANTIDAD_MAXIMA_CARROS; i++) {
			String placa = LETRAS_PLACA_CARRO + String.valueOf(consecutivoPlaca);
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(placa);
			Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoCarro));
			consecutivoPlaca++;
		}
		VehiculoTestDataBuilder vehiculoTestDataBuilderIngreso = new VehiculoTestDataBuilder();
		Vehiculo vehiculoCarroIngreso = vehiculoTestDataBuilderIngreso.build();
		try {
			// act
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoCarroIngreso));
		} catch (EstacionamientoException e) {
			mensajeError = e.getMessage();
		}
		// assert
		Assert.assertEquals(Constantes.PARQUEADERO_SIN_CUPO, mensajeError);
	}

	@Test
	public void parquearVehiculoRestriccionPlaca() {
		
		// arrange
		String mensajeError = null;
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conPlaca(PLACA_RESTRICCION).conCilindraje(CILINDRAJE).
				conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
				conFechaSalida(FECHA_SALIDA != null ? ParqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).conTipoVehiculo(TIPO_VEHICULO);

		Vehiculo vehiculoMoto = vehiculoTestDataBuilder.build();
		try {
			// act
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoMoto));
		} catch (EstacionamientoException e) {
			mensajeError = e.getMessage();
		}
		// assert
		Assert.assertEquals(Constantes.RESTRICCION_PARQUEO_PLACA, mensajeError);
	}

	@Test
	public void validaDesparqueoVehiculoParqueado() {

		try {
			// arrange
			VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
			Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
			
			// act
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculoCarro));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechaFinal;
			fechaFinal = dateFormat.parse("14:59:00 09/08/2018");
			vehiculoCarro.setFechaSalida(fechaFinal);
			vigilanteManager.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoCarro));
			
			// assert
			Assert.assertNull(vehiculoManager.findByPlaca(vehiculoCarro.getPlaca()));
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Test
	public void desparquearMenosUnDiaCarro() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void desparquearMenosUnDiaMoto() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void desparquearMasUnDiaCarro() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void desparquearMasUnDiaMoto() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

}
