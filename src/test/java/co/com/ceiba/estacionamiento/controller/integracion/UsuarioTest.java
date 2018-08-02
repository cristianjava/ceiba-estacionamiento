package co.com.ceiba.estacionamiento.controller.integracion;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class UsuarioTest {

	@Autowired
	VehiculoManager vehiculoManager;
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
		
	}

	@Test
	public void pruebaArquitectura() {

		List<VehiculoEntity> vehiculos1 = vehiculoManager.findAll();
		System.out.println("Inicio prueba ");
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1L);
		Vehiculo vehiculo = new Vehiculo("CVA531", 1200, tipoVehiculo, new Date(), new Date());
		Vehiculo vehiculo2 = new Vehiculo("CVA532", 1200, tipoVehiculo, new Date(), new Date());

		VehiculoEntity vehiculoEntity = VehiculoBuilder.convertirAEntity(vehiculo);
		VehiculoEntity vehiculoEntity2 = VehiculoBuilder.convertirAEntity(vehiculo2);

		vehiculoManager.guardar(vehiculoEntity);
		vehiculoManager.guardar(vehiculoEntity2);
		
		List<VehiculoEntity> vehiculos = vehiculoManager.findAll();
		
		System.out.println(vehiculos.get(0).getPlaca());
	}

}
