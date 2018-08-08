package co.com.ceiba.estacionamiento.negocio.entity.builder;

import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.model.Tiquete;

public final class TiqueteBuilder {

	private TiqueteBuilder() {

	}

	public static TiqueteEntity convertirAEntity(Tiquete tiquete) {
		TiqueteEntity tiqueteEntity = new TiqueteEntity();
		tiqueteEntity.setPlaca(tiquete.getPlaca());
		tiqueteEntity.setFechaIngreso(tiquete.getFechaIngreso());
		tiqueteEntity.setFechaSalida(tiquete.getFechaSalida());
		tiqueteEntity.setValorPago(tiquete.getValorPago());
		tiqueteEntity.setDiasParqueo(tiquete.getDiasEstadia());
		tiqueteEntity.setHorasParqueo(tiquete.getHorasEstadia());
		return tiqueteEntity;
	}

}
