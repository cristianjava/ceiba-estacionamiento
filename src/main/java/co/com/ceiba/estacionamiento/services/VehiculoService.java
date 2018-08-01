package co.com.ceiba.estacionamiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;
import co.com.ceiba.estacionamiento.negocio.entity.builder.VehiculoBuilder;
import co.com.ceiba.estacionamiento.negocio.manager.VehiculoManager;
import co.com.ceiba.estacionamiento.negocio.model.Vehiculo;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoService {

	@Autowired
	VehiculoManager vehiculoManager;
	
	@RequestMapping("/buscarVehiculos")
	public List<VehiculoEntity> registarVehiculo() {
		return vehiculoManager.findAll();
	}

	@RequestMapping(method = RequestMethod.POST ,value =  "/guardarVehiculo")
    public void guardarVehiculo(@RequestBody Vehiculo vehiculo) {
		vehiculoManager.guardar(VehiculoBuilder.convertirAEntity(vehiculo));
    }
}
