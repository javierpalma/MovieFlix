package datos;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

import beans.Cliente;
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
		
		return c.getString();
	}
	
	

}
