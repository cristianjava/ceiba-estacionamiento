package co.com.ceiba.estacionamiento.negocio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import co.com.ceiba.estacionamiento.negocio.manager.impl.VigilanteServiceImpl;

public final class ParqueaderoUtil {

	private static final Logger LOGGER = Logger.getLogger(VigilanteServiceImpl.class.getName());
	
	private ParqueaderoUtil(){
		
	}
	
	public static Date convertStringToDate(String fecha) {
		Date nuevoFormato = null;
		try {
			if (fecha != null && !fecha.isEmpty()) {
				nuevoFormato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").parse(fecha);
			}
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
		return nuevoFormato;
	}
	
}
