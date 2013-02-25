package com.cebem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ElevarServiceAsync {
	void elevarAlCuadrado(Float num, AsyncCallback<Float> callback);
	/*void logout(AsyncCallback<Boolean> callback);
	void add(String user, AsyncCallback<Void> callback);*/
}