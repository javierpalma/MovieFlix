package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.Categoria;
import beans.Cliente;
import beans.Pelicula;
import servicios.ConectarBD;

/**
 * 
 * @author Asiel
 *	
 * @version 1.0
 */

public class DatosCliente {
	
	//Atributos de la clase
	
	private Cliente cliente;

	public DatosCliente() {
		super();
	}
	
	/**
	 * 
	 * @param cliente
	 */

	public DatosCliente(Cliente cliente) {
		super();
		this.cliente = cliente;
	}
	
	/**
	 * 
	 * @return cliente
	 */

	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * 
	 * @param cliente
	 */

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "DatosCliente [cliente=" + cliente + "]";
	}
	
	public Cliente obtenerCliente(String nombre) {
        int flag = -1;
        Connection co =null;
        ConectarBD conect = new ConectarBD();
        java.sql.Statement stm= null;
        ResultSet rs=null;
        
        String sql="SELECT * FROM CLIENTE WHERE NOMBRE_CLIENTE='"+nombre+"';";
        Cliente c = new Cliente();
        
        
        try {            
            co= conect.conectarBD("movieflix") ;
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            
            while (rs.next()) {
                
                c.setIdCliente(rs.getInt(1));
                c.setNombreCliente(rs.getString(2));
                LocalDate fecha = rs.getDate(3).toLocalDate();
                c.setFechaNacimiento(fecha);
                c.setCiudad(rs.getString(4));
                
        
                if(c.getNombreCliente().trim().equalsIgnoreCase(nombre)) {
                    System.out.println("El cliente existe");
                    return c;
                }
                
            }
            stm.close();
            rs.close();
            co.close();
            
        } catch (SQLException e) {
            System.out.println("Error: Clase DatosCliente, método obtenerCliente");
            e.printStackTrace();
           // Logger lgr = Logger.getLogger(nombre);
           //lgr.log(Level.INFO, "FALLO EN PARÁMETRO NOMBRE, MÉTODO obtenerCliente");
        }
        
        return null;
    }
	
	/**
	 * @author Jose Miguel	
	 * @param cliente
	 * @return
	 */
	//Método que modifica la tabla clientes por medio de un objeto como parámetro en base a su ID
	public Boolean modificaCliente(Cliente cliente) {
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		Logger logger = LogManager.getLogger(); 
		int rs;		
	

		boolean actualizar=false;
				
		String sql="UPDATE CLIENTE SET NOMBRE_CLIENTE ='"+ cliente.getNombreCliente()+"', FECHA_NACIMIENTO ='"+ cliente.getFechaNacimiento()+"', CIUDAD ='"+ cliente.getCiudad()+"'" +" WHERE ID_CLIENTE ="+cliente.getIdCliente();
		System.out.println(sql); 
		try {
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs= stm.executeUpdate(sql);
			
			actualizar=true;
			
		} catch (SQLException e) {
			System.out.println("Error: Clase DatosCLientes, método modificaCliente");
			logger.info(e.getMessage());
		}
		
		return actualizar;
	}
	
	/**
	 * @author Vladimir Juan
	 * @param cliente
	 * @param categoria
	 */
	public void asignarCategoriaCliente(Cliente cliente, Categoria categoria) {
		
		
		Connection co=null;
		ConectarBD conect=new ConectarBD();
		Logger logger=LogManager.getLogger();
		ArrayList<Pelicula> lista= new ArrayList<>();
		lista = new GenerarInforme().listarPeliculaPorCategoria(categoria);
		//String sql="INSERT INTO CLIENTE_PELICULA (ID_CLIENTE, ID_PELICULA, VISTA, VALORACION) VALUES ('"+cliente.getId()+"', "+pelicula.getId()+", false, NULL);";
		
		try {
			co=conect.conectarBD("movieFlix");
			java.sql.Statement stm=co.createStatement();
	
			for(Pelicula pelicula : lista) {
				String sql="INSERT INTO CLIENTE_PELICULA (ID_CLIENTE, ID_PELICULA, VISTA, VALORACION) VALUES ("+cliente.getIdCliente()+", "+pelicula.getId()+", false, NULL);";
				stm.executeUpdate(sql);
			}
		}catch(SQLException e) {
			logger.info("Error: clase DatosClientes, metodo asignarCategoria");
		}
	}
	/**
	 * @author Vladimir Juan
	 * @param cliente
	 * @param categoria
	 */
	public void asignarCatalogoCliente(Cliente cliente, Categoria categoria) {
		Connection co=null;
		ConectarBD conect= new ConectarBD();
		Logger logger= LogManager.getLogger();
		String sql="INSERT INTO cliente_categoria VALUES ("+cliente.getIdCliente() + ","+categoria.getId()+")" ;
		try {
			co=conect.conectarBD("movieflix");
			java.sql.Statement stm=co.createStatement();
			int rs= stm.executeUpdate(sql);
			asignarCategoriaCliente(cliente, categoria);
			
		}catch(SQLException e) {
			logger.info("No se puede insertar "+e.getMessage());
		}
		
	}
	/**
	 * Damos de alta un cliente
	 * @param cliente
	 */
	public void altaCliente(Cliente cliente) {
		
		/*
		 * Connection co = null;
		 * ConectarBD con= new ConectarBD();
		 * co=con.conectarBD("movieflix");
		 * 
		 * Usamos mejor el try with resources
		 * 
		 */
		
			try(Connection co=new ConectarBD().conectarBD("movieflix")) {
				
				PreparedStatement pt= co.prepareStatement("INSERT INTO cliente (nombre_cliente,fecha_nacimiento,ciudad) VALUES ( '"+cliente.getNombreCliente()+"','"+cliente.getFechaNacimiento()+"','"+cliente.getCiudad()+"');");
				System.out.println(pt);
				pt.executeUpdate();
				cliente = obtenerCliente(cliente.getNombreCliente());
				for(int i=1; i<7; i++) {
					Categoria categoria = new Categoria();
					categoria.setId(i);
					this.asignarCatalogoCliente(cliente, categoria);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}
	/**
	 * Damos de baja un usuario
	 * @param nombre
	 */
	public void bajaCliente(Cliente cliente) {
			
			try(Connection co=new ConectarBD().conectarBD("movieflix")) {
				String sql="DELETE FROM CLIENTE WHERE ID_CLIENTE="+cliente.getIdCliente()+";";
				PreparedStatement pt= co.prepareStatement(sql);
				pt.executeUpdate();
				System.out.println("Borrado usuario con exito.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
	}
	
	/**
	 * @author Jose Miguel
	 * @param cliente
	 * @param pelicula
	 */
	public void verPelicula(Cliente cliente, Pelicula pelicula) {
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		Logger logger = LogManager.getLogger(); 
		int rs;		
	
		
		boolean actualizar=false;
				
		String sql="UPDATE CLIENTE_PELICULA SET CLIENTE_PELICULA.VISTA = TRUE WHERE ID_CLIENTE = "+cliente.getIdCliente()+" AND ID_PELICULA = "+pelicula.getId()+";";
		System.out.println(sql); 
		try {
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs= stm.executeUpdate(sql);
			
			actualizar=true;
			
		} catch (SQLException e) {
			System.out.println("Error: Clase DatosCLientes, método verPelicula");
			logger.info(e.getMessage());
		}
		
		
	}
	
	/**
	 * @author Jose Miguel
	 * @param cliente
	 * @param pelicula
	 */
	public void valorarPelicula(Cliente cliente, Pelicula pelicula, int valoracion) {
		
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
		Logger logger = LogManager.getLogger(); 
		int rs;		
	
		
		boolean actualizar=false;
				
		String sql="UPDATE CLIENTE_PELICULA SET CLIENTE_PELICULA.VALORACION = " + valoracion + " WHERE ID_CLIENTE = "+cliente.getIdCliente()+" AND ID_PELICULA = "+pelicula.getId()+";";
		System.out.println(sql); 
		try {
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs= stm.executeUpdate(sql);
			
			actualizar=true;
			
		} catch (SQLException e) {
			System.out.println("Error: Clase DatosCLientes, método valorarPelicula");
			logger.info(e.getMessage());
		}
		
	}
	
	
}
