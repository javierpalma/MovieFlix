package datos;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import beans.Pelicula;
import servicios.ConectarBD;
import beans.Categoria;


import beans.Pelicula;

/**
 * 
 * @author Vladimir Juan
 *	
 * @version 1.0
 */

public class DatosPelicula {
	
	//Atributos de la clase
	private Pelicula pelicula;
	
	public DatosPelicula() {
		
	}
	/** 
	 * @param pelicula
	 */
	public DatosPelicula(Pelicula pelicula) {
		this.pelicula=pelicula;
	}
	/**
	 * 
	 * @return pelicula
	 */
	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	public String toString() {
		return this.pelicula.toString();
	}
	//metodos principales
	
	public void altaPelicula(Pelicula pelicula) {
		if(DatosPelicula.obtenerPelicula(pelicula)==true) {
			System.out.println("No se puede dar de alta, ya existe");
		}
		else {
			
			try {
				Connection co= new ConectarBD().conectarBD("MovieFlix");
				PreparedStatement st= co.prepareStatement("INSERT INTO pelicula (nombre_pelicula,anyo_estreno,id_categoria) VALUES ('"+pelicula.getId()+"',"+pelicula.getNombre()+","+pelicula.getAnyoEstreno());
				st.executeUpdate();
				System.out.println("Se dio de alta.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean bajaPelicula(Pelicula pelicula) {
		obtenerPelicula(pelicula);
		return false;
	}
	
	public static Boolean obtenerPelicula(Pelicula pelicula) {
		Boolean flag = false;
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		
		//String sql="SELECT * FROM CLIENTE ORDER BY ID";
		//String sql2 ="SELECT 
		
		ArrayList<Pelicula> listaPelicula= new ArrayList<Pelicula>();
		Pelicula p = new Pelicula();
		Categoria c = new Categoria();
		
		try {			
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setAnyoEstreno(rs.getInt(3));
				c.setId(rs.getInt(4));
				c.setNombre(rs.getString(5));
				p.setCategoria(c);
				listaPelicula.add(p);
				
				if(pelicula == p) {
					System.out.println("La Película " + pelicula + " está disponible");
					flag = true;
				}
				else {
					System.out.println("La Película " + pelicula + " no está disponible");
					flag = false;
				}
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase DatosPelicula, método obtenerPelicula");
			e.printStackTrace();
		}
		
		return flag;
	}

}
