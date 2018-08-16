package co.com.ceiba.estacionamiento.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;

public interface TarifaRepository extends JpaRepository<TarifaEntity, Long>  {

}
