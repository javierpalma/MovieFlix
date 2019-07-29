package servicios;

import java.util.List;

import beans.Pelicula;

public interface I_ServiciosMovieFlix {
	
	
	public void altaPelicula();
	public boolean bajaPelicula();
	public boolean modificarPelicula(Pelicula pelicula);
	public List<Pelicula> listar();
	public void arrancarAplicacion();
}
