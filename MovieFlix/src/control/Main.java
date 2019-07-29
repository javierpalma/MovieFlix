package control;

import servicios.ConectarBD;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ServiciosMovieFlix.arrancarAplicacion();
		ConectarBD con = new ConectarBD();
		con.volcarDatos();
	}

}
