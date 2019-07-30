package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.Categoria;
import beans.Pelicula;
import servicios.ConectarBD;

public class GenerarInforme {
	
	//El método devuelve un listado de la tabla películas donde re recogen solamente 3 campos, nombre, año y nombre_categoria
	/**
	 * @author Jose Miguel
	 * @return list
	 */
	public ArrayList<Pelicula> listarPeliculas(){
		
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		Logger logger = LogManager.getLogger(); 
		
		String sql="SELECT PELICULA.NOMBRE_PELICULA, PELICULA.ANYO_ESTRENO, CATEGORIA.NOMBRE FROM PELICULA, CATEGORIA WHERE PELICULA.ID_CATEGORIA = CATEGORIA.ID_CATEGORIA;";
		ArrayList<Pelicula> listaPelicula= new ArrayList<Pelicula>();
		
		try {			
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
	
				Pelicula p = new Pelicula();
				Categoria c = new Categoria();
				p.setNombre(rs.getString(1));
				p.setAnyoEstreno(rs.getInt(2));
				c.setNombre(rs.getString(3));
				p.setCategoria(c);
				listaPelicula.add(p);
				//System.out.println(p);
				
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase GenerarInforme, método ListarPelicula");
			logger.info(e.getMessage());
		}
		
		return listaPelicula;

	}
	
	
	
	
	
}
