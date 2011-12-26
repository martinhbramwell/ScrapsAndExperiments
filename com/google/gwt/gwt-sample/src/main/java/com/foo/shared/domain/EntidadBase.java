package com.foo.shared.domain;

import java.io.Serializable;
/**
 * <p>Base entity.</p>
 * Entidad base.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 *
 */
public abstract class EntidadBase implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public abstract Serializable getId();

}
