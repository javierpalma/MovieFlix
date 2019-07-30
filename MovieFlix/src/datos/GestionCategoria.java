package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Categoria;
import servicios.ConectarBD;

public class GestionCategoria {
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "GestionCategoria [categoria=" + categoria + "]";
	}

	public GestionCategoria() {
		super();
	}

	public GestionCategoria(Categoria categoria) {
		super();
		this.categoria = categoria;
	}
	
	public int obtenerIdCategoria(String nombre) {
		Connection co=null;
		ConectarBD con=new ConectarBD();
		Statement stm = null;
		ResultSet rs= null;
		String sql= "SELECT id_categoria, nombre FROM categoria WHERE nombre='"+nombre+"';";
		try {
			co=con.conectarBD("movieflix");
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
		
}

