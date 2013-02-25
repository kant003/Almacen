package com.cebem.client;

import java.io.Serializable;

public class Producto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1727115027788134312L;

	private int id;
	private String nombre;
	private String tipo;
	private float precio;
	
	
	public Producto() {
		super();
	}


	public Producto(String nombre, String tipo, float precio) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
	}
		
	
	public Producto(int id, String nombre, String tipo, float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", tipo=" + tipo + ", precio="
				+ precio + "]";
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	

}
