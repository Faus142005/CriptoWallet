package aplicacion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import clases.Activo;
import clases.Criptomoneda;
import clases.Moneda;
import clases.Stock;

public final class ClasePrincipal {

	private static Scanner in;

	public static void main(String[] args) {

		try {

			creacionDeTablasEnBD(MiDataSource.getDataSource().getConnection());
			System.out.println("Conexión a la base de datos establecida\n");

			in = new Scanner(System.in);

			imprimirOpcionesEnTerminal();
			int opcion;
			while ((opcion = in.nextInt()) != 0) {
				in.nextLine();
				switch (opcion) {
				case 1:
					crearMoneda();
					break;

				case 2:
					listarMonedas();
					break;

				case 3:
					GestorAplicacion.generarStock();
					break;

				case 4:
					listarStock();
					break;

				case 5:
					GestorAplicacion.generarMisActivos();
					break;

				case 6:
					listarActivos();
					break;

				case 7:
					GestorAplicacion.comprarCriptomoneda();
					break;

				case 8:
					GestorAplicacion.swap();
					break;

				default:
					break;
				}
				imprimirOpcionesEnTerminal();
			}

			in.close();			
			MiDataSource.closeConnection();
			limpiarTerminal();

		} catch (SQLException e) {

			System.err.print("Error: " + e.getMessage());
		}
	}

	private static void creacionDeTablasEnBD(Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();

		// Crear tabla MONEDA
		String sql = "CREATE TABLE IF NOT EXISTS MONEDA  (" + "TIPO VARCHAR(1) NOT NULL, " + "NOMBRE VARCHAR(50) NOT NULL, "
				+ "NOMENCLATURA VARCHAR(10) PRIMARY KEY NOT NULL, " + "VALOR_DOLAR REAL NOT NULL, "
				+ "VOLATILIDAD REAL NULL" + ")";
		stmt.executeUpdate(sql);

		// Crear tabla STOCK
		sql = "CREATE TABLE IF NOT EXISTS STOCK (" + "NOMENCLATURA VARCHAR(10) PRIMARY KEY NOT NULL, " + "CANTIDAD REAL NULL" + ")";
		stmt.executeUpdate(sql);

		// Crear tabla ACTIVO_CRIPTO
		sql = "CREATE TABLE IF NOT EXISTS ACTIVO_CRIPTO (" + "NOMENCLATURA VARCHAR(10) PRIMARY KEY NOT NULL, "
				+ "CANTIDAD REAL NOT NULL" + ")";
		stmt.executeUpdate(sql);

		// Crear tabla ACTIVO_FIAT
		sql = "CREATE TABLE IF NOT EXISTS ACTIVO_FIAT (" + "NOMENCLATURA VARCHAR(10) PRIMARY KEY NOT NULL, "
				+ "CANTIDAD REAL NOT NULL" + ")";
		stmt.executeUpdate(sql);

		// Crear tabla TRANSACCION
		sql = "CREATE TABLE IF NOT EXISTS TRANSACCION (" + "RESUMEN VARCHAR(1000) NOT NULL, " + "FECHA_HORA DATETIME NOT NULL" + ")";
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

		System.out.println("Menu de opciones");
		System.out.println("0- Salir");
		System.out.println("1- Crear moneda");
		System.out.println("2- Listar moneda");
		System.out.println("3- Generar stock");
		System.out.println("4- Listar stock");
		System.out.println("5- Generar activos");
		System.out.println("6- Listar activos");
		System.out.println("7- Simular compra de criptomoneda");
		System.out.println("8- Simular swap");
	}
	

	// ---------------------------------------------------------------	

	private static void crearMoneda() throws SQLException {

		int opcion;

		do {
			System.out.println("Elija que moneda quiere crear");
			System.out.println("0- Salir");
			System.out.println("1- Moneda fiduciaria");
			System.out.println("2- Criptomoneda");
			opcion = in.nextInt();		
			in.nextLine();
			switch (opcion) {
			case 1:
				GestorAplicacion.crearMonedaFiduciaria();
				break;

			case 2:
				GestorAplicacion.crearCriptomoneda();
				break;
			}
		} while (opcion != 0);
	}

	private static void imprimirListaMoneda(List<Moneda> listaDeMonedas) {
		
		System.out.println("Lista de monedas:");
		
		for (Moneda moneda : listaDeMonedas) {

			System.out.print("Nombre: " + moneda.getNombre());
			System.out.print(" | Nomenclatura: " + moneda.getNomenclatura());
			System.out.print(" | Precio en dolares: " + moneda.getPrecio());
			if (moneda instanceof Criptomoneda) {
				System.out.print(" | Volatilidad: " + ((Criptomoneda) moneda).getVolatilidad());
				System.out.println(" | Tipo: C");
			}else {
				System.out.println(" | Tipo F");
			}
		}
	}
	
	private static void listarMonedas() throws SQLException {

		System.out.println("Critero de Orden para listar Monedas");
		System.out.println("1- Por precio en dólar");
		System.out.println("2- Por nomenclatura");
		int opcion = in.nextInt();
		in.nextLine();
		switch (opcion) {
		case 1:
			imprimirListaMoneda(GestorAplicacion.listarMonedasPrecioEnDolar());
			esperarTerminal();
			break;

		case 2:
			imprimirListaMoneda(GestorAplicacion.listarMonedasNomenclatura());
			esperarTerminal();
			break;
		default:
			System.out.println("Opción inválida, abrazo");
			break;
		}
	}

	// ---------------------------------------------------------------
	
	private static void imprimirListaStock(List <Stock> listaDeStock) {
		System.out.println("Lista de stock:");
		
		for (Stock stock : listaDeStock) {

			System.out.print("Nomenclatura: "+stock.getMoneda().getNomenclatura());
			System.out.println(" | Cantidad disponible: "+stock.getCantidadActual());
		}
	}

	private static void listarStock() throws SQLException {
		
		System.out.println("Critero de Orden para listar Stocks");
		System.out.println("1- Por cantidad");
		System.out.println("2- Por nomenclatura");
		int opcion = in.nextInt();
		in.nextLine();
		switch (opcion) {
		case 1:
			imprimirListaStock(GestorAplicacion.listarStockCantidad());
			esperarTerminal();
			break;

		case 2:
			imprimirListaStock(GestorAplicacion.listarStockNomenclatura());
			esperarTerminal();
			break;
		default:
			System.out.println("Opción inválida, abrazo");
			break;
		}				
	}
	
	// ---------------------------------------------------------------
	
	private static void imprimirListaActivos(List <Activo> listaDeActivo) {
		System.out.println("Lista de activos:");
		
		for (Activo activo : listaDeActivo) {

			System.out.print("Nomenclatura: "+activo.getMoneda().getNomenclatura());
			System.out.println(" | Cantidad disponible: "+activo.getCantidad());
		}
	}

	private static void listarActivos() throws SQLException {
		
		System.out.println("Critero de Orden para listar Stocks");
		System.out.println("1- Por cantidad");
		System.out.println("2- Por nomenclatura");
		int opcion = in.nextInt();
		in.nextLine();
		switch (opcion) {
		case 1:
			imprimirListaActivos(GestorAplicacion.listarActivoCantidad());
			esperarTerminal();
			break;

		case 2:
			imprimirListaActivos(GestorAplicacion.listarActivoNomenclatura());
			esperarTerminal();
			break;
		default:
			System.out.println("Opción inválida, abrazo");
			break;
		}				
	}
	
	// ---------------------------------------------------------------			

}