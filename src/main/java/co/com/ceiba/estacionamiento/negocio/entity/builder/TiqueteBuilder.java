package co.com.ceiba.estacionamiento.negocio.entity.builder;

import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;
import co.com.ceiba.estacionamiento.negocio.model.Tiquete;

public final class TiqueteBuilder {

	private TiqueteBuilder() {

	}

//	public static Tiquete convertirAObject(TiqueteEntity tiqueteEntity) {
//		Tiquete tiquete = null;
//		if (tiqueteEntity != null) {
//			tiquete = new Tiquete(tiqueteEntity.getPlaca(), tiqueteEntity.getFechaIngreso(), tiqueteEntity.getFechaSalida(),
//					tiqueteEntity.getValorPago(), tiqueteEntity.getDiasParqueo(), tiqueteEntity.getHorasParqueo());
//		}
//		return tiquete;
//	}
//
//	public static TiqueteEntity convertirAEntity(Tiquete tiquete) {
//		TiqueteEntity tiqueteEntity = new TiqueteEntity();
//		tiqueteEntity.setPlaca(tiquete.getPlaca());
//		tiqueteEntity.setFechaIngreso(tiquete.getFechaIngreso());
//		tiqueteEntity.setFechaSalida(tiquete.getFechaSalida());
//		tiqueteEntity.setValorPago(tiquete.getValorPago());
//		tiqueteEntity.setDiasParqueo(tiquete.getDiasEstadia());
//		tiqueteEntity.setHorasParqueo(tiquete.getHorasEstadia());
//		return tiqueteEntity;
//	}

}
