package servicios;

import java.util.List;

import beans.Pelicula;

public interface I_ServiciosMovieFlix {
	
	
	public void altaPelicula();
	public void bajaPelicula();
	public boolean modificarPelicula();
	public void listarPeliculas();
	public void arrancarAplicacion();
	
	//Sprint2
	
	public void listarPeliculaCliente();
	public void listarPeliculaPorCategoria();
	public void listarPeliculaNoVistaCliente();
	public void listarPeliculaPorValoracion();
	public void verPelicula();
	public boolean modificarCliente();
	public void altaCliente();
	public void bajaCliente();
	
}
