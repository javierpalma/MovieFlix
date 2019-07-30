package servicios;

import java.util.List;
import java.util.Scanner;

import datos.GenerarInforme;
import beans.Categoria;
import beans.Pelicula;
import datos.DatosPelicula;
import datos.GenerarInforme;
import utilidades.PedirDatos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiciosMovieFlix implements I_ServiciosMovieFlix {

	

	@SuppressWarnings("resource")
	public void altaPelicula() {
		new DatosPelicula().altaPelicula(PedirDatos.pidePelicula());
		
		
		//pedir datos de pelicula a dar de alta
		/*System.out.println("Nombre pelicula: ");
		String nombre=new Scanner(System.in).nextLine();
		System.out.println("Año pelicula: ");
		int anyo=new Scanner(System.in).nextInt();
		System.out.println("Categoria: ");
		String nombreCategoria=new Scanner(System.in).nextLine();
		//pasamos los valores a una nueva instancia de pelicula.
		Pelicula pelicula= new Pelicula();
		Categoria c= new Categoria();
		pelicula.setNombre(nombre);
		pelicula.setAnyoEstreno(anyo);
		c.setNombre(nombreCategoria);
		pelicula.setCategoria(c);
		new DatosPelicula().altaPelicula(pelicula);*/
		
	}	

	@Override
	public void bajaPelicula() {
		new DatosPelicula().bajaPelicula(PedirDatos.pidePelicula());
	}

	@Override
	public boolean modificarPelicula(Pelicula pelicula) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Pelicula> listar() {
		GenerarInforme gi = new GenerarInforme();
		gi.listarPeliculas(DatosPelicula.obtenerPelicula("intocable"));
		
		return null;
	}

	@Override
	public void arrancarAplicacion() {
		// TODO Auto-generated method stub
		
	}
	
	

}
