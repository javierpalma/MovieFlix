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
import datos.GestionCategoria;
import utilidades.Menu;
import utilidades.PedirCategoria;
import utilidades.PedirCliente;
import utilidades.PedirDatos;
import datos.DatosCliente;
public class ServiciosMovieFlix implements I_ServiciosMovieFlix {

	
	//pedir datos de pelicula a dar de alta
	@SuppressWarnings("resource")
	public void altaPelicula() {
		new DatosPelicula().altaPelicula(PedirDatos.pidePelicula());
	}	

	@Override
	public void bajaPelicula() {
		new DatosPelicula().bajaPelicula(PedirDatos.pideNombre().getNombre());
		
	}

	/**
	 * @author Jose Miguel
	 */
	//Método que recoge por teclado la pelicula, se busca si existe, se recoge el id y se modificara en base a su id
	@Override
	public boolean modificarPelicula() {
		int id;
		DatosPelicula dp = new DatosPelicula();
		//PedirDatos pd = new PedirDatos(); //Vladimir lo ha quitado, es un metodo estatico, podemos acceder directamente.
		Pelicula p1 = new Pelicula();
		Pelicula p2 = new Pelicula();
		
		p1 = PedirDatos.pidePelicula();
				
		id = dp.obtenerPelicula(p1.getNombre());
		
		System.out.println("-- TOCA MODIFICAR LA PELÍCULA, INTRODUCE LOS NUEVOS DATOS");
		p2 = PedirDatos.pidePelicula();
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
				case 4:	this.altaCliente();
					break;
				case 5:	this.bajaCliente(); 
					break;
				case 6:	this.modificarCliente();
					break;
				case 7: this.verPelicula();
				case 8: do{
						Menu.listarInformes();
						opcion2= new Scanner(System.in).nextInt();
						switch (opcion2) {
						case 1:
							this.listarPeliculas();
							break;
						case 3:
							this.listarClientes();
							break;
						case 4:
							this.listarPeliculaPorValoracion();
							break;
						case 5:
							this.listarPeliculaCliente();
							break;
						case 6:
							this.listarPeliculaNoVistaCliente();
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
	public void listarClientes() {
		
	}
	
	@Override
	public void listarPeliculaCliente() {
		GenerarInforme f= new GenerarInforme();
		Cliente cliente = new Cliente();
		cliente = PedirCliente.pideCliente();
		cliente = new DatosCliente().obtenerCliente(cliente.getNombreCliente());
		if(cliente!=null) {
			ArrayList<Pelicula> misPeliculas = f.listarPeliculasCliente(cliente);
			for (Pelicula pelicula : misPeliculas) {
				System.out.println(pelicula.getNombre()+"-"+pelicula.getAnyoEstreno()+"-"+pelicula.getCategoria().getNombre());
			}
		}
	}
		
	@Override
	public void listarPeliculaPorCategoria() {
		GenerarInforme f= new GenerarInforme();
		Categoria categoria = new Categoria();
		categoria.setNombre(PedirCategoria.pideCategoria());
		categoria.setId(new GestionCategoria().obtenerIdCategoria(categoria.getNombre()));
		ArrayList<Pelicula> peliculas = f.listarPeliculaPorCategoria(categoria);
		for (Pelicula pelicula : peliculas) {
			System.out.println(pelicula.getNombre()+"-"+pelicula.getAnyoEstreno()+"-"+pelicula.getCategoria().getNombre());
		}
	}

	@Override
	public void listarPeliculaNoVistaCliente() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @author Jose Miguel, Javier Palma
	 */
	//Implantación que llama al método ver película donde se registra en el base de datos que el cliente ve la película.
	@Override
	public void verPelicula() {
		DatosCliente dc = new DatosCliente();
		DatosPelicula dp= new DatosPelicula();
		Cliente c = new Cliente();
		Pelicula p = new Pelicula();
		c = PedirCliente.pideNombre();
		c = dc.obtenerCliente(c.getNombreCliente());
		System.out.println(c);
		p = PedirDatos.pideNombre();
		p.setId(dp.obtenerPelicula(p.getNombre()));
		System.out.println(p);
		if(c!=null && p.getId()!=-1) {
			dc.verPelicula(c, p);
		}
	}
		
	

	@Override
	public void listarPeliculaPorValoracion() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @author Jose Miguel
	 */
	//Método que realiza el alta de un usuario comprobando si ya existía previamente para no sobreescribirlo
	//Hay que realizar una comprobación <-------------------------------------------------------------------
	@Override
	public void altaCliente() {
		
		
		DatosCliente dc = new DatosCliente();
		PedirCliente pc = new PedirCliente();
		Cliente c = new Cliente();
		
		c = pc.pideCliente();
		String nombreCliente = c.getNombreCliente();
		//dc.obtenerCliente(c.getNombreCliente());
		
		if(dc.obtenerCliente(c.getNombreCliente()) == null) {
			//llamar al altarCliente de la clase DatosCliente
			dc.altaCliente(c);
		}else {
			System.out.println("Ese cliente ya existe, no se puede crear.");
		}
		
	}
	@Override
	public void bajaCliente() {
		DatosCliente dc = new DatosCliente();
		Cliente c = new Cliente();
		 c = PedirCliente.pideNombre();
		
		if(dc.obtenerCliente(c.getNombreCliente()) != null) {
			//llamar al altarCliente de la clase DatosCliente
			dc.bajaCliente(c);
		}else {
			System.out.println("El cliente no existe, no se puede borrar.");
		}	
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
