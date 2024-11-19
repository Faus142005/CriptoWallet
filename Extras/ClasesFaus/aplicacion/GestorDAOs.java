package aplicacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.sql.DataSource;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Stock;
import clasesDAOjdbc.MonedaDAOJDBC;
import clasesDAOjdbc.StockDAOJDBC;
import comparators.ComparadorNomenclaturaMoneda;
import comparators.ComparadorNomenclaturaStock;
import comparators.ComparadorPrecioEnDolar;

public class GestorDAOs {
	private static final double MAX_CANTIDAD = 1000.0; // cantidad máxima de criptos a generar

	private static DataSource data;

	private static Scanner in;

	static {
		data = MiDataSource.getDataSource();
		in = new Scanner(System.in);
	}
	// 1 y 2

	public static void crearCriptomoneda() throws SQLException {

		System.out.println("Ingrese el nombre de la criptomoneda");
		String nombre = in.nextLine();

		System.out.println("Ingrese la nomenclatura de la criptomoneda");
		String nomenclatura = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();

		System.out.println("Ingrese la volatilidad de " + nombre);
		double volatilidad = in.nextDouble();

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();

		if (confirmacion == 1) {
			MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
			StockDAOJDBC sJDBC = new StockDAOJDBC();
			Criptomoneda c = new Criptomoneda(nombre, nomenclatura, precio, volatilidad);
			Stock s = new Stock(0, c);
			mJDBC.insertarCriptomoneda(data, c);
			sJDBC.insertarStock(data, s);
		}

		else {
			System.out.println("Operación cancelada.");
		}

	}

	public static void crearMonedaFiduciaria() throws SQLException {

		System.out.println("Ingrese el nombre de la moneda fiduciaria");
		String nombre = in.nextLine();

		System.out.println("Ingrese la nomenclatura de la moneda fiduciaria");
		String nomenclatura = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();

		if (confirmacion == 1) {
			MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
			StockDAOJDBC sJDBC = new StockDAOJDBC();
			FIAT f = new FIAT(nombre, nomenclatura, precio);
			Stock s = new Stock(0, f);
			mJDBC.insertarFIAT(data, f);
			sJDBC.insertarStock(data, s);
			System.out.println("La moneda fue creada exitosamente");
		}

		else {
			System.out.println("Operación cancelada.");
		}

	}

	public static List<Moneda> listarMonedasPrecioEnDolar() throws SQLException {
		MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
		List<Moneda> listaDeMonedas = mJDBC.listarMonedas(data);
		Collections.sort(listaDeMonedas, new ComparadorPrecioEnDolar());
		return listaDeMonedas;
	}

	public static List<Moneda> listarMonedasNomenclatura() throws SQLException {
		MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
		List<Moneda> listaDeMonedas = mJDBC.listarMonedas(data);
		Collections.sort(listaDeMonedas, new ComparadorNomenclaturaMoneda());
		return listaDeMonedas;
	}

	// 3 y 4
	
	public static void generarStock() throws SQLException{

		String sql = "SELECT nomenclatura FROM Criptomonedas";
		String sqlUpdate = "UPDATE Stocks SET cantidad = ? WHERE nomenclatura = ?";

		PreparedStatement selectStmt = data.getConnection().prepareStatement(sql);
		PreparedStatement updateStmt = data.getConnection().prepareStatement(sqlUpdate);

		ResultSet resul = selectStmt.executeQuery();

		Random random = new Random();
		while (resul.next()) {
			String nomenclatura = resul.getString("nomenclatura");
			double stockAleatorio = 0.01 + (MAX_CANTIDAD - 0.01) * random.nextDouble(); // genera un valor aleatorio 0.01 y 1000
			updateStmt.setDouble(1, stockAleatorio);
			updateStmt.setString(2, nomenclatura);
			updateStmt.executeUpdate();
		}

		System.out.println("Stock generado aleatoriamente para todas las criptomonedas.");
		
		
		//No hay que hacerlo en el jdbc??
	}

	public static List <Stock> listarStockNomenclatura() throws SQLException{
		
		StockDAOJDBC sJDBC = new StockDAOJDBC();
		List<Stock> listaDeStock = sJDBC.listarStock(data);
		Collections.sort(listaDeStock, new ComparadorNomenclaturaStock());
		return listaDeStock;
	}
}
