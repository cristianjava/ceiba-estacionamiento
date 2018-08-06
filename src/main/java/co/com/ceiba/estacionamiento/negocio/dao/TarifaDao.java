package co.com.ceiba.estacionamiento.negocio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;

public interface TarifaDao extends JpaRepository<TarifaEntity, Long>  {

}
