package co.com.ceiba.estacionamiento.negocio.util;

public final class Constantes {

	private Constantes() {
		super();
	}

	public static final String EL_VEHICULO_ESTA_PARQUEADO = "El vehiculo ya se encuentra parqueado";
	public static final String EL_VEHICULO_NO_ESTA_PARQUEADO = "El vehiculo no se encuentra parqueado";
	public static final String NO_HAY_VEHICULOS_PARQUEADERO = "No hay vehiculos en el parqueadero";
	public static final String PARQUEADERO_SIN_CUPO = "El parqueadero no cuenta con cupos disponibles para estacionar el vehiculo";
	public static final String RESTRICCION_PARQUEO_PLACA = "solo pueden ingresar al parqueadero los días lunes y domingos";
	public static final String HTTP_CODIGO_EXITO = "200";
	public static final String HTTP_MENSAJE_EXITO = "Exito";
	public static final String HTTP_DESCRIPCION_EXITO = "La petición se ejecutó correctamente";

	public static final int TV_CARRO = 1;
	public static final int TV_MOTO = 2;

	public static final int CMV_CANTIDAD_MAXIMA_MOTOS = 10;
	public static final int CMV_CANTIDAD_MAXIMA_CARROS = 20;

	public static final String C_A = "A";
	public static final int C_TIEMPO_ADICIONAL_HORA_PARQUEO = 15;
	public static final int C_QUINIENTOS = 500;
	public static final int C_CERO = 15;

	public static final int TT_HORA_CARRO = 0;
	public static final int TT_DIA_CARRO = 1;
	public static final int TT_HORA_MOTO = 2;
	public static final int TT_DIA_MOTO = 3;
	public static final int TT_CC_MAYOR_500 = 4;

}
