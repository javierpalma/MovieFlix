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
			System.out.println("No se puede a�adir, ya se a�adio");
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
	
	public boolean bajaPelicula(Pelicula pelicula) {
		
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		int id=-1;
		String sql="SELECT * FROM PELICULA WHERE ID_PELICULA=";
		boolean baja = false;
		
		if((id=obtenerPelicula(pelicula.getNombre()))!=-1) {
			
			try {
				co= conect.conectarBD("movieflix") ;
				stm=co.createStatement();
				rs=stm.executeQuery(sql+id);
				baja= true;
				
			} catch (SQLException e) {
				
				System.out.println("Error: Clase DatosPelicula, m�todo bajaPelicula");
				e.printStackTrace();
				Logger lgr = Logger.getLogger(pelicula.getNombre());
	            lgr.log(Level.INFO, "FALLO EN PAR�METRO NOMBRE, M�TODO bajaPelicula");
			}
			
		}
		return baja;
	}

	//M�todo para ver si la pel�cula existe para ver si damos el alta o no, devuelve el boolean para comprobar su existencia
	//M�todo modificado para que retorne el id de la clase pel�cula para usarlo en otros m�todos
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
					System.out.println("La pel�cula est� disponible");
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
			System.out.println("Error: Clase DatosPelicula, m�todo obtenerPelicula");
			e.printStackTrace();
			Logger lgr = Logger.getLogger(nombre);
            lgr.log(Level.INFO, "FALLO EN PAR�METRO NOMBRE, M�TODO obtenerPelicula");
		}
		
		return p.getId();
	}
	
	public static Boolean modificaPelicula(Pelicula pelicula) {
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;		
	

		boolean actualizar=false;
				
		String sql="UPDATE PELICULA SET NOMBRE_PELICULA ='"+ pelicula.getNombre()+"', ANYO_ESTRENO ='"+ pelicula.getAnyoEstreno()+"', ID_CATEGORIA ='"+ pelicula.getCategoria()+"'" +" WHERE ID_PELICULA ="+pelicula.getId();
		try {
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			
			actualizar=true;
			
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, m�todo actualizar");
			e.printStackTrace();
		}
		
		return actualizar;
	}

}
