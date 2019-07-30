package utilidades;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.Cliente;

public class PedirCliente {
	
	private static Logger logger = LogManager.getLogger();
	private static int anio, mes, dia;
	
	public static Cliente pedirCliente() {
	
		System.out.println("Nombre usuario: ");
		String nombre=new Scanner(System.in).nextLine();
		System.out.println("Fecha nacimineto: ");
		LocalDate ld= pideFecha();
		System.out.println("Ciudad: ");
		String ciudad=new Scanner(System.in).nextLine();
		
		return new Cliente(nombre,ld, ciudad);
	}
	
	private static LocalDate pideFecha() {
		try {
			
			System.out.println("Año: ");
			anio=new Scanner(System.in).nextInt();
			System.out.println("Mes: ");
			mes=new Scanner(System.in).nextInt();
			System.out.println("Dia: ");
			dia=new Scanner(System.in).nextInt();
			
			if(mes<1 || mes>12 || dia<1 || dia>31 ||(mes==2 && (dia<1 || dia>29))) throw new DateTimeException("\nMes o dia incorrecto");
		}
		catch(InputMismatchException ime) {
			logger.info("El año, mes y dia deben ser numericos");
			 logger.error("Año, mes o dia incorrectos.");
			 pideFecha();
		}
		catch(DateTimeException dte) {
			logger.error(dte.getMessage());
			pideFecha();
		}
		
		return LocalDate.of(anio, mes, dia);
	}

}
