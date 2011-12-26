package com.foo.client.gin;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.foo.client.entrypoints.MyApp;
import com.foo.client.views.components.Menu;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * <p>The injector used by the application {@link MyApp}.</p>
 *  
 * Espa&ntilde;ol:<br/>  
 * Injector utilizado por el entry point {@link MyApp}.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 */
@GinModules({ EmbalajeClientModule.class , EmbalajeInternalsModule.class})
public interface EmbalajeGinjector extends Ginjector {

	PlaceHistoryHandler getPlaceHistoryHandler();

	SimplePanel getAppWidget();
	
	Menu getMenu();	
	
	DispatchAsync getDispatchAsync();

}