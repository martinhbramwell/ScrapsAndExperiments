package com.foo.client.utils;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * <p>
 * Base class used as default {@link AsyncCallback} where handle
 * {@link AsyncCallback#onFailure(Throwable)}. Here we can customize exception
 * handler in the application.
 * </p>
 * 
 * Clase base utilizado como default {@link AsyncCallback} que por defecto
 * maneja el {@link AsyncCallback#onFailure(Throwable)} mostrando un alerta con
 * el mensaje de la excepci&oacute;n.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * 
 * @param <T>
 *            The type of the return value that was declared in the synchronous
 *            version of the method. If the return type is a primitive, use the
 *            boxed version of that primitive (for example, an int return type
 *            becomes an Integer type argument, and a void return type becomes a
 *            Void type argument, which is always null).
 * @since 0.1.0
 */
public abstract class DefaultAsyncCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable e) {
		Window.alert("Ex: " + e.getClass().toString() + "\nMensaje: "
				+ String.valueOf(e.getMessage()));
	}

}
