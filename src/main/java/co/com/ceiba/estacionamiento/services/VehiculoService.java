package co.com.ceiba.estacionamiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.manager.VigilanteManager;
import co.com.ceiba.estacionamiento.negocio.model.ResponseService;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoService {

	@Autowired
	VehiculoManager vehiculoManager;
	
	@Autowired
	VigilanteManager vigilanteManager;
	
	@RequestMapping(method = RequestMethod.POST ,value =  "/guardarVehiculo")
    public void guardarVehiculo(@RequestBody Vehiculo vehiculo) {
		vehiculoManager.guardar(VehiculoBuilder.convertirAEntity(vehiculo));
    }

	@RequestMapping("/buscarVehiculos")
	public List<VehiculoEntity> registarVehiculo() {
		return vehiculoManager.findAll();
	}

	@RequestMapping(method = RequestMethod.POST ,value =  "/registrarParqueo")
    public ResponseService registrarParqueo(@RequestBody Vehiculo vehiculo) {
		ResponseService responseService = new ResponseService();
		try {
			vigilanteManager.ingresarVehiculoParqueadero(VehiculoBuilder.convertirAEntity(vehiculo));
			responseService.setCodigo("200");
			responseService.setMensaje("Exito");
		} catch (Exception e) {
			responseService.setMensaje(e.getMessage());
			throw new EstacionamientoException(e);
		}
		return responseService;
    }

}
