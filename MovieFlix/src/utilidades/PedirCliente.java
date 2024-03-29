package utilidades;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.Cliente;
import beans.Pelicula;

/**
 * 
 * @author Vladimir Juan
 *
 */
public class PedirCliente {
	
	private static Logger logger = LogManager.getLogger();
	private static int anio, mes, dia;
	
	/**
	 * 
	 * @return Cliente
	 */
	@SuppressWarnings("resource")
	public static Cliente pideCliente() {
	
		System.out.println("Nombre usuario: ");
		String nombre=new Scanner(System.in).nextLine();
		
		System.out.println("Fecha nacimineto: ");
		LocalDate ld= pideFecha();
		
		System.out.println("Ciudad de residencia: ");
		String ciudad=new Scanner(System.in).nextLine();
		
		return new Cliente(nombre,ld, ciudad);
	}
	
	public static Cliente pideNombre() {
		System.out.println("Nombre usuario: ");
		String nombre=new Scanner(System.in).nextLine();

		
		return new Cliente(nombre,null, null);
	}
	
	//poner posit morado porque se necesita para solo pedir nombre para borrar usuario
	public static String borrarCliente() {
		System.out.println("Nombre: ");
		String nombre=new Scanner(System.in).nextLine();
		return nombre;
	}
	
	@SuppressWarnings("resource")
	/**
	 *
	 * @return localDate
	 */
	private static LocalDate pideFecha() {
		try {
			
			System.out.println("A�o: ");
			anio=new Scanner(System.in).nextInt();
			System.out.println("Mes: ");
			mes=new Scanner(System.in).nextInt();
			System.out.println("Dia: ");
			dia=new Scanner(System.in).nextInt();
			
			if(mes<1 || mes>12 || dia<1 || dia>31 ||(mes==2 && (dia<1 || dia>29))) throw new DateTimeException("\nMes o dia incorrecto");
		}
		catch(InputMismatchException ime) {
			logger.info("El a�o, mes y dia deben ser numericos");
			 logger.error("A�o, mes o dia incorrectos.");
			 pideFecha();
		}
		catch(DateTimeException dte) {
			logger.error(dte.getMessage());
			pideFecha();
		}
		
		return LocalDate.of(anio, mes, dia);
	}

}
