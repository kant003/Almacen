package com.cebem.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("bd")
public interface BDService extends RemoteService {
	Void add(Producto p);
	ArrayList<Producto> listar();
	Void modificar(Producto p);
	Void eliminar(Integer id);
	
	
}