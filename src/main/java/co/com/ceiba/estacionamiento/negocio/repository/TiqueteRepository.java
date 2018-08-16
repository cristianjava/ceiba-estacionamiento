package co.com.ceiba.estacionamiento.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.estacionamiento.negocio.entity.TiqueteEntity;

public interface TiqueteRepository extends JpaRepository<TiqueteEntity, Long> {

}
