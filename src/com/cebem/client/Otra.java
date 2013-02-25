package com.cebem.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Otra extends Composite implements HasText {

	private static OtraUiBinder uiBinder = GWT.create(OtraUiBinder.class);

	interface OtraUiBinder extends UiBinder<Widget, Otra> {
	}

	public Otra() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private static Otra INSTANCE = new Otra();
	 
   
    public static Otra getInstance() {
    	 if (INSTANCE == null) { 
             INSTANCE = new Otra();
         }
        return INSTANCE;
    }
	

	
	@UiField
	Button button;
	@UiField Button button_1;

	public Otra(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

	@UiHandler("button_1")
	void onButton_1Click(ClickEvent event) {
		RootPanel.get("contenedor").clear();
		RootPanel.get("contenedor").add(GestionProductos.getInstance());
	}
}
