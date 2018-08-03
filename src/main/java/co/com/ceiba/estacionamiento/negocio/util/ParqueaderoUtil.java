package co.com.ceiba.estacionamiento.negocio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParqueaderoUtil {

	private ParqueaderoUtil(){
		
	}
	
	public static Date convertStringToDate(String fecha) {
		Date nuevoFormato = null;
		try {
			if (fecha != null && !fecha.isEmpty()) {
				nuevoFormato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").parse(fecha);
			}
		} catch (ParseException e) {
			nuevoFormato = null;
		}
		return nuevoFormato;
	}
	
	public static boolean isEmptyNull(Long valor) {
		return (valor == 0 ? true : false);
	}
}
