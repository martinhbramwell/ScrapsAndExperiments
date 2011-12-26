package com.foo.server.servicios.impl;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.foo.server.dao.IProvinciaDAO;
import com.foo.server.servicios.IProvinciaService;
import com.foo.shared.domain.Provincia;

/**
 * <p>An example service.</p>
 * Ejemplo de la implementaci&oacute;n de un servicio.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
@Service
public class ProvinciaServicesImpl implements IProvinciaService {

	@Inject
	private IProvinciaDAO provinciaDAO;

	@Override
	public Provincia findById(Integer id) {
		return provinciaDAO.findById(id);
	}

	@Override
	public Provincia save(Provincia p) {
		return provinciaDAO.save(p);
	}

	@Override
	public List<Provincia> findByName(String name) {
		return provinciaDAO.findByName(name);
	}

}
