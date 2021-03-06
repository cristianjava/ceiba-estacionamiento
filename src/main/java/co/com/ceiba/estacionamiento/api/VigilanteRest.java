package co.com.ceiba.estacionamiento.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.repository.TarifaRepository;
import co.com.ceiba.estacionamiento.negocio.service.TiqueteService;
import co.com.ceiba.estacionamiento.negocio.service.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.service.VigilanteService;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;

@RestController
@RequestMapping("/vehiculo")
public class VigilanteRest {

	VigilanteService vigilanteService;
	
	@Autowired
	VehiculoService vehiculoService;

	@Autowired
	TarifaRepository tarifaRepository;
	
	@Autowired
	TiqueteService tiqueteService; 	

	/**
	 * Servicio para consultar todos los vehiculos parqueados
	 * 
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/buscarVehiculos")
	public List<VehiculoEntity> buscarVehiculos() {
		return vehiculoService.findAll();
	}

	/**
	 * Servicio para consultar un vehiculo parqueado por la placa
	 * 
	 * @param vehiculo
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST ,value =  "/buscarVehiculoPlaca")
	public VehiculoEntity buscarVehiculoPlaca(@RequestBody Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = vehiculoService.findByPlaca(vehiculo.getPlaca());
		if (vehiculoEntity == null) {
			throw new EstacionamientoException(Constantes.EL_VEHICULO_NO_ESTA_PARQUEADO);
		}
		return vehiculoEntity;
	}

	/**
	 * Servicio rest para registrar un vehiculo al parqueadero
	 * 
	 * @param vehiculo
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST ,value =  "/registrarParqueo")
    public void registrarParqueo(@RequestBody Vehiculo vehiculo) {
		if (vigilanteService == null) {
			ApplicationContext ctx = new AnnotationConfigApplicationContext(ParqueaderoConfiguration.class);
			vigilanteService = ctx.getBean(VigilanteService.class, vehiculoService, tarifaRepository, tiqueteService);
		}
		vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
    }

	/**
	 * Servicio para desparquear un vehiculo y para calcular el valor a pagar
	 * 
	 * @param vehiculo
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST ,value =  "/salidaParqueadero")
    public TiqueteEntity salidaParqueadero(@RequestBody Vehiculo vehiculo) {
		if (vigilanteService == null) {
			ApplicationContext ctx = new AnnotationConfigApplicationContext(ParqueaderoConfiguration.class);
			vigilanteService = ctx.getBean(VigilanteService.class, vehiculoService, tarifaRepository, tiqueteService);
		}
		return vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculo));
    }

}
