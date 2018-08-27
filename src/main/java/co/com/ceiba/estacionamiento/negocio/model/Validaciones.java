package co.com.ceiba.estacionamiento.negocio.model;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.negocio.validate.Validate;

@Service
public class Validaciones {


	List<Validate> validations;

	public List<Validate> getValidaciones() {
		return validations;
	}

	public void setValidaciones(List<Validate> validations) {
		this.validations = validations;
	}
	
}
