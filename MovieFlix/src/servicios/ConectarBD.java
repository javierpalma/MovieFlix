package servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;

/**
*
* @author Javier Palma
*/

public class ConectarBD {
	
	private String bd;
	String url = "jdbc:mysql://10.90.36.103:3306/";
	String dataZone= "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String user = "movieflix";
	String password = "movieflix";
	
	public ConectarBD() {
		super();
	}

	//el metodo conectarBD devuelve la conexión a la bd
	public Connection conectarBD(String bd) {
		Connection con = null;
		this.bd = url+bd+this.dataZone;
		try {
			con = DriverManager.getConnection(this.bd, user, password);
			return con;
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	} 
	
	public void volcarDatos() {
		File f_txt = new File("peliculas_numCat.txt");
		Connection con;
		PreparedStatement pst = null;
		ResultSet rs= null;
		String linea;
		
		try {
			Reader r = new FileReader(f_txt);
			BufferedReader br = new BufferedReader (r);
			linea = br.readLine();
			con=this.conectarBD("movieflix");
			System.out.println("Conectado a BD");
			System.out.println(linea);
			do{
				String[]datos = linea.split(",");
				//System.out.println(datos[0]);
				pst = con.prepareStatement("INSERT INTO pelicula (nombre_pelicula,anyo_estreno,id_categoria) VALUES ('"+datos[0]+"',"+datos[1]+","+datos[2]+");");
				//System.out.println(pst);
				pst.executeUpdate();
				//System.out.println(pst.executeUpdate());
				linea = br.readLine();
				
			}while(linea !="FEOF");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}	
		
	}
}
