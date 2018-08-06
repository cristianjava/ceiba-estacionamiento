package co.com.ceiba.estacionamiento.negocio.entity.builder;

import java.io.Serializable;

import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;
import co.com.ceiba.estacionamiento.negocio.model.Tarifa;

public class TarifaBuilder implements Serializable{

	private static final long serialVersionUID = 4871484631900559882L;

	private TarifaBuilder() {}
	
	public static Tarifa convertirAObject(TarifaEntity tarifaEntity) {
		Tarifa tarifa = null;
		if(tarifaEntity != null) {
			tarifa = new Tarifa();
			tarifa.setIdTarifa(tarifaEntity.getIdTarifa());
			tarifa.setDescripcionTarifa(tarifaEntity.getDescripcionTarifa());
			tarifa.setValor(tarifaEntity.getValor());
		}
		return tarifa;
	}
	
	public static TarifaEntity convertirAEntity(Tarifa tarifa) {
		TarifaEntity tarifaEntity = new TarifaEntity();
		tarifaEntity.setIdTarifa(tarifa.getIdTarifa());
		tarifaEntity.setDescripcionTarifa(tarifa.getDescripcionTarifa());
		tarifaEntity.setValor(tarifa.getValor());
		return tarifaEntity;
	}
}
