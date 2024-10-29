package aplicacion;

import java.sql.*;
import javax.sql.DataSource;

import clases.Moneda;
import clasesDAOjdbc.MonedaDAOjdbc;
import clasesDAOjdbc.StockDAOjdbc;

import java.util.List;
import java.util.Scanner;

public class ClasePrincipal {

	private static Scanner in;
	

	public static void main(String[] args) {				

		try {

			System.out.println("Conexión a la base de datos establecida");			

			in = new Scanner(System.in);						
			
			imprimirOpcionesEnTerminal();
			int opcion;
			while ((opcion = in.nextInt()) != 0) {

				switch (opcion) {
				case 1:
					GestorDAOs.crearMoneda();
					break;

				case 2:
					listarMonedas();					
					break;

				case 3:					
					GestorDAOs.generarStock();
					break;

				case 4:
					listarStock();
					break;

				case 5:
					GestorDAOs.generarActivos();
					break;

				case 6:
					listarActivos();
					break;

				case 7:
					GestorDAOs.comprarCriptomoneda();
					break;

				case 8:
					GestorDAOs.swap();
					break;
					
				default:
					System.out.println("Elija nuevamente");
					imprimirOpcionesEnTerminal();
					break;
				}
			}

			in.close();
			MiDataSource.closeConnection();

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
	
	
	private static void listarMonedas() throws SQLException {
		List<Moneda> listaDeMonedas = GestorDAOs.crearListaMonedas();

		System.out.println("Critero de Orden para listar Monedas:\n 1) Por precio en dólar\n2) Por nomenclatura");
		int opcion;
		while ((opcion = in.nextInt()) != 0) {

			switch (opcion) {
			case 1:
				GestorDAOs.listarMonedasPrecioEnDolar(listaDeMonedas);
				break;

			case 2:
				GestorDAOs.listarMonedasNomenclatura(listaDeMonedas);
				break;
			default:
				System.out.println("Opción inválida, elija nuevamente");
				break;
			}
		}
	}
				
	
	//---------------------------------------------------------------


	private static void listarStock() throws SQLException {

	}

	private static void generarActivos(Connection connection) throws SQLException {

	}

	private static void listarActivos() throws SQLException {

	}

	private static void comprarCriptomoneda(Connection connection) throws SQLException {

	}

	private static void swap(Connection connection) throws SQLException {

	}
}