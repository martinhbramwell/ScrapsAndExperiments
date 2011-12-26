package com.foo.shared.rpc.actions;

import com.foo.shared.domain.Provincia;
import com.foo.shared.rpc.results.BuscarProvinciaResult;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

import net.customware.gwt.dispatch.shared.Action;

/**
 * <p>
 * Action used to search {@link Provincia} entities. This action can be a
 * {@link GwtEvent}, so can be dispatch in view by {@link EventBus} and handle
 * in a presenter.
 * </p> {@link Action} utilizado para buscar provincias por id. Notar que
 * podemos hacerlo evento.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
public class BuscarProvinciaPorIdAction implements
		Action<BuscarProvinciaResult> {

	private Integer id;

	//for serialization purpose
	// Solo a fines de serializacion.
	BuscarProvinciaPorIdAction() {
	}

	public Integer getId() {
		return id;
	}

	public BuscarProvinciaPorIdAction(Integer id) {
		super();
		this.id = id;
	}

}
