package com.foo.server.support;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.customware.gwt.dispatch.shared.Action;

import org.springframework.stereotype.Component;

/**
 * <p>Annotation to configure action handlers.</p>
 * Anotaci&oacute;n utilizada para definir los handlers de los {@link Action} definidos.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @see <a
 *      href="http://pgt.de/2009/09/16/use-spring-with-gwt-dispatch/">use-spring-with-gwt-dispatch</a>
 * @since 0.1.0
 * 
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionHandlerBean {}
