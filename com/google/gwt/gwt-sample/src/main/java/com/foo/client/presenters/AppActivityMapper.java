package com.foo.client.presenters;


import javax.inject.Inject;

import com.foo.client.places.ProvinciaEditPlace;
import com.foo.client.places.ProvinciaListaPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Provider;

/**
 * 
 * <p>Map places to activities.</p>
 * 
 * Mapea las actividades que deben comenzar dado un place. Para m&aacute;s
 * informaci&oacute;n de como funciona las actividades y places consultar:
 * <ul>
 * <li><a
 * href="http://tbroyer.posterous.com/gwt-21-activities">gwt-21-activities</a></li>
 * <li><a href="http://tbroyer.posterous.com/gwt-21-places">gwt-21-places</a></li>
 * 
 * </ul>
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
public class AppActivityMapper implements ActivityMapper {

	private final Provider<ProvinciaListaPresenter> providerProvinciaListaPresenter;
	private final Provider<ProvinciaEditPresenter> provinciaEditProvider;
	private final Provider<MainPresenter> providerMainPresenter;

	// TODO:MEJORA>> Ver como evitar hacer gigantesco este constructor.
	@Inject
	public AppActivityMapper(
			Provider<ProvinciaListaPresenter> providerProvinciaListaPresenter,
			Provider<ProvinciaEditPresenter> provinciaEditProvider,
			Provider<MainPresenter> providerMainPresenter) {
		this.providerProvinciaListaPresenter = providerProvinciaListaPresenter;
		this.provinciaEditProvider = provinciaEditProvider;
		this.providerMainPresenter = providerMainPresenter;
	}

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof ProvinciaListaPlace)
			return providerProvinciaListaPresenter.get();
		else if (place instanceof ProvinciaEditPlace)
			return provinciaEditProvider.get().withPlace(
					(ProvinciaEditPlace) place);
		return providerMainPresenter.get();
	}
}
