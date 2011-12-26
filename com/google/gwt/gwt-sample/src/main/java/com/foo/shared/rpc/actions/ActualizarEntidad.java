package com.foo.shared.rpc.actions;

import com.foo.shared.domain.EntidadBase;
import com.foo.shared.rpc.results.ResultadoActualizacionEntidad;

import net.customware.gwt.dispatch.shared.Action;

/**
 * <p>Action used to save an entity.</p>
 * Acci&oacute;n de ejemplo utilizada para salvar una entidad en la base.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * 
 * @param <E>
 *            entidad
 * @since 0.1.0
 */
public class ActualizarEntidad<E extends EntidadBase> implements
		Action<ResultadoActualizacionEntidad> {

	private E entidad;

	ActualizarEntidad() {
		// para que sea serializable
	}

	public ActualizarEntidad(E entidad) {
		super();
		this.entidad = entidad;

	}

	public E getEntidad() {
		return entidad;
	}

}
