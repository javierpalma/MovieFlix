package servicios;

import java.util.List;
import java.util.Scanner;

import beans.Categoria;
import beans.Pelicula;
import datos.DatosPelicula;
import utilidades.PedirDatos;

public class ServiciosMovieFlix implements I_ServiciosMovieFlix {

	

	@SuppressWarnings("resource")
	public void altaPelicula() {
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
		new DatosPelicula().altaPelicula(PedirDatos.pidePelicula());
	}	

	@Override
	public boolean bajaPelicula() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarPelicula(Pelicula pelicula) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Pelicula> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void arrancarAplicacion() {
		// TODO Auto-generated method stub
		
	}
	
	

}
