package control;

import beans.Categoria;
import beans.Pelicula;
import datos.DatosPelicula;
import servicios.ConectarBD;
import servicios.ServiciosMovieFlix;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ServiciosMovieFlix.arrancarAplicacion();
		//ConectarBD con = new ConectarBD();
		//con.volcarDatos();
		Categoria c1 = new Categoria(2, "romantica");
		Pelicula p1 = new Pelicula(1,"", 1997, c1);
		DatosPelicula.obtenerPelicula("Intocable");
		ServiciosMovieFlix sm = new ServiciosMovieFlix();
		sm.altaPelicula();
	}
	
	
}
