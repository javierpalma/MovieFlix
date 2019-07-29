package datos;

import beans.Pelicula;

public class DatosPelicula {
	
	private Pelicula pelicula;
	
	public DatosPelicula() {
		
	}
	
	public DatosPelicula(Pelicula pelicula) {
		this.pelicula=pelicula;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	public String toString() {
		return this.pelicula.toString();
	}

}
