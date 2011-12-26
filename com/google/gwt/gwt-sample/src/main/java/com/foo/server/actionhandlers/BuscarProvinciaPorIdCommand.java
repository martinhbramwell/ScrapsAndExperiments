package com.foo.server.actionhandlers;


import java.util.ArrayList;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.foo.server.servicios.IProvinciaService;
import com.foo.server.support.ActionHandlerBean;
import com.foo.shared.domain.Provincia;
import com.foo.shared.rpc.actions.BuscarProvinciaPorIdAction;
import com.foo.shared.rpc.results.BuscarProvinciaResult;


/**
 * Handler of {@link BuscarProvinciaPorIdAction}.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 *
 */
@ActionHandlerBean
public class BuscarProvinciaPorIdCommand extends SimpleActionHandler<BuscarProvinciaPorIdAction, BuscarProvinciaResult> {

	@Inject
	IProvinciaService provinciaService;

	@Override
	public BuscarProvinciaResult execute(BuscarProvinciaPorIdAction action,
			ExecutionContext executionContext) throws DispatchException {
		Provincia p = provinciaService.findById(action.getId());
		ArrayList<Provincia> provincia = new ArrayList<Provincia>(1);
		if (p != null)
			provincia.add(p);
		return new BuscarProvinciaResult(provincia);
	}
	

}
