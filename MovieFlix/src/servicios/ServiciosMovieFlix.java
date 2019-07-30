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

	
	//pedir datos de pelicula a dar de alta
	@SuppressWarnings("resource")
	public void altaPelicula() {
		new DatosPelicula().altaPelicula(PedirDatos.pidePelicula());
	}	

	@Override
	public void bajaPelicula() {
		new DatosPelicula().bajaPelicula(PedirDatos.pidePelicula().getNombre());
		
	}

	/**
	 * @author Jose Miguel
	 */
	//Método que recoge por teclado la pelicula, se busca si existe, se recoge el id y se modificara en base a su id
	@Override
	public boolean modificarPelicula() {
		int id;
		DatosPelicula dp = new DatosPelicula();
		PedirDatos pd = new PedirDatos();
		Pelicula p1 = new Pelicula();
		Pelicula p2 = new Pelicula();
		
		p1 = pd.pidePelicula();
				
		id = dp.obtenerPelicula(p1.getNombre());
		
		System.out.println("-- TOCA MODIFICAR LA PELÍCULA, INTRODUCE LOS NUEVOS DATOS");
		p2 = pd.pidePelicula();
		p2.setId(id);
		
		dp.modificaPelicula(p2);
		
		return false;
	}

	@Override
	public List<Pelicula> listar() {
		GenerarInforme f= new GenerarInforme();
		return f.listarPeliculas();
	}

	@Override
	public void arrancarAplicacion() {
		int opcion;
		do {
		Menu.mostrarMenu();
		opcion=new Scanner(System.in).nextInt();
		
			switch(opcion) {
				case 1: this.altaPelicula();break;
				case 2:	this.bajaPelicula();break;
				case 3: this.modificarPelicula();break;
				//case 4:	this.altaCliente();break;
				//case 5:	this.bajaCliente(); break;
				//case 6:	this.modificarCliente();break;
				case 7: this.listar();break;
				case 0: break;
			}
		}while(opcion!=0);
		
	}
	
	

}
