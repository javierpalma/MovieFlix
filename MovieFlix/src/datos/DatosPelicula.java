package datos;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import beans.Pelicula;
import control.E05_select;
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
	
	/**
	 * 
	 * @param pelicula
	 */

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	public String toString() {
		return this.pelicula.toString();
	}
	
	//metodos principales
	
	
	/**
	 * 
	 * @param pelicula
	 * Damos de alta una pelicula
	 */
	//revisar
	public void altaPelicula(Pelicula pelicula) {
		if(obtenerPelicula()==true) {
			System.out.println("No se puede añadir, ya se añadio");
		}
		else {
			Connection co=null;
			ConectarBD con=new ConectarBD();
			co=con.conectarBD("MovieFlix");
			try {
				PreparedStatement pt= co.prepareStatement("INSERT INTO pelicula (nombre_pelicula,anyo_estreno,id_categoria) VALUES ( pelicula.getID()+","+pelicula.getNombre()+","+pelicula.getAnyoEstreno()+)");
				pt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 
	 * @param pelicula
	 * @return
	 */
	
	public boolean bajaPelicula(String nombre) {
		obtenerPelicula(nombre);
		return false;
	}

	/**
	 * 
	 * @param pelicula
	 */

	//Método para ver si la película existe para ver si damos el alta o no, devuelve el boolean para comprobar su existencia
	//Método modificado para que retorne el id de la clase película para usarlo en otros métodos
	/**
	 * @author Jose Miguel
	 * @param pelicula
	 * @return
	 */
	public static int obtenerPelicula(String nombre) {
		Boolean flag = false;
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		
		String sql="SELECT PELICULA.ID_PELICULA, PELICULA.NOMBRE_PELICULA, PELICULA.ANYO_ESTRENO, CATEGORIA.ID_CATEGORIA, CATEGORIA.NOMBRE FROM PELICULA, CATEGORIA WHERE PELICULA.ID_CATEGORIA = CATEGORIA.ID_CATEGORIA ORDER BY PELICULA.ID_PELICULA";

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
				//listaPelicula.add(p);
				//System.out.println(p.getNombre().toLowerCase()==pelicula.getNombre().toLowerCase());
				//System.out.println(nom.compareTo(nombre));
				
				if(p.getNombre().trim().equalsIgnoreCase(nombre)) {
					System.out.println("La película está disponible");
					return p.getId();
				}
				else {
					return -1;
				}
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase DatosPelicula, método obtenerPelicula");
			e.printStackTrace();
			Logger lgr = Logger.getLogger(nombre);
            lgr.log(Level.INFO, "FALLO EN PARÁMETRO NOMBRE, MÉTODO obtenerPelicula");
		}
		
		return p.getId();
	}

}
