package datos;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import org.apache.logging.log4j.Level;
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
	 * @return
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
        
        String sql="SELECT * FROM CLIENTE WHERE NOMBRE CLIENTE=NOMBRE";
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
	

	public void asignarCategoriaCliente(Cliente cliente, Categoria categoria) {
		
		
		Connection co=null;
		ConectarBD conect=new ConectarBD();
		Logger logger=LogManager.getLogger();
		ArrayList<Pelicula> lista= new ArrayList();
		lista = new GenerarInforme().listarPeliculaPorCategoria(categoria);
		//String sql="INSERT INTO CLIENTE_PELICULA (ID_CLIENTE, ID_PELICULA, VISTA, VALORACION) VALUES ('"+cliente.getId()+"', "+pelicula.getId()+", false, NULL);";
		
		try {
			co=conect.conectarBD("movieFlix");
			java.sql.Statement stm=co.createStatement();
	
			for(Pelicula pelicula : lista) {
				String sql="INSERT INTO CLIENTE_PELICULA (ID_CLIENTE, ID_PELICULA, VISTA, VALORACION) VALUES ('"+cliente.getIdCliente()+"', "+pelicula.getId()+", false, NULL);";
				stm.executeQuery(sql);
			}
		}catch(SQLException e) {
			logger.info("Error: clase DatosClientes, metodo asignarCategoria");
		}
	}
	
	public void asignarCatalogoCliente(Cliente cliente, Categoria categoria) {
		Connection co=null;
		ConectarBD conect= new ConectarBD();
		Logger logger= LogManager.getLogger();
		
		String sql="INSERT INTO cliente_categoria VALUES ("+cliente.getIdCliente() + ","+categoria.getId()+")" ;
		
		try {
			co=conect.conectarBD("movieFlix");
			java.sql.Statement stm=co.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			
			asignarCategoriaCliente(cliente, categoria);
			
		}catch(SQLException e) {
			logger.info("No se puede insertar");
		}
		
	}

}
