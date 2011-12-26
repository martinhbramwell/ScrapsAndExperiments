package com.foo.server.support;

import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;

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
public class ActionHandlerRegistryBean extends DefaultActionHandlerRegistry {

	@Inject
	public void setHandlers(List<ActionHandler<?, ?>> handlers) {
		for (ActionHandler<?, ?> actionHandler : handlers) {
			addHandler(actionHandler);
		}
	}
}
