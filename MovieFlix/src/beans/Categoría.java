package beans;

public class Categor�a {
	
	private int id;
	private String nombre;
	
	public Categor�a() {
		super();
	}

	public Categor�a(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Categor�a [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
