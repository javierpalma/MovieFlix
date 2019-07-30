package utilidades;

import java.util.Scanner;

import beans.Categoria;

/**
 * 
 * @author Jose Miguel
 *
 */
//Método hiper intenso que devuelve una palabra "nombre de la categoria".
public class PedirCategoria {

	public static String pideCategoria() {
		System.out.println("Nombre de la categoría");
		String nombre = new Scanner(System.in).nextLine();
		
		return nombre;
	}
	
}
