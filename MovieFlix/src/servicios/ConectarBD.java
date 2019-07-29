package servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*
* @author Javier Palma
*/

public class ConectarBD {
	
	private String bd;
	String url = "jdbc:mysql://localhost:3306/";
	String user = "movieflix";
	String password = "movieflix";
	
	
	//el metodo conectarBD devuelve la conexión a la bd
	public Connection  conectarBD(String bd) {
		Connection con = null;
		this.bd = url+bd;
		try {
			con = DriverManager.getConnection(this.bd, user, password);
			return con;
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	} 
}
