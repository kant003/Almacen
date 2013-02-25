package com.cebem.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BDServiceAsync {
	void add(Producto p, AsyncCallback<Void> callback);
	void listar(AsyncCallback< ArrayList<Producto> > callback);
	void modificar(Producto p, AsyncCallback<Void> callback);
	void eliminar(Integer id, AsyncCallback<Void> callback);
}