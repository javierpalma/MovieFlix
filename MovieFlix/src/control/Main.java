package control;


import servicios.I_ServiciosMovieFlix;
import servicios.ServiciosMovieFlix;

/**
 * 
 * @author Asiel
 *
 */

//Clase Main que invoca al método arrancarAplicacion de la clase ServiciosMovieFlix.

import servicios.ConectarBD;




public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ServiciosMovieFlix.arrancarAplicacion();
		ConectarBD con = new ConectarBD();
		con.volcarDatos();
	}

}
