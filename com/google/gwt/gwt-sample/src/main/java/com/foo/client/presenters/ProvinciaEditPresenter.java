package com.foo.client.presenters;

import javax.inject.Inject;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.foo.client.places.ProvinciaEditPlace;
import com.foo.client.places.ProvinciaListaPlace;
import com.foo.client.utils.DefaultAsyncCallback;
import com.foo.shared.domain.Provincia;
import com.foo.shared.rpc.actions.ActualizarEntidad;
import com.foo.shared.rpc.actions.BuscarProvinciaPorIdAction;
import com.foo.shared.rpc.results.BuscarProvinciaResult;
import com.foo.shared.rpc.results.ResultadoActualizacionEntidad;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ValueBoxBase;

/**
 * <p>An example presenter where use {@link EventBus}.</p>
 * Presenter a modo de ejemplo. En este presenter no se usa el {@link EventBus}.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
public class ProvinciaEditPresenter extends AbstractActivity {

	public interface Display extends IsWidget {

		ValueBoxBase<String> getNombreInput();

		void limpiarPantalla();

		Button getBotonCancelar();

		Button getBotonGuardar();

		SpanElement getMensajes();

		void deshabilitarInputText();

	}

	private ProvinciaEditPlace place;
	private Provincia p;
	private final Display vista;
	private final PlaceController placeController;
	private final DispatchAsync dispatch;

	@Inject
	public ProvinciaEditPresenter(Display vista,
			PlaceController placeController, DispatchAsync dispatch) {
		this.vista = vista;
		this.placeController = placeController;
		this.dispatch = dispatch;
		bind();
	}

	private void bind() {
		vista.getBotonCancelar().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				placeController.goTo(new ProvinciaListaPlace());
			}
		});

		vista.getBotonGuardar().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (isBlank(vista.getNombreInput().getText())) {
					vista.getMensajes().setInnerText("Valor requerido");
				} else {
					p.setNombre(SafeHtmlUtils.htmlEscape(vista.getNombreInput().getText()));
					dispatch.execute(
							new ActualizarEntidad<Provincia>(p),
							new DefaultAsyncCallback<ResultadoActualizacionEntidad>() {
								@Override
								public void onSuccess(
										ResultadoActualizacionEntidad result) {
									// TODO Auto-generated method stub
									vista.getBotonGuardar().setEnabled(false);
									vista.deshabilitarInputText();
									vista.getBotonCancelar().setText("Aceptar");
								}
							});
				}
			}

		});
	}

	private static boolean isBlank(String text) {
		return text != null && text.trim().length() == 0;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		vista.limpiarPantalla();
		if (place.getId() != null) {
			p = null;
			dispatch.execute(new BuscarProvinciaPorIdAction(place.getId()),
					new DefaultAsyncCallback<BuscarProvinciaResult>() {
						@Override
						public void onSuccess(BuscarProvinciaResult result) {
							if (!result.getProvincias().isEmpty()) {
								p = result.getProvincias().get(0);
								vista.getNombreInput().setText(p.getNombre());
							}

						}
					});

		}
		panel.setWidget(vista.asWidget());

	}

	public void goTo(Place place) {
		placeController.goTo(place);
	}

	public Activity withPlace(ProvinciaEditPlace place) {
		this.place = place;
		return this;
	}

}
