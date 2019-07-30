package utilidades;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PedirUsuario {
	
	private static Logger logger = LogManager.getLogger();
	private static int anio, mes, dia;
	
	public static void pedirUsuario() {
		System.out.println("Id usuario: ");
		int id=new Scanner(System.in).nextInt();
		System.out.println("Nombre usuario: ");
		String nombre=new Scanner(System.in).nextLine();
		System.out.println("Fecha nacimineto: ");
		LocalDate ld= fecha();
		
		
	}
	
	private static LocalDate fecha() {
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
			 fecha();
		}
		catch(DateTimeException dte) {
			logger.error(dte.getMessage());
			fecha();
		}
		
		return LocalDate.of(anio, mes, dia);
	}

}
