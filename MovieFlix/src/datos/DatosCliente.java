package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

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
		
		String sql="";
		Cliente c = new Cliente();
		
		
		try {			
			co= conect.conectarBD("movieflix") ;
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			
			while (rs.next()) {
				
				c.setIdCliente(rs.getInt);
				c.setNombreCliente(rs.getString());
				
		
				if(c.getNombre().trim().equalsIgnoreCase(nombre)) {
					System.out.println("");
					return c.getNmbre();
				}
				else {
					flag= -1;
				}
			}
			stm.close();
			rs.close();
			co.close();
			
		} catch (SQLException e) {
			System.out.println("Error: Clase DatosCliente, método obtenerCliente");
			e.printStackTrace();
			Logger lgr = Logger.getLogger(nombre);
            lgr.log(Level.INFO, "FALLO EN PARÁMETRO NOMBRE, MÉTODO obtenerCliente");
		}
		
		return c.getNombre();
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
			System.out.println("Error: Clase DatosPelicula, método actualizar");
			e.printStackTrace();
		}
		
		return actualizar;
	}
	

}
