package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.Categoria;
import beans.Cliente;
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
	
	public ArrayList<Pelicula> listarPeliculaPorCategoria(Categoria categoria){
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		Logger logger = LogManager.getLogger(); 
		
		String sql="SELECT P.ID_PELICULA, P.nombre_pelicula, p.anyo_estreno, p.id_categoria, c.nombre FROM PELICULA AS P, CATEGORIA AS C WHERE P.ID_CATEGORIA = C.ID_CATEGORIA AND P.id_categoria="+categoria.getId()+";";
		ArrayList<Pelicula> listaPelicula= new ArrayList<Pelicula>();
		
		try {			
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
	
				Pelicula p = new Pelicula();
				Categoria c = new Categoria();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setAnyoEstreno(rs.getInt(3));
				c.setId(rs.getInt(4));
				c.setNombre(rs.getString(5));
				p.setCategoria(c);
				listaPelicula.add(p);
				//System.out.println(p);
				
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase GenerarInforme, método ListarPeliculaPorCategoría");
			logger.info(e.getMessage());
		}
		
		return listaPelicula;
	}
	
	public ArrayList<Pelicula> listarPeliculasCliente(Cliente cliente){
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		Logger logger = LogManager.getLogger(); 
		
		String sql="SELECT * FROM CLIENTE_PELICULA, PELICULA, categoria WHERE ID_CLIENTE ="+cliente.getIdCliente()+" AND  cliente_pelicula.id_pelicula = pelicula.id_pelicula AND pelicula.id_categoria = categoria.nombre;";
		
		ArrayList<Pelicula> listaPelicula= new ArrayList<Pelicula>();
		
		try {			
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
	
				Cliente c = new Cliente();
				Pelicula p = new Pelicula();
				Categoria cat = new Categoria();
				
				c.setIdCliente(rs.getInt(1));
				p.setId(rs.getInt(2));
				p.setNombre(rs.getString(6));
				p.setAnyoEstreno(rs.getInt(7));
				cat.setId(rs.getInt(9));
				cat.setNombre(rs.getString(11));
				
				
				listaPelicula.add(p);
				//System.out.println(p);
				
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase GenerarInforme, método ListarPeliculaCliente");
			logger.info(e.getMessage());
		}
		
		return listaPelicula;
	}
	
	
	
	
	
	
	
}
