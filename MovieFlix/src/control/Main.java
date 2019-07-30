package control;

import beans.Categoria;
import beans.Pelicula;
import datos.DatosPelicula;
import datos.GenerarInforme;
import servicios.ConectarBD;
import servicios.ServiciosMovieFlix;

public class Main {

	public static void main(String[] args) {
		
		new ServiciosMovieFlix().arrancarAplicacion();
		
		
	}
	
	
}
