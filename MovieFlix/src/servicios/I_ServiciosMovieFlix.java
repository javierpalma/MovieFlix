package servicios;

import beans.Pelicula;

public interface I_ServiciosMovieFlix {
	
	
	public void altaPelicula();
	public boolean bajaPelicula();
	public boolean modificarPelicula(Pelicula pelicula);
	
}
