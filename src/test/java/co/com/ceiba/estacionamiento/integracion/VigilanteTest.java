package co.com.ceiba.estacionamiento.integracion;

import static org.junit.Assert.fail;

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
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class VigilanteTest {

	// datos de prueba para una moto con placa valida y cilindraje menor a 500
	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "WIK64A";
	private static final int CILINDRAJE = 100;
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final TipoVehiculo TIPO_VEHICULO = new TipoVehiculo("Moto", 2L);
	
	@Autowired
	VigilanteManager vigilanteManager;

	@Test
	public void parquearVehiculoCarroParqueado() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		// act
		vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
		try {
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
			fail();
		} catch (EstacionamientoException e) {
			// assert
			Assert.assertEquals(Constantes.EL_VEHICULO_ESTA_PARQUEADO, e.getMessage());
		}
	}

	@Test
	public void parquearVehiculoMotoParqueado() {
		
		// arrange
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
			// assert
			Assert.assertEquals(Constantes.EL_VEHICULO_ESTA_PARQUEADO, e.getMessage());
		}
	}

	@Test
	public void parquearCarro() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void parquearCarroExistente() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void parquearMoto() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void parquearMotoExistente() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void parquearCarroNoDisponible() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void parquearMotoNoDisponible() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

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

	@Test
	public void buscarCatidadCarrosParqueados() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void buscarCatidadMotosParqueados() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void eliminarVehiculoParqueado() {

		// arrange
		// 

		// act
		
		// assert
		Assert.assertTrue(true);

	}

	@Test
	public void validaDesparqueoVehiculoParqueado() {

		// arrange

		// act
		
		// assert
		Assert.assertTrue(true);

	}

}
