package co.com.ceiba.estacionamiento.negocio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;

public interface TiqueteDao extends JpaRepository<TiqueteEntity, Long> {

}
