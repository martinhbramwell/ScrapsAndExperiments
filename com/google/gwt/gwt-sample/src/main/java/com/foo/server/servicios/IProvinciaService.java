package com.foo.server.servicios;

import com.foo.shared.domain.Provincia;

/**
 * <p>Contract required used by an use case or history.</p>
 * <p>
 * Contrato de un servicio requerido para un CU o historia.
 * </p>
 * <i>Nota:</i> Esta interfaz es a modo de ejemplo.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * 
 * @since 0.1.0
 *  
 */
public interface IProvinciaService {

	Provincia findById(Integer id);

	Provincia save(Provincia p);

	java.util.List<Provincia> findByName(String name);

}
