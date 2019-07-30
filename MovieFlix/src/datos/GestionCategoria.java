package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		co=con.conectarBD("MovieFlix");
		try {
			PreparedStatement pt= co.prepareStatement("SELECT id_categoria, nombre FROM categoria WHERE nombre='"+nombre+"';");
			ResultSet rs = pt.executeQuery();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
		
}

