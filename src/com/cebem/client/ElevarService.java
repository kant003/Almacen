package com.cebem.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("elevar")
public interface ElevarService extends RemoteService {
	Float elevarAlCuadrado(Float num);
	/*Boolean logout();
	Void add(String user);*/
}