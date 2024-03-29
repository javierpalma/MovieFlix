package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.Categoria;
import beans.Cliente;
import beans.Pelicula;
import servicios.ConectarBD;

public class GenerarInforme {
	
	/**
	 * @author Jose Miguel
	 * @return
	 */
	//M�todo que muestra la media de valoraci�n de las pel�culas
	public ArrayList<Pelicula> listarPeliculasPorValoracion(){
		
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		Logger logger = LogManager.getLogger(); 
		
		String sql="SELECT AVG(CP.VALORACION) AS media, P.NOMBRE_PELICULA, P.ANYO_ESTRENO, C.nombre FROM CLIENTE_PELICULA AS CP, PELICULA AS P, categoria AS C WHERE p.id_pelicula = cp.id_pelicula AND p.id_categoria = c.id_categoria group by p.id_pelicula HAVING media IS NOT NULL ORDER BY media desc";
		ArrayList<Pelicula> listaPelicula= new ArrayList<Pelicula>();
		
		try {			
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
	
				Pelicula p = new Pelicula();
				Categoria c = new Categoria();
				
				String media = rs.getString(1);
				p.setNombre(rs.getString(2));
				p.setAnyoEstreno(rs.getInt(3));
				c.setNombre(rs.getString(4));
				p.setCategoria(c);
				System.out.println(p.getNombre()+"-"+p.getAnyoEstreno()+"-"+p.getCategoria().getNombre()+"-"+media);			
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase GenerarInforme, m�todo ListarPelicula");
			logger.info(e.getMessage());
		}
		
		return null;
	}
	
	//El m�todo devuelve un listado de la tabla pel�culas donde re recogen solamente 3 campos, nombre, a�o y nombre_categoria
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
			System.out.println("Error: Clase GenerarInforme, m�todo ListarPelicula");
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
		ArrayList<Pelicula> miListaPelicula= new ArrayList<Pelicula>();
		
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
				miListaPelicula.add(p);
				
				
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase GenerarInforme, m�todo ListarPeliculaPorCategor�a");
			logger.info(e.getMessage());
		}
		return miListaPelicula;
	}
	
	public ArrayList<Pelicula> listarPeliculasCliente(Cliente cliente){
		ArrayList<Pelicula> listaPelicula= new ArrayList<Pelicula>();
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		Logger logger = LogManager.getLogger(); 
		
		String sql="SELECT * FROM CLIENTE_PELICULA AS CL, PELICULA AS P, categoria AS C WHERE CL.ID_CLIENTE="+cliente.getIdCliente()+" AND P.ID_CATEGORIA=C.ID_CATEGORIA AND CL.ID_PELICULA=P.ID_PELICULA ORDER BY P.ID_PELICULA;";
		
		
		
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
				cat.setNombre(rs.getString(10));
				p.setCategoria(cat);
				
				listaPelicula.add(p);				
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase GenerarInforme, m�todo ListarPeliculaCliente");
			logger.info(e.getMessage());
			return null;
		}
		
		return listaPelicula;
	}
	
	public ArrayList<Pelicula> ListarPeliculaClienteNoVista(Cliente cliente){
		
		ArrayList<Pelicula> PeliculaNoVista= new ArrayList<Pelicula>();
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		ResultSet rs=null;
		Logger logger = LogManager.getLogger(); 
		
		String sql="SELECT * FROM CLIENTE_PELICULA AS CL, PELICULA AS P, categoria AS C WHERE CL.ID_CLIENTE="+cliente.getIdCliente()+" AND P.ID_CATEGORIA=C.ID_CATEGORIA AND CL.ID_PELICULA=P.ID_PELICULA AND VISTA = FALSE ORDER BY P.ID_PELICULA ";
		
		
		
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
				cat.setNombre(rs.getString(10));
				p.setCategoria(cat);

				PeliculaNoVista.add(p);				
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase GenerarInforme, m�todo ListarPeliculaClienteNoVista");
			logger.info(e.getMessage());
			return null;
		}
		
		return PeliculaNoVista;
	}
	
	public ArrayList<Cliente> listarCliente(){
		ArrayList<Cliente> lista=new ArrayList<>();
		String sql="SELECT * FROM cliente";
		
		try(Connection co=new ConectarBD().conectarBD("movieflix")) {
			Cliente c;
			Statement st= co.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next()) {
				c=new Cliente();
				c.setIdCliente(rs.getInt(1));
				c.setNombreCliente(rs.getString(2));
				c.setFechaNacimiento(rs.getDate(3).toLocalDate());
				c.setCiudad(rs.getString(4));
				lista.add(c);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	
	
	
	
}
