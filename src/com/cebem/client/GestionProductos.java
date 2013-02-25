package com.cebem.client;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dev.ModuleTabPanel.Session;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.event.dom.client.ChangeEvent;

public class GestionProductos extends Composite implements HasText {

	private static GestionProductosUiBinder uiBinder = GWT
			.create(GestionProductosUiBinder.class);
	@UiField(provided=true) CellList<Producto> cellList = new CellList<Producto>(new AbstractCell<Producto>(){
		@Override
		public void render(Context context, Producto value, SafeHtmlBuilder sb) {
			// TODO
			sb.append(SafeHtmlUtils.fromTrustedString(value.getNombre()));
			sb.append(SafeHtmlUtils.fromTrustedString(" "));
			sb.append(SafeHtmlUtils.fromTrustedString(value.getPrecio()+"&euro;"));
			sb.append(SafeHtmlUtils.fromTrustedString("(" + value.getTipo() + ")"));
			
		}
	});
	
	
	private static GestionProductos INSTANCE = new GestionProductos();
	 
	   
    public static GestionProductos getInstance() {
    	 if (INSTANCE == null) { 
             INSTANCE = new GestionProductos();
         }
        return INSTANCE;
    }
	
	ListDataProvider<Producto> dataProvider = new ListDataProvider<Producto>();
	SingleSelectionModel<Producto> singleSelectionModel = new SingleSelectionModel<Producto>();
	
	
	
	@UiField TextBox nombre;
	@UiField ListBox tipo;
	@UiField DoubleBox precio;
	@UiField Button add;
	@UiField Button borrar;
	@UiField Button modificar;
	@UiField Button borrarFormulario;
	@UiField Button button;
	@UiField Button button_1;
	@UiField Button ir;

	interface GestionProductosUiBinder extends
			UiBinder<Widget, GestionProductos> {
	}

	public GestionProductos() {
		initWidget(uiBinder.createAndBindUi(this));
		
		tipo.addItem("Tablet","1");
		tipo.addItem("Ordenador","2");
		tipo.addItem("Impresora","60");
		
		// selecciono el segundo elemento del combo
		tipo.setSelectedIndex(1);
		
		dataProvider.addDataDisplay(cellList);
		cellList.setSelectionModel(singleSelectionModel);
		
		rellenarListaProductos();				
		
		singleSelectionModel.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				Producto p = singleSelectionModel.getSelectedObject();
				Window.alert("Has seleccionado:"+p.getNombre());
				
				nombre.setText( p.getNombre());
				precio.setValue((double) p.getPrecio());
				if(p.getTipo().equals("Tablet")){
					tipo.setSelectedIndex(0);
				}else if(p.getTipo().equals("Ordenador")){
					tipo.setSelectedIndex(1);
				}else{
					tipo.setSelectedIndex(2);
				}
			}
		
		});
		
		
		String n= Cookies.getCookie("n");
		if(n!=null) nombre.setText(n);
	}

	private void rellenarListaProductos() {
		
		BDServiceAsync llamadaAlServidor = GWT.create(BDService.class);
		llamadaAlServidor.listar(new AsyncCallback<ArrayList<Producto>>() {
			
			@Override
			public void onSuccess(ArrayList<Producto> result) {
				// TODO Auto-generated method stub
				dataProvider.setList(result);
				dataProvider.refresh();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("No se puede listar los productos");
				
			}
		});
		
	}

	public GestionProductos(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	public void setText(String text) {
			}

	public String getText() {
		return "";
	}

	@UiHandler("add")
	void onAddClick(ClickEvent event) {
		String n = nombre.getText();
		float p = precio.getValue().floatValue();
		String t = tipo.getItemText(  tipo.getSelectedIndex()  );
		
		Producto prod = new Producto(n,t,p);
		dataProvider.getList().add(prod);
		dataProvider.refresh();
	}
	@UiHandler("borrar")
	void onBorrarClick(ClickEvent event) {
		Producto p = singleSelectionModel.getSelectedObject();
		dataProvider.getList().remove(p);
		dataProvider.refresh();
		
	}
	@UiHandler("modificar")
	void onModificarClick(ClickEvent event) {
		
		String n = nombre.getText();
		float p = precio.getValue().floatValue();
		String t = tipo.getItemText(  tipo.getSelectedIndex()  );
		
		Producto prod = singleSelectionModel.getSelectedObject();
		
		prod.setNombre(n);
		prod.setPrecio(p);
		prod.setTipo(t);
		
		dataProvider.refresh();
		
	}


	@UiHandler("borrarFormulario")
	void onBorrarFormularioClick(ClickEvent event) {
		nombre.setText("");
		precio.setValue(0.0);
		tipo.setSelectedIndex(0);
	}
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		float p = precio.getValue().floatValue();
		float resultado = p*p;
		precio.setValue((double) resultado);
	}
	@UiHandler("button_1")
	void onButton_1Click(ClickEvent event) {
		float numero = precio.getValue().floatValue();
		
		ElevarServiceAsync elevarServicio =
				GWT.create(ElevarService.class);
		elevarServicio.elevarAlCuadrado(numero, new AsyncCallback<Float>()  {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("La comunicacion con el servidor ha fallado");
			}

			@Override
			public void onSuccess(Float result) {
				// TODO Auto-generated method stub
				precio.setValue(result.doubleValue());
			}
		});
	}
	@UiHandler("ir")
	void onIrClick(ClickEvent event) {
		
		RootPanel.get("contenedor").clear();
		RootPanel.get("contenedor").add(Otra.getInstance());
	}
	@UiHandler("nombre")
	void onNombreChange(ChangeEvent event) {
		 Cookies.setCookie("n",nombre.getValue());
	}
}




















