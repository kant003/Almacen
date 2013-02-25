package com.cebem.server;

import com.cebem.client.ElevarService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ElevarServiceImpl extends RemoteServiceServlet
implements ElevarService{

	@Override
	public Float elevarAlCuadrado(Float num) {
	
		return num*num;
	}

}
