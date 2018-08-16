package co.com.ceiba.estacionamiento.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.negocio.entity.TarifaEntity;
import co.com.ceiba.estacionamiento.negocio.repository.TarifaRepository;
import co.com.ceiba.estacionamiento.negocio.service.TarifaService;

@Service
public class TarifaServiceImpl implements TarifaService{

	@Autowired
	TarifaRepository tarifaRepository;
	
	/*
	 * (non-Javadoc)
	 * @see co.com.ceiba.estacionamiento.negocio.manager.TarifaManager#findByPK(java.lang.Integer)
	 */
	@Override
	public List<TarifaEntity> findAll() {
		return tarifaRepository.findAll();
	}
}
