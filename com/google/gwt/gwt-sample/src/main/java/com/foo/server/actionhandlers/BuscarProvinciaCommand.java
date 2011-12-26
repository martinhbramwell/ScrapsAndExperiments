package com.foo.server.actionhandlers;


import java.util.ArrayList;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.foo.server.servicios.IProvinciaService;
import com.foo.server.support.ActionHandlerBean;
import com.foo.shared.domain.Provincia;
import com.foo.shared.rpc.events.BuscarProvinciaEvent;
import com.foo.shared.rpc.results.BuscarProvinciaResult;


/**
 * Handler of {@link BuscarProvinciaEvent}.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */

@ActionHandlerBean
public class BuscarProvinciaCommand extends SimpleActionHandler<BuscarProvinciaEvent, BuscarProvinciaResult> {

	@Inject
	IProvinciaService provinciaService;

	@Override
	public BuscarProvinciaResult execute(BuscarProvinciaEvent event,
			ExecutionContext executionContext) throws DispatchException {
		return new BuscarProvinciaResult(new ArrayList<Provincia>(
				provinciaService.findByName(event.getNombre())));
	}

}
