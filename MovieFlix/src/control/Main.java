package control;

<<<<<<< HEAD
<<<<<<< HEAD
import servicios.I_ServiciosMovieFlix;
import servicios.ServiciosMovieFlix;

/**
 * 
 * @author Asiel
 *
 */

//Clase Main que invoca al método arrancarAplicacion de la clase ServiciosMovieFlix.
=======
import servicios.ConectarBD;
>>>>>>> 842fcc14e14054b8ed93663f0e43bde0e178249f
=======
import servicios.ConectarBD;
>>>>>>> 63d19d2dc0f7ebf6a7efbe3ff486886763341394

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ServiciosMovieFlix.arrancarAplicacion();
		ConectarBD con = new ConectarBD();
		con.volcarDatos();
	}

}
