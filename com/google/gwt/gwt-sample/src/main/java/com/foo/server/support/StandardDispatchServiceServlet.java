package com.foo.server.support;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.standard.AbstractStandardDispatchServlet;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <p>The unique servlet. Where configure action handlers.</p>
 * Servlet dispatch. Configura los action handlers.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @see <a
 *      href="http://pgt.de/2009/09/16/use-spring-with-gwt-dispatch/">use-spring-with-gwt-dispatch</a>
 * @since 0.1.0
 * 
 */
@SuppressWarnings("serial")
public final class StandardDispatchServiceServlet extends
		AbstractStandardDispatchServlet {

	private Dispatch dispatch;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		AutowireCapableBeanFactory beanFactory = ctx
				.getAutowireCapableBeanFactory();
		beanFactory.autowireBean(this);
	}

	@Inject
	public void setDispatch(Dispatch dispatch) {
		this.dispatch = dispatch;
	}

	@Override
	protected Dispatch getDispatch() {
		return dispatch;
	}

}
