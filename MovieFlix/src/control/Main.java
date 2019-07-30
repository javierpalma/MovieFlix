package control;

import java.time.LocalDate;

import beans.Categoria;
import beans.Cliente;
import beans.Pelicula;
import datos.DatosCliente;
import datos.DatosPelicula;
import datos.GenerarInforme;
import servicios.ConectarBD;
import servicios.ServiciosMovieFlix;

public class Main {

	public static void main(String[] args) {
		
		new ServiciosMovieFlix().arrancarAplicacion();

	}
	
	
}
