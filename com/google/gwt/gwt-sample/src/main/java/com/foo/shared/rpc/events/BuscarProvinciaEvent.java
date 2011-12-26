package com.foo.shared.rpc.events;

import net.customware.gwt.dispatch.shared.Action;

import com.foo.shared.rpc.handlers.IProvinciaBusquedaHandler;
import com.foo.shared.rpc.results.BuscarProvinciaResult;
import com.google.gwt.event.shared.GwtEvent;

public class BuscarProvinciaEvent extends GwtEvent<IProvinciaBusquedaHandler> implements Action<BuscarProvinciaResult>{

	public static final Type<IProvinciaBusquedaHandler> TYPE = new Type<IProvinciaBusquedaHandler>();
	
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<IProvinciaBusquedaHandler> getAssociatedType() {
		return TYPE;
	}
	
	BuscarProvinciaEvent() {
		this("");
	}
	
	public BuscarProvinciaEvent(String nombre) {
		super();
		this.nombre=nombre;
	}

	@Override
	protected void dispatch(IProvinciaBusquedaHandler handler) {
		handler.onBuscarProvincia(this);
	}
	
	

}
