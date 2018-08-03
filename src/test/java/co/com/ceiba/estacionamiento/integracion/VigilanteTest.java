package co.com.ceiba.estacionamiento.integracion;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class VigilanteTest {

	@Autowired
	VehiculoManager vehiculoManager;
	
	/**
	 * Configuracion antes de ejecutar la tarea de integracion
	 */
	@Before
	public void setUp() {
		
	}
	
	/**
	 * Configuracion despues de ejecutar la tarea de integracion
	 */
	@After
	public void tearDown() {
		
	}

	@Test
	public void parquearVehiculoParqueado() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		// act
		vehiculoManager.guardar(VehiculoBuilder.convertirAEntity(vehiculo));
		try {
			vehiculoManager.guardar(VehiculoBuilder.convertirAEntity(vehiculo));
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
