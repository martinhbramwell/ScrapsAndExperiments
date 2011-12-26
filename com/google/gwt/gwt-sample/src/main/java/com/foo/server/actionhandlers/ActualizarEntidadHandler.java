package com.foo.server.actionhandlers;


import javax.inject.Inject;

import com.foo.server.servicios.IProvinciaService;
import com.foo.server.support.ActionHandlerBean;
import com.foo.shared.domain.EntidadBase;
import com.foo.shared.domain.Provincia;
import com.foo.shared.rpc.actions.ActualizarEntidad;
import com.foo.shared.rpc.actions.BuscarProvinciaPorIdAction;
import com.foo.shared.rpc.results.ResultadoActualizacionEntidad;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

/**
 * Handler of {@link BuscarProvinciaPorIdAction}.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
@ActionHandlerBean
public class ActualizarEntidadHandler
		implements
		ActionHandler<ActualizarEntidad<? extends EntidadBase>, ResultadoActualizacionEntidad> {

	@Inject
	IProvinciaService provinciaService;

	@SuppressWarnings("unchecked")
	@Override
	public Class<ActualizarEntidad<? extends EntidadBase>> getActionType() {
		// Fuente
		// http://stackoverflow.com/questions/75175/create-instance-of-generic-type-in-java
		return (Class<ActualizarEntidad<? extends EntidadBase>>) new ActualizarEntidad<EntidadBase>(null).getClass();

	}

	@SuppressWarnings("serial")
	@Override
	public ResultadoActualizacionEntidad execute(
			ActualizarEntidad<? extends EntidadBase> action,
			ExecutionContext context) throws DispatchException {
		if (action.getEntidad() instanceof Provincia){
			provinciaService.save((Provincia)action.getEntidad());
			return new ResultadoActualizacionEntidad();
		}
		throw new DispatchException("No se pudo salver"){};
	}

	@Override
	public void rollback(ActualizarEntidad<? extends EntidadBase> action,
			ResultadoActualizacionEntidad result, ExecutionContext context)
			throws DispatchException {
		// TODO Auto-generated method stub

	}

}
