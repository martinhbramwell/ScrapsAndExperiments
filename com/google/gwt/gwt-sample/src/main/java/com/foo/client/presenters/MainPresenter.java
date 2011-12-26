package com.foo.client.presenters;


import javax.inject.Inject;

import com.foo.client.places.MainPlace;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * <p> {@link MainPlace}'s presenter.</p>
 * Presenter asociado a {@link MainPlace}.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 *
 */
public class MainPresenter extends AbstractActivity  {

	private final Display vista;
	
	public interface Display extends IsWidget{
		
	}

	@Inject
	public MainPresenter(Display vista) {
		this.vista=vista;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
     	panel.setWidget(vista.asWidget());
	}


	



}
