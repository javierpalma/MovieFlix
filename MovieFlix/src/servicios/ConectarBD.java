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
	String user = "movieflix";
	String password = "movieflix";
	
	public ConectarBD() {
		super();
	}

	//el metodo conectarBD devuelve la conexión a la bd
	public Connection conectarBD(String bd) {
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
	
	public void volcarDatos() {
		File f_txt = new File("peliculas_numCat.txt");
		Connection con;
		PreparedStatement pst = null;
		String linea;
		
		try {
			Reader r = new FileReader(f_txt);
			BufferedReader br = new BufferedReader (r);
			linea = br.readLine();
			do{
				String[]datos = linea.split(",");
				con=this.conectarBD("movieflix");
				pst = con.prepareStatement("INSERT INTO pelicula(nombre,anyoEstreno,id_categoria VALUES ("+datos[0]+","+datos[1]+","+datos[2]+")");
				linea = br.readLine();
				
			}while(linea !="FEOF");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}	
		
	}
}
