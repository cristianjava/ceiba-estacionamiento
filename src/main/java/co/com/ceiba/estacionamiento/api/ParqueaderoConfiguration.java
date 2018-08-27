package co.com.ceiba.estacionamiento.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import co.com.ceiba.estacionamiento.negocio.model.Validaciones;
import co.com.ceiba.estacionamiento.negocio.repository.TarifaRepository;
import co.com.ceiba.estacionamiento.negocio.service.TiqueteService;
import co.com.ceiba.estacionamiento.negocio.service.VehiculoService;
import co.com.ceiba.estacionamiento.negocio.service.VigilanteService;
import co.com.ceiba.estacionamiento.negocio.service.impl.VigilanteServiceImpl;
import co.com.ceiba.estacionamiento.negocio.validate.Validate;
import co.com.ceiba.estacionamiento.negocio.validate.impl.ValidateParqueoDisponible;
import co.com.ceiba.estacionamiento.negocio.validate.impl.ValidateRestriccionPlaca;

@Configuration
public class ParqueaderoConfiguration {

	@Bean
	@Lazy(value = true)
	public VigilanteService createVigilante(VehiculoService vehiculoService, TarifaRepository tarifaRepository,
			TiqueteService tiqueteService) {
		
		// se valida que el parqueadero no tenga los 20 carros y 10 motos
		List<Validate> validaciones = new ArrayList<>();
		validaciones.add(new ValidateParqueoDisponible(vehiculoService));

		// se valida que si la placa empieza por A y es Domingo o Lunes permite el parqueo
		validaciones.add(new ValidateRestriccionPlaca());
		Validaciones validacionesNegocio = new Validaciones();
		validacionesNegocio.setValidaciones(validaciones);
		
		return new VigilanteServiceImpl(vehiculoService, tarifaRepository, tiqueteService, validacionesNegocio);

	}
}
