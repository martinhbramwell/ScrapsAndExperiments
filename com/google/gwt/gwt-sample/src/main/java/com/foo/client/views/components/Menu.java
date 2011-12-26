package com.foo.client.views.components;


import javax.inject.Inject;

import com.foo.client.places.MainPlace;
import com.foo.client.places.ProvinciaListaPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>Application's menu.</p>
 * Menu de la aplicaci&oacute;n.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 *
 */
public class Menu extends Composite {

	private static final Binder binder = GWT.create(Binder.class);

	@UiField(provided = true)
	MenuBar menu;

	private final PlaceController placeController;

	interface Binder extends UiBinder<Widget, Menu> {
	}

	@Inject
	public Menu(PlaceController placeController) {

		//TODO El menu debe levantarse segun el perfil de usuario.
		//The menu must build by user's rol.
		
		this.placeController = placeController;
		// Make some sub-menus that we will cascade from the top menu.
		MenuBar menux = new MenuBar(true);

		menux.addItem("Provincias", getCommand(new ProvinciaListaPlace()));

		menux.addItem("Partidos", getCommand(new MainPlace()));

		// Make a new menu bar, adding a few cascading menus to it.
		menu = new MenuBar();
		menu.addItem("Tablas", menux);

		initWidget(binder.createAndBindUi(this));

	}

	Command getCommand(final Place place) {
		return new Command() {
			@Override
			public void execute() {
				placeController.goTo(place);
			}
		};
	}
}
