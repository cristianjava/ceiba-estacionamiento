package co.com.ceiba.estacionamiento.negocio.exception;

public class EstacionamientoException extends RuntimeException {

	private static final long serialVersionUID = -7540495326499143641L;

	public EstacionamientoException(String message) {
		super(message);
	}

	public EstacionamientoException(Exception e) {
		super(e);
	}
}
