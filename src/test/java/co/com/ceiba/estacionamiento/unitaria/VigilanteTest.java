package co.com.ceiba.estacionamiento.unitaria;


import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.api.ParqueaderoConfiguration;
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.repository.TarifaRepository;
import co.com.ceiba.estacionamiento.negocio.service.TiqueteService;
import co.com.ceiba.estacionamiento.negocio.service.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.service.VigilanteService;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class VigilanteTest {

	VigilanteService vigilanteService;

	@Autowired
	TarifaRepository tarifaRepository;
	
	@Autowired
	TiqueteService tiqueteService; 	

	@Autowired
	VehiculoService vehiculoService;

	/**
	 * Metodo para instanciar el servicio de vigilanteSerivice con el parqueaderConfiguration
	 */
	private void instanciaVigilanteservice() {
		if (vigilanteService == null) {
			ApplicationContext ctx = new AnnotationConfigApplicationContext(ParqueaderoConfiguration.class);
			vigilanteService = ctx.getBean(VigilanteService.class, vehiculoService, tarifaRepository, tiqueteService);
		}
	}

	@Test
	public void NoParqueadoTest() {

		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculoCarro = vehiculoTestDataBuilder.build();
		this.instanciaVigilanteservice();
		
		try {
			// act 
			vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculoCarro));
		} catch (EstacionamientoException e) {
			// assert
			Assert.assertEquals(Constantes.EL_VEHICULO_NO_ESTA_PARQUEADO, e.getMessage());
		}
	}

}
