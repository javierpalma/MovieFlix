package beans;

//Se crea la clase categoría con atributos id y nombre.

public class Categoria {
	
	
	private int id;
	private String nombre;
	
	public Categoria() {
		super();
	}
 /**
  * 
  * @param id
  * @param nombre
  */
	public Categoria(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
/**
 * 
 * @return id
 */
	public int getId() {
		return id;
	}
/**
 * @param id
 */
	public void setId(int id) {
		this.id = id;
	}
/**
 * 
 * @return nombre
 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * @param nombre
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 */

	@Override
	public String toString() {
		return "Categoría [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
