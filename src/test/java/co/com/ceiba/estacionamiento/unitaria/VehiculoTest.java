package co.com.ceiba.estacionamiento.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;
import co.com.ceiba.estacionamiento.negocio.util.ParqueaderoUtil;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EstacionamientoApplication.class})
@DataJpaTest
public class VehiculoTest {

	@Autowired
	VehiculoService vehiculoService;
	
	private static final DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static final String PLACA = "CVA531";
	private static final int CILINDRAJE = 1200;
	private static final String FECHA_INGRESO = hourdateFormat.format(new Date());
	private static final String FECHA_SALIDA = null;
	private static final TipoVehiculo TIPO_VEHICULO = new TipoVehiculo("Carro", 1L);

	@Test
	public void crearTiqueteTest() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
				conPlaca(PLACA).
				conCilindraje(CILINDRAJE).
				conFechaIngreso(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO)).
				conFechaSalida(FECHA_SALIDA != null ? ParqueaderoUtil.convertStringToDate(FECHA_SALIDA) : null).
				conTipoVehiculo(TIPO_VEHICULO);

		// act
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();

		// assert
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(CILINDRAJE, vehiculo.getCilindraje());
		assertEquals(ParqueaderoUtil.convertStringToDate(FECHA_INGRESO), vehiculo.getFechaIngreso());
		assertEquals(ParqueaderoUtil.convertStringToDate(FECHA_SALIDA), vehiculo.getFechaSalida());
		assertEquals(TIPO_VEHICULO, vehiculo.getTipoVehiculo());
	}

	@Test
	public void validarVehiculoNoEsteParqueado() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		// act
		VehiculoEntity vehiculoEntity = vehiculoService.findByPlaca(vehiculo.getPlaca());
		
		// assert
		assertNull(vehiculoEntity);
	}

	@Test
	public void validaDesparqueoVehiculoEsteParqueado() {

		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		try {
			// act
			vehiculoService.eliminar(VehiculoBuilder.convertirAEntity(vehiculo));
		} catch (EstacionamientoException e) {
			// assert
			Assert.assertEquals(Constantes.EL_VEHICULO_NO_ESTA_PARQUEADO, e.getMessage());
		}
	}
}
