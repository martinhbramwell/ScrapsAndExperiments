package com.foo.server.support;

import net.customware.gwt.dispatch.server.ActionHandlerRegistry;
import net.customware.gwt.dispatch.server.spring.SpringDispatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @see <a
 *      href="http://pgt.de/2009/09/16/use-spring-with-gwt-dispatch/">use-spring-with-gwt-dispatch</a>
 * @since 0.1.0
 * 
 */

@Component
public class DispatchBean extends SpringDispatch {

	@Autowired
	public DispatchBean(ActionHandlerRegistry handlerRegistry) {
		super(handlerRegistry);
	}

}
