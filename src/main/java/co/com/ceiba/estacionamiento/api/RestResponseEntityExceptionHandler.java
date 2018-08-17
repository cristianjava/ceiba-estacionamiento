package co.com.ceiba.estacionamiento.api;

import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.ceiba.estacionamiento.negocio.exception.EstacionamientoException;
import co.com.ceiba.estacionamiento.negocio.service.impl.VigilanteServiceImpl;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGERCONSOLE = Logger.getLogger(VigilanteServiceImpl.class.getName());
	
	@ExceptionHandler({ EstacionamientoException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<Object> handleAccesTecnicalExceptioon(Exception ex, WebRequest request) {
		LOGGERCONSOLE.info(ex.toString());
		return new ResponseEntity<Object>("Se presentó un error comuniquese con el administrador del sistema", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
