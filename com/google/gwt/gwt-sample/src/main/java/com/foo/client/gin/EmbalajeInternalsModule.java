package com.foo.client.gin;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;

import com.foo.client.places.AppPlaceHistoryMapper;
import com.foo.client.places.MainPlace;
import com.foo.client.presenters.AppActivityMapper;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * <p>Module to internals configurations.</p>
 * 
 *  Espa&ntilde;ol:<br/>
 * M&oacute;dulo de configuraci&oacute;n de clases relacionadas al framework GWT como puede ser
 * {@link PlaceController}. 

 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * @see EmbalajeClientModule
 */
class EmbalajeInternalsModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(SimplePanel.class).in(Singleton.class);
		bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(
				Singleton.class);
		bind(ActivityMapper.class).to(AppActivityMapper.class).in(
				Singleton.class);


	}

	@Provides
	@Singleton
	public PlaceHistoryHandler getHistoryHandler(
			PlaceController placeController, PlaceHistoryMapper historyMapper,
			EventBus eventBus, ActivityManager activityManager) {
		/*
		 * !!Important note!! maybe you have noticed that we are passing an
		 * instance of ActivityManager to the HistoryHandler provider method and
		 * not assigning it to anything. This is simply to force an instance of
		 * activity manager to get created, initialize and register itself with
		 * the event bus. It's just a little trick.
		 */
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, new MainPlace());
		return historyHandler;
	}

	@Provides
	@Singleton
	public DispatchAsync getDispatchAsync() {

		return new StandardDispatchAsync(new DefaultExceptionHandler());

	}

	@Provides
	@Singleton
	public PlaceController getPlaceController(EventBus eventBus) {
		return new PlaceController(eventBus);
	}

	@Provides
	@Singleton
	public ActivityManager getActivityManager(ActivityMapper mapper,
			EventBus eventBus, SimplePanel display) {
		ActivityManager activityManager = new ActivityManager(mapper, eventBus);
		activityManager.setDisplay(display);
		return activityManager;
	}

}