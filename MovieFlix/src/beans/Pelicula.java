package beans;

import beans.Categoria;

/**
 * 
 * @author José Miguel
 *
 */

// Se crea la clase pelicula con atributos id, nombre y año de estreno.
// Se introduce el atributo categoría perteneciente al tipo Categoria.
// Se importa la la clase Categoria
public class Pelicula {
	
	private int id;
	private String nombre;
	private int anyoEstreno;
	private Categoria categoria;
	
	public Pelicula() {
		super();
		// TODO Auto-generated constructor stub	
	}

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param anyoEstreno
	 * @param categoria
	 */
	
	public Pelicula(int id, String nombre, int anyoEstreno, Categoria categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anyoEstreno = anyoEstreno;
		this.categoria = categoria;
	}
	
	public Pelicula(String nombre, int anyoEstreno, Categoria categoria) {
		this.nombre=nombre;
		this.anyoEstreno=anyoEstreno;
		this.categoria=categoria;
	}
	
	/**
	 * 
	 * @return
	 */

	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
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
	 * @return
	 */

	public int getAnyoEstreno() {
		return anyoEstreno;
	}
	
	/**
	 * 
	 * @param anyoEstreno
	 */

	public void setAnyoEstreno(int anyoEstreno) {
		this.anyoEstreno = anyoEstreno;
	}
	
	/**
	 * 
	 * @return
	 */

	public Categoria getCategoria() {
		return categoria;
	}
	
	/**
	 * 
	 * @param categoria
	 */

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anyoEstreno;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		if (anyoEstreno != other.anyoEstreno)
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", nombre=" + nombre + ", anyoEstreno=" + anyoEstreno + "]";
	}
	
	
	
}
