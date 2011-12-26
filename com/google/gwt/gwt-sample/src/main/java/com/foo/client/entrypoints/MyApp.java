package com.foo.client.entrypoints;


import com.foo.client.gin.EmbalajeGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Application Entry point 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 */
public class MyApp implements EntryPoint {

	@Override
	public void onModuleLoad() {

		EmbalajeGinjector injector = GWT.create(EmbalajeGinjector.class);

		RootPanel.get("header").add(injector.getMenu());
		RootPanel.get("body").add(injector.getAppWidget());

		// Goes to the place represented on URL else default place
		injector.getPlaceHistoryHandler().handleCurrentHistory();

	}
}
