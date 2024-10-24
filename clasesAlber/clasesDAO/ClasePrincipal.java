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
			//connection.setAutoCommit(false); //para q no haga los commit automáticamente

			in = new Scanner(System.in);

			GestorBaseDeDatos.creacionDeTablasEnBD(connection);
			
			imprimirOpcionesEnTerminal();
			int opcion;
			while ((opcion = in.nextInt()) != 0) {

				switch (opcion) {
				case 1:
					crearMoneda(connection);
					break;

				case 2:
					listarMonedas(connection);
					break;

				case 3:
					generarStock(connection);
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

	/*
	private static void crearMoneda(Connection connection) throws SQLException {
		
		System.out.println("Elija qué moneda quiere crear:\n" + "1) Moneda fiduciaria\n" + "2) Criptomoneda");
		boolean seleccionado = false;
		
		while(!seleccionado) {
			
			switch (in.nextInt()){
			
			case 1: 				
				MonedaFiduciariaDAOjdbc fiat = new MonedaFiduciariaDAOjdbc();
				fiat.insertarMonedaFiduciaria(connection);
				seleccionado = true;
				break;
				
			case 2:		
				CriptomonedaDAOjdbc cripto = new CriptomonedaDAOjdbc();
				cripto.insertarCriptomoneda(connection);
				seleccionado = true;
				break;
			}
		}
	}
	
	private static void insertarMonedaFiduciaria(Connection connection) throws SQLException {
		
		System.out.println("Ingrese el nombre de la moneda fiduciaria");
		String nombre = in.nextLine();
		
		System.out.println("Ingrese la nomenclatura de la moneda fiduciaria");
		String nomenclatura = in.nextLine();		
		
				
		Statement stmt = connection.createStatement();
		String query = "INSERT INTO moneda (nombre,nomenclatura) VALUES(nombre, nomenclatura)";
		int res = stmt.executeUpdate(query);
		stmt.close();			

	}
	

	private static void insertarCriptomoneda(Connection connection) throws SQLException {
		System.out.println("Ingrese el nombre de la criptomoneda");
		String nombre = in.nextLine();

		System.out.println("Ingrese la nomenclatura de la criptomoneda");
		String nomenclatura = in.nextLine();

		System.out.println("Ingrese el path de la imagen para el icono de la criptomoneda");
		String imagePath = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();
	}

	private static void listarMonedas(Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet resul=stmt.executeQuery("SELECT * FROM moneda");
			
		while(resul.next()) {
			System.out.println("Nombre: "+resul.getString("nombre")+
							   "\nNomenclatura: "+ resul.getString("nomenclatura")+
							   "\nPrecio en Dólares: "+ resul.getString("precio")+
							   "\nVolatilidad: "+ resul.getString("volatilidad")+
							   "\nStock: "+ resul.getString("stock"));
		}
		
		stmt.close();							
	}
	*/

	private static void generarStock(Connection connection) throws SQLException {
	}

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