package com.foo.client.views;


import com.foo.client.presenters.ProvinciaEditPresenter.Display;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>An example view.</p>
 * Pantalla de ejemplo.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
public class ProvinciaEdit extends Composite implements Display {

	private static final Binder binder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ProvinciaEdit> {
	}

	@UiField
	TextBox nombreInput;
	@UiField
	Button guardarButton;
	@UiField
	Button cancelarButton;

	@UiField
	SpanElement mensajes;

	public ProvinciaEdit() {
		super();
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public ValueBoxBase<String> getNombreInput() {
		return nombreInput;
	}

	@Override
	public void limpiarPantalla() {
		mensajes.setInnerText("");
		guardarButton.setEnabled(true);
		nombreInput.setEnabled(true);
		cancelarButton.setText("Cancelar");
	}

	@Override
	public Button getBotonCancelar() {
		return cancelarButton;
	}

	@Override
	public Button getBotonGuardar() {
		return guardarButton;
	}

	@Override
	public SpanElement getMensajes() {
		return mensajes;
	}

	@Override
	public void deshabilitarInputText() {
		nombreInput.setEnabled(false);		
	}

}
