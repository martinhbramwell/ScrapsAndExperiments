package com.foo.client.views;


import java.util.ArrayList;

import javax.inject.Inject;

import com.foo.client.presenters.ProvinciaListaPresenter.Display;
import com.foo.shared.domain.Provincia;
import com.foo.shared.rpc.events.BuscarProvinciaEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

public class ProvinciaLista extends Composite implements Display {

	private static final Binder binder = GWT.create(Binder.class);

	@UiField(provided = true)
	CellTable<Provincia> provincias = new CellTable<Provincia>();

	@UiField(provided = true)
	SimplePager paginador = new SimplePager();

	@UiField
	Button botonBuscar;

	@UiField(provided = true)
	TextBox nombreInput;

	SingleSelectionModel<Provincia> singleSelectionModel;

	public interface Binder extends UiBinder<Widget, ProvinciaLista> {
	}

	private final EventBus eventBus;

	@Inject
	public ProvinciaLista(EventBus eventBus) {
		super();
		this.eventBus = eventBus;

		crearTabla();
		nombreInput = new TextBox();
		nombreInput.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER)
					botonBuscar.click();
			}
		});

		initWidget(binder.createAndBindUi(this));

	}

	private void crearTabla() {
		TextColumn<Provincia> nombre = new TextColumn<Provincia>() {

			@Override
			public String getValue(Provincia p) {
				return p.getNombre();
			}
		};
		TextColumn<Provincia> clave = new TextColumn<Provincia>() {

			@Override
			public String getValue(Provincia p) {
				return p.getId().toString();
			}
		};
		provincias.addColumn(clave, "Id");
		provincias.addColumn(nombre, "Nombre");

		ListDataProvider<Provincia> p = new ListDataProvider<Provincia>(
				new ArrayList<Provincia>());

		p.addDataDisplay(provincias);

		paginador.setDisplay(provincias);
		
		// Add a selection model to handle user selection.
		singleSelectionModel = new SingleSelectionModel<Provincia>();

		provincias.setSelectionModel(singleSelectionModel);
		// selectionModel
		// .addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		// public void onSelectionChange(SelectionChangeEvent event) {
		// Provincia selected = selectionModel.getSelectedObject();
		// if (selected != null) {
		// presenter.seleccionado(selected);
		// }
		// }
		// });

	}

	@UiHandler("botonBuscar")
	void onBuscarPresionado(ClickEvent e) {
		// List<Provincia> provinc = presenter.findByName(SafeHtmlUtils
		// .htmlEscape(nombreInput.getText()));
		// provincias.setRowCount(provinc.size(), true);
		// provincias.setRowData(0, provinc);
		eventBus.fireEvent(new BuscarProvinciaEvent(SafeHtmlUtils
				.htmlEscape(nombreInput.getText())));
	}

	@Override
	public HasData<Provincia> getTablaProvincia() {
		return provincias;
	}

	public AbstractPager getPaginador() {
		return paginador;
	}

	@Override
	public HasClickHandlers getBotonBuscar() {
		return botonBuscar;
	}

	@Override
	public HasText getNombreInput() {
		return nombreInput;
	}

	@Override
	public SingleSelectionModel<Provincia> getSelectionModel() {
		return singleSelectionModel;
	}

}
