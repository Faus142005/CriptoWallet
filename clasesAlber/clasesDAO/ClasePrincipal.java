package clasesDAO;

import java.sql.*;
import javax.sql.DataSource;

import java.util.Scanner;

public class ClasePrincipal {

	private static Scanner in;

	//private static final String URL = "jdbc:sqlite:CriptoWallet.db";

	public static void main(String[] args) {
		
		DataSource dataSource = MiDataSource.getDataSource();

		try (Connection connection = dataSource.getConnection()) {

			System.out.println("Conexión a la base de datos establecida");			

			in = new Scanner(System.in);						
			
			imprimirOpcionesEnTerminal();
			int opcion;
			while ((opcion = in.nextInt()) != 0) {

				switch (opcion) {
				case 1:
					crearMoneda(connection);
					break;

				case 2:
					MonedaDAOjdbc moneda = new MonedaDAOjdbc();
					moneda.listarMonedas(connection);
					break;

				case 3:					
					StockDAOjdbc stock = new StockDAOjdbc();
					stock.generarStock(connection);
					break;

				case 4:
					listarStock(connection);
					break;

				case 5:
					generarActivos(connection);
					break;

				case 6:
					listarActivos(connection);
					break;

				case 7:
					comprarCriptomoneda(connection);
					break;

				case 8:
					swap(connection);
					break;
					
				default:
					System.out.println("Elija nuevamente");
					imprimirOpcionesEnTerminal();
					break;
				}
			}

			in.close();
			connection.close();

		} catch (SQLException e) {

			System.err.print("Error al conectar a la base de datos " + e.getMessage());
		}
	}


	private static void imprimirOpcionesEnTerminal() {
		System.out.println("MENÚ DE OPCIONES: ");
		System.out.print("1) Crear moneda\n" +
						 "2) Listar monedas\n" +
						 "3) Generar Stock\n" +
						 "4) Listar Stock\n" +
						 "5) Generar Activo\n" +
						 "6) Listar Activos\n" +
						 "7) Comprar Criptomoneda\n" +
						 "8) Realizar operación de Swap\n" );
																		
	}
	
	private static void crearMoneda(Connection connection) throws SQLException {		
			
		System.out.println("Elija qué tipo de moneda quiere crear:\n" + "1) Moneda fiduciaria\n" + "2) Criptomoneda");
		boolean seleccionado = false;
			
		while(!seleccionado) {
			
			switch (in.nextInt()){
			
			case 1: 				
				crearMonedaFiduciaria(connection);
				seleccionado = true;
				break;
				
			case 2:		
				crearCriptomoneda(connection);
				seleccionado = true;
				break;
			}			
		}
	}
	
	private static void crearCriptomoneda(Connection connection) throws SQLException {
		
		System.out.println("Ingrese el nombre de la criptomoneda");
		String nombre = in.nextLine();

		System.out.println("Ingrese la nomenclatura de la criptomoneda");
		String nomenclatura = in.nextLine();
		
		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();
		
		System.out.println("Ingrese la volatilidad de " + nombre);
		double volatilidad = in.nextDouble();
		
		System.out.println("Ingrese el stock disponible de " + nombre);
		double cantidad = in.nextDouble();
		
		System.out.println("Desea confirmar?\n Ingrese 1 para confirmar, y cualquier otra para cancelar.");
		int confirmacion=in.nextInt();
		
		if(confirmacion==1) {			
		    MonedaDAOjdbc moneda = new MonedaDAOjdbc();
		    moneda.insertarMoneda(connection,nombre,nomenclatura,precio,volatilidad);
		    StockDAOjdbc stock = new StockDAOjdbc();
		    stock.insertarStock(connection,nomenclatura,cantidad);
		}
		
		else {
			System.out.println("Operación cancelada.");
		}		
		
	}
	
	private static void crearMonedaFiduciaria(Connection connection) throws SQLException {
		
		System.out.println("Ingrese el nombre de la moneda fiduciaria");
		String nombre = in.nextLine();
		
		System.out.println("Ingrese la nomenclatura de la moneda fiduciaria");
		String nomenclatura = in.nextLine();	
		
		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();
		
		System.out.println("Desea confirmar?\n Ingrese 1 para confirmar, y cualquier otra para cancelar.");
		int confirmacion=in.nextInt();
		
		if(confirmacion==1) {			
		    MonedaDAOjdbc moneda = new MonedaDAOjdbc();
		    moneda.insertarMoneda(connection,nombre,nomenclatura,precio);		    
		}
		
		else {
			System.out.println("Operación cancelada.");
		}		
		
	}
				
	
	//---------------------------------------------------------------


	private static void listarStock(Connection connection) throws SQLException {

	}

	private static void generarActivos(Connection connection) throws SQLException {

	}

	private static void listarActivos(Connection connection) throws SQLException {

	}

	private static void comprarCriptomoneda(Connection connection) throws SQLException {

	}

	private static void swap(Connection connection) throws SQLException {

	}
}