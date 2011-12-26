package com.foo.client.gin;

import com.foo.client.presenters.MainPresenter;
import com.foo.client.presenters.MenuPresenter;
import com.foo.client.presenters.ProvinciaEditPresenter;
import com.foo.client.presenters.ProvinciaListaPresenter;
import com.foo.client.presenters.ProvinciaEditPresenter.Display;
import com.foo.client.views.MainView;
import com.foo.client.views.ProvinciaEdit;
import com.foo.client.views.ProvinciaLista;
import com.foo.client.views.components.Menu;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;

/**
 * <p>Module where configure views and presenters (or activities). It can be used
 * to configure other classes, as services or collaborators. Don't use to gwt
 * related classes as {@link PlaceController}, etc. For this type of
 * configuration use {@link EmbalajeInternalsModule}.</p> 
 * 
 * Espa&ntilde;ol:<br/>
 * M&oacute;dulo de configuraci&oacute;n de vistas y presenters (&oacute; actividades). Se puede
 * usar para otras clases, como servicios y/o utilitarios. No usar para clases
 * relacionadas al framework GWT como puede ser {@link PlaceController} etc.
 * para eso usar {@link EmbalajeInternalsModule}.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
class EmbalajeClientModule extends AbstractGinModule {

	@Override
	protected void configure() {

		// Vistas
		bind(ProvinciaListaPresenter.Display.class).to(ProvinciaLista.class)
				.in(Singleton.class);
		bind(Display.class).to(ProvinciaEdit.class).in(Singleton.class);
		bind(Menu.class).in(Singleton.class);
		bind(com.foo.client.presenters.MainPresenter.Display.class).to(
				MainView.class).in(Singleton.class);

		// Presenter
		bind(ProvinciaListaPresenter.class);
		bind(MenuPresenter.class);
		bind(ProvinciaEditPresenter.class);
		bind(MainPresenter.class);

	}

}