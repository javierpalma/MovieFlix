package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Categoria;
import beans.Cliente;
import beans.Pelicula;
import datos.DatosCliente;
import datos.DatosPelicula;
import datos.GenerarInforme;
import utilidades.Menu;
import utilidades.PedirCliente;
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
	public void listarPeliculas() {
		GenerarInforme f= new GenerarInforme();
		ArrayList<Pelicula> peliculas = f.listarPeliculas();
		for (Pelicula pelicula : peliculas) {
			System.out.println(pelicula.getNombre()+"-"+pelicula.getAnyoEstreno()+"-"+pelicula.getCategoria().getNombre());
		}
	}

	@Override
	public void arrancarAplicacion() {
		int opcion, opcion2;
		do {
		Menu.mostrarMenu();
		opcion=new Scanner(System.in).nextInt();
		
			switch(opcion) {
				case 1: this.altaPelicula();
					break;
				case 2:	this.bajaPelicula();
					break;
				case 3: this.modificarPelicula();
					break;
				//case 4:	this.altaCliente();break;
				//case 5:	this.bajaCliente(); break;
				//case 6:	this.modificarCliente();break;
				case 7: do{
						Menu.listarInformes();
						opcion2= new Scanner(System.in).nextInt();
						switch (opcion2) {
						case 1:
							this.listarPeliculas();
							break;
						case 2:
							
							break;
						case 3:
							
							break;
						case 4:
							
							break;
						case 5:
							
							break;
						case 0:
							break;
	
						default:
							break;
						}
					}while(opcion2!=0);
					break;
				case 0: break;
			}
		}while(opcion!=0);
	}

	@Override
	public void listarPeliculaCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarPeliculaNoVistaCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarPeliculaPorValoracion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaCliente() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void bajaCliente() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @author Jose Miguel
	 */
	//Metodo que introduce por teclado una modificación del cliente por medio de su ID y comprueba que existe el cliente para actualizarlo.
	@Override
	public boolean modificarCliente() {
		
		DatosCliente dc = new DatosCliente();
		PedirCliente pc = new PedirCliente();
		Cliente c1 = new Cliente();
		Cliente c2 = new Cliente();
		Cliente c3 = new Cliente();
		
		c1 = pc.pideCliente();
		
		c3 = dc.obtenerCliente(c1.getNombreCliente());
		c3.getIdCliente();
		
		System.out.println("-- TOCA MODIFICAR EL CLIENTE, INTRODUCE LOS NUEVOS DATOS");
		c2 = pc.pideCliente();
		c2.setIdCliente(c3.getIdCliente());
		
		dc.modificaCliente(c2);
		
	 return false;	
	}




}
