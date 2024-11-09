package aplicacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.sql.DataSource;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clasesDAOjdbc.MonedaDAOjdbc;
import clasesDAOjdbc.StockDAOjdbc;
import comparators.ComparadorNomenclaturaMoneda;
import comparators.ComparadorPrecioEnDolar;

public class GestorDAOs {
	private static final double MAX_CANTIDAD = 1000.0; // cantidad máxima de criptos a generar

	private static DataSource connection;

	private static Scanner in = new Scanner(System.in);

	static {
		connection = MiDataSource.getDataSource();
	}

	public static void crearMoneda() throws SQLException { // donde tendría que aparecer el error?

		System.out.println("Elija qué tipo de moneda quiere crear:\n" + "1) Moneda fiduciaria\n" + "2) Criptomoneda");
		boolean seleccionado = false;

		while (!seleccionado) {

			switch (in.nextInt()) {

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

	private static void crearCriptomoneda() throws SQLException {

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
		int confirmacion = in.nextInt();

		if (confirmacion == 1) {
			MonedaDAOjdbc moneda = new MonedaDAOjdbc();
			moneda.insertarMoneda(connection, nombre, nomenclatura, precio, volatilidad);
			StockDAOjdbc stock = new StockDAOjdbc();
			stock.insertarStock(connection, nomenclatura, cantidad);
		}

		else {
			System.out.println("Operación cancelada.");
		}

	}

	private static void crearMonedaFiduciaria() throws SQLException {

		System.out.println("Ingrese el nombre de la moneda fiduciaria");
		String nombre = in.nextLine();

		System.out.println("Ingrese la nomenclatura de la moneda fiduciaria");
		String nomenclatura = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();

		System.out.println("Desea confirmar?\n Ingrese 1 para confirmar, y cualquier otra para cancelar.");
		int confirmacion = in.nextInt();

		if (confirmacion == 1) {
			MonedaDAOjdbc moneda = new MonedaDAOjdbc();
			moneda.insertarMoneda(connection, nombre, nomenclatura, precio);
		}

		else {
			System.out.println("Operación cancelada.");
		}

	}

	public static List<Moneda> crearListaMonedas() throws SQLException {
		List<Moneda> listaDeMonedas = new ArrayList<>();

		Statement stmt = connection.getConnection().createStatement();
		ResultSet resul = stmt.executeQuery("SELECT * FROM moneda");

		while (resul.next()) {
			if (resul.getString(0).equalsIgnoreCase("C")) {
				Criptomoneda cripto = new Criptomoneda();
				cripto.setNomenclatura(resul.getString("NOMENCLATURA"));
				cripto.setNombre(resul.getString("NOMBRE"));
				cripto.setPrecio(resul.getDouble("VALOR_DOLAR"));
				cripto.setVolatilidad(resul.getDouble("VOLATILIDAD"));
				listaDeMonedas.add(cripto);
			} else {
				FIAT fiat = new FIAT();
				fiat.setNomenclatura(resul.getString("NOMENCLATURA"));
				fiat.setNombre(resul.getString("NOMBRE"));
				fiat.setPrecio(resul.getDouble("VALOR_DOLAR"));
				listaDeMonedas.add(fiat);

			}
		}

		resul.close();
		stmt.close();

		return listaDeMonedas;
	}

	public static void listarMonedasPrecioEnDolar(List<Moneda> listaDeMonedas) throws SQLException {
		Collections.sort(listaDeMonedas, new ComparadorPrecioEnDolar());

		// PREGUNTAR SI ES C O F, MONEDA ABSTRACT

		for (Moneda moneda : listaDeMonedas) {
			System.out.println("Nombre: " + moneda.getNombre() + "\nNomenclatura: " + moneda.getNomenclatura()
					+ "\nPrecio en Dólares: " + moneda.getPrecio() + "\nVolatilidad: " + moneda.getVolatilidad());
		}
	}

	public static void listarMonedasNomenclatura(List<Moneda> listaDeMonedas) throws SQLException {
		Collections.sort(listaDeMonedas, new ComparadorNomenclaturaMoneda());

		for (Moneda moneda : listaDeMonedas) {
			System.out.println("Nombre: " + moneda.getNombre() + "\nNomenclatura: " + moneda.getNomenclatura()
					+ "\nPrecio en Dólares: " + moneda.getPrecio() + "\nVolatilidad: " + moneda.getVolatilidad());
		}
	}

	public static void generarStock() {

		String sql = "SELECT nomenclatura FROM Criptomonedas";
		String sqlUpdate = "UPDATE Stocks SET cantidad = ? WHERE nomenclatura = ?";

		try (PreparedStatement selectStmt = connection.getConnection().prepareStatement(sql);
				PreparedStatement updateStmt = connection.getConnection().prepareStatement(sqlUpdate);

				ResultSet resul = selectStmt.executeQuery()) {

			Random random = new Random();
			while (resul.next()) {
				String nomenclatura = resul.getString("nomenclatura");
				double stockAleatorio = 0.01 + (MAX_CANTIDAD - 0.01) * random.nextDouble(); // genera un valor aleatorio
																							// entre 0.01 y 1000.0

				updateStmt.setDouble(1, stockAleatorio);
				updateStmt.setString(2, nomenclatura);
				updateStmt.executeUpdate();
			}

			System.out.println("Stock generado aleatoriamente para todas las criptomonedas.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void listarStock() {
		// TODO Auto-generated method stub

	}
}
