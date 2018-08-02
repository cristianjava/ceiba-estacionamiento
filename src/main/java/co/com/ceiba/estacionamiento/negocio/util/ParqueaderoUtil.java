package co.com.ceiba.estacionamiento.negocio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParqueaderoUtil {

	public Date convertStringToDate(String fecha) throws ParseException {
		Date nuevoFormato = null;
		if (fecha != null && !fecha.isEmpty()) {
			nuevoFormato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").parse(fecha);
		}
		return nuevoFormato;
	}
}
