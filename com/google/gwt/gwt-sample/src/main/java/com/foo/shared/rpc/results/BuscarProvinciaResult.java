package com.foo.shared.rpc.results;


import java.util.ArrayList;

import com.foo.shared.domain.Provincia;
import com.foo.shared.rpc.events.BuscarProvinciaEvent;

import net.customware.gwt.dispatch.shared.Result;

/**
 * <p>Result of the server side execution of {@link BuscarProvinciaEvent}</p>
 * Resultado de la ejecuci&oacute;n de la b&uacute;squeda de provincia.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 */
public class BuscarProvinciaResult implements Result {

	private ArrayList<Provincia> provincias;

	/**
	 * Retorna las provincias encontradas.
	 * 
	 * @return las provincias encontradas.
	 */
	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}

	// Propositos de serializacion
	BuscarProvinciaResult() {
		provincias = new ArrayList<Provincia>(0);
	}

	public BuscarProvinciaResult(ArrayList<Provincia> resultado) {
		super();
		provincias = resultado;
	}

}
