package co.com.ceiba.estacionamiento.integracion;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.api.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class VehiculoTest {

	// datos pruebas fechas
	private static final String FECHA_INGRESO_TEST_DOS = "10:00:00 08/08/2018";
	private static final String FECHA_SALIDA_TEST_DOS = "10:30:00 08/08/2018";
	
	@Autowired
	VehiculoManager vehiculoManager;

	@Autowired
	VehiculoService vehiculoService;

	@Test
	public void serviceParqueoBusca() {

		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conFechaIngreso(null);
		Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
		List<VehiculoEntity> listaVehiculos = null;
		
		// act
		vehiculoService.registrarParqueo(vehiculoCarro);
		listaVehiculos = vehiculoService.buscarVehiculos();
		
		// assert
		Assert.assertNotNull(listaVehiculos);
	}

	@Test
	public void serviceParqueoBuscaPlaca() {

		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
		VehiculoEntity vehiculoEntity = null;
		
		// act
		vehiculoService.registrarParqueo(vehiculoCarro);
		vehiculoEntity = vehiculoService.buscarVehiculoPlaca(vehiculoCarro);
		
		// assert
		Assert.assertNotNull(vehiculoEntity);
	}

	@Test
	public void serviceParqueoDesparqueo() {

		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
		
		// act
		vehiculoService.registrarParqueo(vehiculoCarro);
		vehiculoService.salidaParqueadero(vehiculoCarro);
		
		// assert
		Assert.assertNull(vehiculoManager.findByPlaca(vehiculoCarro.getPlaca()));
	}

	@Test
	public void serviceParqueoDesparqueoPruebaDos() {

		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO_TEST_DOS))
				.conFechaSalida(ParqueaderoUtil.convertStringToDate(FECHA_SALIDA_TEST_DOS));
		Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
		
		// act
		vehiculoService.registrarParqueo(vehiculoCarro);
		vehiculoService.salidaParqueadero(vehiculoCarro);
		
		// assert
		Assert.assertNull(vehiculoManager.findByPlaca(vehiculoCarro.getPlaca()));
	}
}
