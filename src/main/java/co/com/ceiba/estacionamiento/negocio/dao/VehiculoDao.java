package co.com.ceiba.estacionamiento.negocio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;

public interface VehiculoDao extends JpaRepository<VehiculoEntity, Long>{

	@Query(value="SELECT COUNT(*) FROM TIPO_VEHICULO AS TV WHERE TV.ID =?1", nativeQuery=true)
	Integer findVehiculoByTipoVehiculoActivo(Long id);
	
}
