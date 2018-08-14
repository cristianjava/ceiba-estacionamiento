package co.com.ceiba.estacionamiento.services;

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
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager;
import co.com.ceiba.estacionamiento.negocio.model.ResponseService;
import co.com.ceiba.estacionamiento.negocio.model.TipoVehiculo;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;
import co.com.ceiba.estacionamiento.negocio.util.Constantes;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoService {

	@Autowired
	VehiculoManager vehiculoManager;
	
	@Autowired
	VigilanteManager vigilanteManager;
	
	/**
	 * Servicio para consultar todos los vehiculos parqueados
	 * 
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/buscarVehiculos")
	public List<VehiculoEntity> buscarVehiculos() {
		try {
			List<VehiculoEntity> vehiculosEntity = vehiculoManager.findAll();
			if (vehiculosEntity.isEmpty()) {
				throw new EstacionamientoException(Constantes.NO_HAY_VEHICULOS_PARQUEADERO);
			}
			return vehiculosEntity;
		} catch (Exception e) {
			throw new EstacionamientoException(e);
		}
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
		try {
			return vehiculoManager.findByPlaca(vehiculo.getPlaca());
		} catch (Exception e) {
			throw new EstacionamientoException(e);
		}
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
		ResponseService responseService = new ResponseService();
		try {
			if (vehiculo.getFechaIngreso() == null) {
				vehiculo.setFechaIngreso(new Date());
			}
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
			responseService.setCodigo(Constantes.HTTP_CODIGO_EXITO);
			responseService.setMensaje(Constantes.HTTP_MENSAJE_EXITO);
			responseService.setDescripcion(Constantes.HTTP_DESCRIPCION_EXITO);
		} catch (Exception e) {
			throw new EstacionamientoException(e);
		}
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
		try {
			if (vehiculo.getFechaSalida() == null) {
				vehiculo.setFechaSalida(new Date());
			}
			vehiculo.setTipoVehiculo(new TipoVehiculo());
			return vigilanteManager.salidaVehiculoParqueado(VehiculoBuilder.convertirAEntity(vehiculo));
		} catch (Exception e) {
			throw new EstacionamientoException(e);
		}
    }

}
