package co.com.ceiba.estacionamiento.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.model.ResponseService;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.service.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.service.VigilanteService;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;

@RestController
@RequestMapping("/vehiculo")
public class VigilanteRest {

	@Autowired
	VehiculoService vehiculoService;
	
	@Autowired
	VigilanteService vigilanteService;
	
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
    public ResponseService registrarParqueo(@RequestBody Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = vehiculoService.findByPlaca(vehiculo.getPlaca());
		if (vehiculoEntity != null) {
			throw new EstacionamientoException(Constantes.EL_VEHICULO_ESTA_PARQUEADO);
		}
		vehiculo.setFechaIngreso(vehiculo.getFechaIngreso() == null ? new Date() : vehiculo.getFechaIngreso());
		ResponseService responseService = new ResponseService();
		vigilanteService.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
		responseService.setCodigo(Constantes.HTTP_CODIGO_EXITO);
		responseService.setMensaje(Constantes.HTTP_MENSAJE_EXITO);
		responseService.setDescripcion(Constantes.HTTP_DESCRIPCION_EXITO);
		return responseService;
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
		vehiculo.setFechaSalida(vehiculo.getFechaSalida() == null ? new Date() : vehiculo.getFechaSalida());
		vehiculo.setTipoVehiculo(new TipoVehiculo());
		return vigilanteService.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculo));
    }

}
