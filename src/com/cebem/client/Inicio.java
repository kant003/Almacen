package com.cebem.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Inicio implements EntryPoint {

//GestionProductos gest = new GestionProductos();
//	Otra ot = new Otra();
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		RootPanel.get("contenedor").add(GestionProductos.getInstance()); 
	}

}
