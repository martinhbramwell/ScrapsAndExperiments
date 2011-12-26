package com.foo.server.rpc.hibernate;

/**
 * Infrastructure exception
 * @author juan.p.gardella
 *
 */
public class InfrastructureException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InfrastructureException(Throwable cause) {
		super(cause);
	}

}
