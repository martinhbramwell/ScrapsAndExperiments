package com.foo.server.dao;


import java.util.List;

import com.foo.shared.domain.Provincia;


/**
 * <p>An example DAO.</p>
 * DAO a modo de ejemplo.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 *
 */
public interface IProvinciaDAO {

	public abstract Provincia findById(Integer id);

	public abstract Provincia save(Provincia p);

	public abstract List<Provincia> findByName(String name);

}