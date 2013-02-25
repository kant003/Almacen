package com.cebem.server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cebem.client.BDService;
import com.cebem.client.Producto;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class BDServiceImpl extends RemoteServiceServlet
implements BDService{

	private static final long serialVersionUID = 8410286604639346047L;

	private Connection abrirBD(){
		String url ="jdbc:mysql://localhost:3306/productos";
		String usuario ="root";
		String clave="toor";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = 
					DriverManager.getConnection(url,usuario,clave);
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:"+e);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error:"+e);
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("error:"+e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("error:"+e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	private void cerrarBD(Connection con){
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Void add(Producto p) {
		Connection con = abrirBD();
		try {
			PreparedStatement pst = 
					con.prepareStatement("INSERT INTO producto (nombre,precio,tipo) VALUES (?,?,?)");
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getPrecio());
			pst.setString(3, p.getTipo());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cerrarBD(con);
		}
		
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Producto> listar() {
		ArrayList<Producto> listado = new ArrayList<Producto>();
		/*listado.add(new Producto(1,"uno", "ipad",3));
		listado.add(new Producto(2,"dos", "ipad",55));
		listado.add(new Producto(3,"tres", "samsung",6));*/
		Connection con = abrirBD();
		try {
			PreparedStatement pst = 
					con.prepareStatement("SELECT * FROM producto");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				float precio = rs.getFloat("precio");
				String tipo = rs.getString("tipo");
				
				Producto p = new Producto(id,nombre,tipo,precio);
				listado.add(p);
				
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cerrarBD(con);
		}
		
		return listado;

	}

	@Override
	public Void modificar(Producto p) {
		Connection con = abrirBD();
		try {
			PreparedStatement pst = 
					con.prepareStatement("UPDATE producto SET nombre=?, precio=?, tipo=? WHERE id=?");
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getPrecio());
			pst.setString(3, p.getTipo());
			pst.setInt(4, p.getId());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cerrarBD(con);
		}
		
		return null;
	}

	@Override
	public Void eliminar(Integer id) {
		Connection con = abrirBD();
		try {
			PreparedStatement pst = 
					con.prepareStatement("DELETE producto WHERE id=?");
			pst.setInt(1, id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cerrarBD(con);
		}
		
		return null;
	}

	
}
