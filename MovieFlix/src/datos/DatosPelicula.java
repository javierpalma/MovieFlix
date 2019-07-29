package datos;

import beans.Pelicula;

/**
 * 
 * @author Vladimir Juan
 *	
 * @version 1.0
 */

public class DatosPelicula {
	
	//Atributos de la clase
	private Pelicula pelicula;
	
	public DatosPelicula() {
		
	}
	/** 
	 * @param pelicula
	 */
	public DatosPelicula(Pelicula pelicula) {
		this.pelicula=pelicula;
	}
	/**
	 * 
	 * @return pelicula
	 */
	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	public String toString() {
		return this.pelicula.toString();
	}
	//metodos principales
	
	public boolean buscarPelicula(Pelicula pelicula) {
		return false;
	}
	public void altaPelicula(Pelicula pelicula) {
		buscarPelicula(pelicula);
	}
	
	public boolean bajaPelicula(Pelicula pelicula) {
		buscarPelicula(pelicula);
		return false;
	}
	
	

}
