package utilidades;

import java.util.Scanner;

import beans.Categoria;
import beans.Pelicula;

public class PedirDatos {
	
	public static Pelicula pidePelicula() {
		System.out.println("Nombre de la pelicula: ");
		String nombre=new Scanner(System.in).nextLine();
		System.out.println("Año de estreno: ");
		int anio=new Scanner(System.in).nextInt();
		System.out.println("Id categoria: ");
		int numeroCategoria=new Scanner(System.in).nextInt();
		
		Pelicula p= new Pelicula();
		p.setNombre(nombre);
		p.setAnyoEstreno(anio);
		Categoria c= new Categoria();
		c.setId(numeroCategoria);
		p.setCategoria(c);
		
		return p;
	}

}
