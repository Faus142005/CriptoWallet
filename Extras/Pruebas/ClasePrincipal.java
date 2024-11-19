package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ClasePrincipal {

	private static Scanner in;

	private static final String URL = "jdbc:sqlite:example.db";

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection(URL)) {

			System.out.println("Conexion a la base de datos establecida");

			in = new Scanner(System.in);

			creacionDeTablasEnBD(connection);

			imprimirOpcionesEnTerminal();
			int opcion;
			while ((opcion = in.nextInt()) != 0) {

				switch (opcion) {
				case 1:
					crearMoneda();
					break;

				case 2:
					listarMoneda();
					break;

				case 3:
					generarStock();
					break;

				case 4:
					listarStock();
					break;

				case 5:
					generarActivos();
					break;

				case 6:
					listarActivos();
					break;

				case 7:
					comprarCriptomoneda();
					break;

				case 8:
					swap();
					break;
				default:
					System.out.println("Elija nuevamente");
					imprimirOpcionesEnTerminal();
					break;
				}
			}

			in.close();

		} catch (SQLException e) {

			System.err.print("Error al conectar a la base de datos" + e.getMessage());
		}
	}

	private static void creacionDeTablasEnBD(Connection connection) throws SQLException {

		Statement stmt;
		stmt = connection.createStatement();

		String sql = "";

		stmt.executeUpdate(sql);

		sql = "";

		stmt.executeUpdate(sql);

		sql = "";

		stmt.executeUpdate(sql);

		sql = "";

		stmt.executeUpdate(sql);

		stmt.close();
	}

	private static void imprimirOpcionesEnTerminal() {

		System.out.print("1- Crear moneda" + "2- Generar stock");
	}

	private static void crearMoneda() {
		
		System.out.println("Elija que moneda quiere crear\n" + "1- Moneda fiduciaria\n" + "2- Criptomoneda");
		boolean seleccionado = false;
		
		while(!seleccionado) {
			
			switch (in.nextInt()){
			case 1: 
				
				crearMonedaFiduciaria();
				seleccionado = true;
				break;
				
			case 2:				
				crearCriptomoneda();
				seleccionado = true;
				break;
			}
		}
	}

	private static void crearMonedaFiduciaria() {
		
		System.out.println("Ingrese el nombre de la moneda fiduciaria");
		String nombre = in.nextLine();
		
		System.out.println("Ingrese la abreviatura de la moneda fiduciaria");
		String abreviatura = in.nextLine();
		
		System.out.println("Ingrese el precio de " + nombre + "en dolares");
	}
	

	private static void crearCriptomoneda() {
		System.out.println("Ingrese el nombre de la criptomoneda");
		String nombre = in.nextLine();

		System.out.println("Ingrese la abreviatura de la criptomoneda");
		String abreviatura = in.nextLine();

		System.out.println("Ingrese el path de la imagen para el icono de la criptomoneda");
		String imagePath = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();
	}

	private static void listarMoneda() {
	}

	private static void generarStock() {
	}

	private static void listarStock() {

	}

	private static void generarActivos() {

	}

	private static void listarActivos() {

	}

	private static void comprarCriptomoneda() {

	}

	private static void swap() {

	}
}
