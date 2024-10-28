package baseDeDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import baseDeDatos.daoJDBC.CriptomonedaDAOJDBC;
import baseDeDatos.daoJDBC.MonedaFiduciariaDAOJDBC;
import clases.Criptomoneda;
import clases.FIAT;

public class ClasePrincipal {

	private static Scanner in;

	private static final String URL = "jdbc:sqlite:example.db";
	private static final Connection connection = MyConnection.getConnection();

	public static void main(String[] args) {

		System.out.println("Conexion a la base de datos establecida");

		in = new Scanner(System.in);

		// creacionDeTablasEnBD(connection);
		int opcion;
		try {

			do {

				limpiarTerminal();
				imprimirOpcionesEnTerminal();
				opcion = Integer.valueOf(in.nextLine());

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
					simularCompraCriptomoneda();
					break;

				case 8:
					simularSwap();
					break;
				}
			} while (opcion != 0);

			in.close();

		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
	}

	private static void creacionDeTablasEnBD(Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();

		// Crear tabla MONEDA
		String sql = "CREATE TABLE MONEDA (" + "TIPO VARCHAR(1) NOT NULL, " + "NOMBRE VARCHAR(50) NOT NULL, "
				+ "NOMENCLATURA VARCHAR(10) PRIMARY KEY NOT NULL, " + "VALOR_DOLAR REAL NOT NULL, "
				+ "VOLATILIDAD REAL NULL, " + "STOCK REAL NULL" + ")";
		stmt.executeUpdate(sql);

		// Crear tabla ACTIVO_CRIPTO
		sql = "CREATE TABLE ACTIVO_CRIPTO (" + "NOMENCLATURA VARCHAR(10) PRIMARY KEY NOT NULL, "
				+ "CANTIDAD REAL NOT NULL" + ")";
		stmt.executeUpdate(sql);

		// Crear tabla ACTIVO_FIAT
		sql = "CREATE TABLE ACTIVO_FIAT (" + "NOMENCLATURA VARCHAR(10) PRIMARY KEY NOT NULL, "
				+ "CANTIDAD REAL NOT NULL" + ")";
		stmt.executeUpdate(sql);

		// Crear tabla TRANSACCION
		sql = "CREATE TABLE TRANSACCION (" + "RESUMEN VARCHAR(1000) NOT NULL, " + "FECHA_HORA DATETIME NOT NULL" + ")";
		stmt.executeUpdate(sql);

		// Cerrar el Statement
		stmt.close();

	}

	private static void limpiarTerminal() {

		for (int i = 0; i < 100; i++)
			System.out.println();
	}

	private static void esperarTerminal() {

		System.out.println("Presiona cualquier enter para continuar...");
		in.nextLine(); // Espera a que el usuario presione cualquier tecla
	}

	private static void imprimirOpcionesEnTerminal() {

		System.out.println("1- Crear moneda");
		System.out.println("2- Listar moneda");
		System.out.println("3- Generar stock");
		System.out.println("4- Listar stock");
		System.out.println("5- Generar activos");
		System.out.println("6- Listar activos");
		System.out.println("7- Simular compra de criptomoneda");
		System.out.println("8- Simular swap");
	}

	private static void crearMoneda() throws SQLException {

		int opcion;

		do {
			System.out.println("Elija que moneda quiere crear");
			System.out.println("0- Salir");
			System.out.println("1- Moneda fiduciaria");
			System.out.println("2- Criptomoneda");
			opcion = Integer.valueOf(in.nextLine());
			switch (opcion) {
			case 1:

				crearMonedaFiduciaria();
				break;

			case 2:
				crearCriptomoneda();
				break;
			}
		} while (opcion != 0 && !connection.isClosed());
	}

	private static void crearMonedaFiduciaria() throws SQLException {
		System.out.println("Ingrese el nombre de la moneda fiduciaria");
		String nombre = in.nextLine();

		System.out.println("Ingrese la abreviatura de la moneda fiduciaria");
		String abreviatura = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + " en dólares");
		double precio = Double.valueOf(in.nextLine());

		FIAT f = new FIAT();
		f.setNombre(nombre);
		f.setAbreviatura(abreviatura);
		f.setPrecio(precio);

		MonedaFiduciariaDAOJDBC.insertarMonedaFiduciaria(f);
	}

	private static void crearCriptomoneda() throws SQLException {
		System.out.println("Ingrese el nombre de la criptomoneda");
		String nombre = in.nextLine();

		System.out.println("Ingrese la abreviatura de la criptomoneda");
		String abreviatura = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + " en dolares");
		double precio = Double.valueOf(in.nextLine());

		System.out.println("Ingrese la volatilidad de " + nombre);
		double volatilidad = Double.valueOf(in.nextLine());

		Criptomoneda c = new Criptomoneda();
		c.setNombre(nombre);
		c.setAbreviatura(abreviatura);
		c.setPrecio(precio);
		c.setVolatilidad(volatilidad);

		CriptomonedaDAOJDBC.insertarCriptomoneda(c);
	}

	private static void listarMoneda() throws SQLException {
		String sql = "SELECT * FROM MONEDA ORDER BY NOMENCLATURA";

		try (Connection connection = DriverManager.getConnection(URL);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("Listado de monedas:");
			while (rs.next()) {
				String tipo = rs.getString("TIPO");
				String nombre = rs.getString("NOMBRE");
				String nomenclatura = rs.getString("NOMENCLATURA");
				double valorDolar = rs.getDouble("VALOR_DOLAR");
				double volatilidad = rs.getDouble("VOLATILIDAD");
				double stock = rs.getDouble("STOCK");

				System.out.printf(
						"Tipo: %s, Nombre: %s, Nomenclatura: %s, Valor (USD): %.2f, Volatilidad: %.2f, Stock: %.2f%n",
						tipo, nombre, nomenclatura, valorDolar, volatilidad, stock);
			}
		} catch (SQLException e) {
			System.err.println("Error al listar las monedas: " + e.getMessage());
		}

		esperarTerminal();
	}

	private static void generarStock() {
	}

	private static void listarStock() {

	}

	private static void generarActivos() {

	}

	private static void listarActivos() {

	}

	private static void simularCompraCriptomoneda() {

	}

	private static void simularSwap() {

	}
}
