package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
			        Logger logger = LogManager.getLogger(); 
			        
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
			        	
			            logger.info(e.getMessage());
			        }
			        
			        return null;
			    }
	
	public Boolean modificaCliente(Cliente cliente) {
		Connection co =null;
		ConectarBD conect = new ConectarBD();
		java.sql.Statement stm= null;
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
			System.out.println("Error: Clase DatosPelicula, m�todo actualizar");
			e.printStackTrace();
		}
		
		return actualizar;
	}
	

}
