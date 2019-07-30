package servicios;

import java.util.List;
import java.util.Scanner;

import beans.Categoria;
import beans.Pelicula;
import datos.DatosPelicula;
import datos.GenerarInforme;
import utilidades.Menu;
import utilidades.PedirDatos;

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
		new DatosPelicula().bajaPelicula(PedirDatos.pidePelicula().getNombre());
		
	}

	@Override
	public boolean modificarPelicula(Pelicula pelicula) {
		int id;
		DatosPelicula dp = new DatosPelicula();
		PedirDatos pd = new PedirDatos();
		Pelicula p1 = new Pelicula();
		
		id = dp.obtenerPelicula(p1.getNombre());
		p1.setId(id);
		p1 = pd.pidePelicula();
		
		//actualizar pelicula
		return false;
	}

	@Override
	public List<Pelicula> listar() {
		GenerarInforme f= new GenerarInforme();
		return f.listarPeliculas();
	}

	@Override
	public void arrancarAplicacion() {
		
		Menu.mostrarMenu();
		int opcion=new Scanner(System.in).nextInt();
		
		switch(opcion) {
			case 1: this.altaPelicula();break;
			case 2:	this.bajaPelicula();break;
			case 3: this.modificarPelicula(PedirDatos.pidePelicula());break;
			case 4:	
			case 5:
			case 6:
			case 7:
			case 0:break;s
		}
		
	}
	
	

}
