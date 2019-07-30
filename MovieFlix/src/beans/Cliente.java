package beans;

import java.time.LocalDate;

/**
 * 
 * @author Asiel
 *
 */

// Se crea la clase cliente con atributos idCliente, nombreCliente, fechaNacimiento y ciudad.
// Se importa la la clase LocalDate.

public class Cliente {

	private int idCliente;
	private String nombreCliente;
	private LocalDate fechaNacimiento;
	private String ciudad;
	
	public Cliente() {
		super();
	}
	
	/**
	 * 
	 * @param idCliente
	 * @param nombtreCliente
	 * @param fechaNacimiento
	 * @param ciudad
	 */

	public Cliente(int idCliente, String nombtreCliente, LocalDate fechaNacimiento, String ciudad) {
		super();
		this.idCliente = idCliente;
		this.nombreCliente = nombtreCliente;
		this.fechaNacimiento = fechaNacimiento;
		this.ciudad = ciudad;
	}
	
	/**
	 * 
	 * @return
	 */

	public int getIdCliente() {
		return idCliente;
	}
	
	/**
	 * 
	 * @param idCliente
	 */

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	/**
	 * 
	 * @return
	 */

	public String getNombreCliente() {
		return nombreCliente;
	}
	
	/**
	 * 
	 * @param nombtreCliente
	 */

	public void setNombreCliente(String nombtreCliente) {
		this.nombreCliente = nombtreCliente;
	}
	
	/**
	 * 
	 * @return
	 */

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * 
	 * @param fechaNacimiento
	 */

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * 
	 * @return
	 */

	public String getCiudad() {
		return ciudad;
	}
	
	/**
	 * 
	 * @param ciudad
	 */

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombreCliente=" + nombreCliente + ", fechaNacimiento="
				+ fechaNacimiento + ", ciudad=" + ciudad + "]";
	}
	
	
	
	
	
	
	
}
