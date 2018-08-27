package co.com.ceiba.estacionamiento.negocio.model;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.negocio.validate.Validate;

@Service
public class Validaciones {


	List<Validate> validaciones;

	public List<Validate> getValidaciones() {
		return validaciones;
	}

	public void setValidaciones(List<Validate> validaciones) {
		this.validaciones = validaciones;
	}
	
}
