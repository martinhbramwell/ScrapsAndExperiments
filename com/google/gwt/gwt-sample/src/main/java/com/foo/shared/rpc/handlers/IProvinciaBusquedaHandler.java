package com.foo.shared.rpc.handlers;


import com.foo.shared.rpc.events.BuscarProvinciaEvent;
import com.google.gwt.event.shared.EventHandler;

/**
 * <p> {@link BuscarProvinciaEvent}'s handler. </p>
 * Handler para el evento {@link BuscarProvinciaEvent}. Creado a modo de ejemplo.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 *
 */
public interface IProvinciaBusquedaHandler extends EventHandler{
	
	//Se podria hacer un handler a nivel historia a CU.
	/**
	 * Se ejecuta cuando se dispara el evento {@link BuscarProvinciaEvent}.
	 * @param buscarProvinciaEvent evento elevado.
	 */
	void onBuscarProvincia(BuscarProvinciaEvent buscarProvinciaEvent);
}
