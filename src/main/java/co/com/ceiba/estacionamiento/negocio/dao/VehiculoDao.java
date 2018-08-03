package co.com.ceiba.estacionamiento.negocio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.estacionamiento.negocio.entity.VehiculoEntity;

public interface VehiculoDao extends JpaRepository<VehiculoEntity, Long>{

	@Query(value="SELECT * FROM VEHICULO_PARQUEADO v WHERE v.placa =:placa", nativeQuery=true)
	VehiculoEntity findVehiculoByPlaca(@Param("placa") String placa);
}
