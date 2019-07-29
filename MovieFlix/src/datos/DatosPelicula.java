package datos;

import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Pelicula;
import servicios.ConectarBD;

import com.mysql.cj.xdevapi.Statement;

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
	
	public boolean buscarPelicula(Pelicula pelicula) {
		return false;
	}
	public void altaPelicula(Pelicula pelicula) {
		buscarPelicula(pelicula);
	}
	
	public boolean bajaPelicula(Pelicula pelicula) {
		buscarPelicula(pelicula);
		return false;
	}
	
	public static void obtenerPelicula(Pelicula pelicula) {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		
		//String sql="SELECT * FROM CLIENTE ORDER BY ID";
		
		ArrayList<Pelicula> listaCliente= new ArrayList<Pelicula>();
		
		try {			
			co= ConectarBD.conectarBD("movieflix");
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				Cliente c=new Cliente();
				c.setId(rs.getInt(1));
				c.setCedula(rs.getString(2));
				c.setNombre(rs.getString(3));
				c.setApellido(rs.getString(4));
				listaCliente.add(c);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
	}

}
