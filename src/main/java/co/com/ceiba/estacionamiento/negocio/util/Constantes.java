package co.com.ceiba.estacionamiento.negocio.util;

public class Constantes {

	private Constantes() {
		
	}
	
	public static final String EL_VEHICULO_ESTA_PARQUEADO = "El vehiculo ya se encuentra parqueado";
	public static final String EL_VEHICULO_NO_ESTA_PARQUEADO = "El vehiculo no se encuentra parqueado";
	public static final String NO_SE_PUDO_INGRESAR_EL_TIQUETE = "Ocurrio un error al guardar el tiquete";
	public static final String PARQUEADERO_SIN_CUPO = "El parqueadero no cuenta con cupos disponibles para estacionar el vehiculo";
	public static final String RESTRICCION_PARQUEO_PLACA = "El parqueadero no cuenta con cupos disponibles para estacionar el vehiculo";

	public static class TipoVehiculo {
		public static final int CARRO = 1;
		public static final int MOTO = 2;
	}

	public static class CantidadMaximaVehiculos {
		public static final int CANTIDAD_MAXIMA_MOTOS = 10;
		public static final int CANTIDAD_MAXIMA_CARROS = 20;
	}

	public static class Comunes {
		public static final String A = "A";
		public static final int TIEMPO_ADICIONAL_HORA_PARQUEO = 15;
	}
	
}
