package com.foo.client.views;


import com.foo.client.presenters.MainPresenter.Display;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>Main view.</p>
 * Vista principal.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 */
public class MainView extends Composite implements Display {

	private static final Binder binder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, MainView> {
	}

	public MainView() {
		initWidget(binder.createAndBindUi(this));
	}

}
