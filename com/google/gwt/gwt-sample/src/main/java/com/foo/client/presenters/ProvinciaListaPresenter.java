package com.foo.client.presenters;

import javax.inject.Inject;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.foo.client.places.ProvinciaEditPlace;
import com.foo.client.utils.DefaultAsyncCallback;
import com.foo.shared.domain.Provincia;
import com.foo.shared.rpc.events.BuscarProvinciaEvent;
import com.foo.shared.rpc.handlers.IProvinciaBusquedaHandler;
import com.foo.shared.rpc.results.BuscarProvinciaResult;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Another example presenter.
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 */
public class ProvinciaListaPresenter extends AbstractActivity implements
		IProvinciaBusquedaHandler {

	public interface Display extends IsWidget {

		HasData<Provincia> getTablaProvincia();

		AbstractPager getPaginador();

		HasClickHandlers getBotonBuscar();

		HasText getNombreInput();

		SingleSelectionModel<Provincia> getSelectionModel();

	}

	private final Display vista;
	private final PlaceController placeController;
	private final DispatchAsync dispatch;

	@Inject
	public ProvinciaListaPresenter(Display vista,
			PlaceController placeController, DispatchAsync dispatch) {
		super();
		this.vista = vista;
		this.placeController = placeController;
		this.dispatch = dispatch;
		bind();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//Cuando para la actividad los eventos registrados se quitan Magia :)
		registrarHandlers(eventBus);
		panel.setWidget(vista.asWidget());
	}

	private void registrarHandlers(final EventBus eventBus) {
		eventBus.addHandler(BuscarProvinciaEvent.TYPE, this);
	}

	public void seleccionado(Provincia selected) {
		placeController.goTo(new ProvinciaEditPlace(selected.getId()));
	}

	void bind() {

		// vista.getBotonBuscar().addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// List<Provincia> provinc = findByName(SafeHtmlUtils
		// .htmlEscape(vista.getNombreInput().getText()));
		// vista.getTablaProvincia().setRowCount(provinc.size(), true);
		// vista.getTablaProvincia().setRowData(0, provinc);
		// dispatch.execute(new Sum(2, 5),
		// new AsyncCallback<SumResponse>() {
		// @Override
		// public void onFailure(Throwable e) {
		// Window.alert(e.getMessage());
		// }
		// @Override
		// public void onSuccess(SumResponse result) {
		// Window.alert(result.getResult().toString());
		// }
		// });
		// }
		// });

		final SingleSelectionModel<Provincia> selectionModel = vista
				.getSelectionModel();

		selectionModel.addSelectionChangeHandler(new Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Provincia selected = selectionModel.getSelectedObject();
				if (selected != null) {
					seleccionado(selected);
				}
			}
		});

	}

	@Override
	public void onBuscarProvincia(BuscarProvinciaEvent evento) {

		dispatch.execute(new BuscarProvinciaEvent(evento.getNombre()),
				new DefaultAsyncCallback<BuscarProvinciaResult>() {
					@Override
					public void onSuccess(BuscarProvinciaResult result) {
						ListDataProvider<Provincia> p = new ListDataProvider<Provincia>(
								result.getProvincias());
						p.addDataDisplay(vista.getTablaProvincia());						
					}
				});
	}

}
