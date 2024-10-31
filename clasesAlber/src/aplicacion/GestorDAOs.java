package aplicacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import clases.Activo;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Stock;
import clases.Transaccion;
import clasesDAOjdbc.Activo_CriptoDAOjdbc;
import clasesDAOjdbc.Activo_FIATDAOjdbc;
import clasesDAOjdbc.MonedaDAOJDBC;
import clasesDAOjdbc.StockDAOJDBC;
import clasesDAOjdbc.TransaccionDAOjdbc;
import comparators.moneda.*;
import comparators.stock.*;
import comparators.activo.*;

public class GestorDAOs {	

	private static DataSource data;

	private static Scanner in;

	static {
		data = MiDataSource.getDataSource();
		in = new Scanner(System.in);
	}
	
	public static Criptomoneda cargarCriptomoneda() {
		System.out.println("Ingrese el nombre de la criptomoneda");
		String nombre = in.nextLine();

		System.out.println("Ingrese la nomenclatura de la criptomoneda");
		String nomenclatura = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + " en dolares");
		double precio = in.nextDouble();

		System.out.println("Ingrese la volatilidad de " + nombre);
		double volatilidad = in.nextDouble();
		Criptomoneda c = new Criptomoneda(nombre, nomenclatura, precio, volatilidad);
		
		return c;
	}
	
	public static FIAT cargarFIAT() {
		System.out.println("Ingrese el nombre de la moneda fiduciaria");
		String nombre = in.nextLine();

		System.out.println("Ingrese la nomenclatura de la moneda fiduciaria");
		String nomenclatura = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();
		
		FIAT f = new FIAT(nombre, nomenclatura, precio);
		
		return f;
	}
	
	// 1 y 2

	public static void crearCriptomoneda() throws SQLException {
		Criptomoneda c = cargarCriptomoneda();

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();

		if (confirmacion == 1) {
			MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
			StockDAOJDBC sJDBC = new StockDAOJDBC();
			
			Stock s = new Stock(0, c);
			mJDBC.insertarCriptomoneda(data, c);
			sJDBC.insertarStock(data, s);
		}

		else {
			System.out.println("Operación cancelada.");
		}

	}

	public static void crearMonedaFiduciaria() throws SQLException {
		FIAT f = cargarFIAT();

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();

		if (confirmacion == 1) {
			MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
			StockDAOJDBC sJDBC = new StockDAOJDBC();
			
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

		StockDAOJDBC sJDBC = new StockDAOJDBC();
		sJDBC.generarStock(data);
		System.out.println("Stock generado aleatoriamente para todas las criptomonedas.");

	}

	public static List <Stock> listarStockNomenclatura() throws SQLException{
		
		StockDAOJDBC sJDBC = new StockDAOJDBC();
		List<Stock> listaDeStock = sJDBC.listarStock(data);
		Collections.sort(listaDeStock, new ComparadorNomenclaturaStock());
		return listaDeStock;
		
	}
	
	public static List <Stock> listarStockCantidad() throws SQLException{
		
		StockDAOJDBC sJDBC = new StockDAOJDBC();
		List<Stock> listaDeStock = sJDBC.listarStock(data);
		Collections.sort(listaDeStock, new ComparadorCantidadStock());
		return listaDeStock;
		
	}
	
	// 5 y 6
	
	public static void generarMisActivos() throws SQLException{
		System.out.println("Ingrese la nomenclatura de la moneda");
		String nomenclatura = in.nextLine();
		
		System.out.println("Ingrese la cantidad de " + nomenclatura);
		double cantidad = in.nextDouble();
		
		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - para cancelar");
		int confirmacion = in.nextInt();

		if (confirmacion == 1) {			
			MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
			char resul = mJDBC.buscarMonedaPorNomenclatura(data, nomenclatura);
			if(resul=='0') {
				System.out.println("ERROR: La moneda "+nomenclatura+" no existe en la base de datos");
				return;		//El programa informa un error?
			}
			
			Activo a = new Activo(cantidad, new Moneda(nomenclatura));			
			if (resul=='C') {
				Activo_CriptoDAOjdbc a_cJDBC = new Activo_CriptoDAOjdbc();
				a_cJDBC.insertarActivoCripto(data, a);
			}				
			
			else {
				Activo_FIATDAOjdbc a_fJDBC = new Activo_FIATDAOjdbc();			
				a_fJDBC.insertarActivoFIAT(data, a);
			}	
			
			System.out.println("El activo fue guardado exitosamente");
		}

		else {
			System.out.println("Operación cancelada.");
		}
	}
	
	public static List <Activo> listarActivoNomenclatura() throws SQLException{
		
		Activo_CriptoDAOjdbc a_cJDBC = new Activo_CriptoDAOjdbc();				
		Activo_FIATDAOjdbc a_fJDBC = new Activo_FIATDAOjdbc();
		
		List<Activo> listaActivosTotales = new LinkedList<Activo>();
		listaActivosTotales.addAll(a_cJDBC.listarActivoCripto(data));
		listaActivosTotales.addAll(a_fJDBC.listarActivoFIAT(data));
		
		Collections.sort(listaActivosTotales, new ComparadorNomenclaturaActivo());
		return listaActivosTotales;
		
	}
	
	public static List <Activo> listarActivoCantidad() throws SQLException{
		
		Activo_CriptoDAOjdbc a_cJDBC = new Activo_CriptoDAOjdbc();				
		Activo_FIATDAOjdbc a_fJDBC = new Activo_FIATDAOjdbc();
		
		List<Activo> listaActivosTotales = new LinkedList<Activo>();
		listaActivosTotales.addAll(a_cJDBC.listarActivoCripto(data));
		listaActivosTotales.addAll(a_fJDBC.listarActivoFIAT(data));
		
		Collections.sort(listaActivosTotales, new ComparadorCantidadActivo());
		return listaActivosTotales;
		
	}

	// 7 y 6
	
	private static double equivalenciaDeFIATaCripto(String nCripto, String nFIAT, double cantidadDeFIAT) throws SQLException {
		MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
		double precioFIAT=0;
		double precioCripto=0;
		
		precioFIAT = mJDBC.buscarFIAT(data, nFIAT).getPrecio();
		precioCripto = mJDBC.buscarCriptomoneda(data, nCripto).getPrecio();
		
		return (precioCripto/precioFIAT);
	}
	
	private static boolean chequearYActualizarStock(String nFiat, double cantidad) throws SQLException {
		StockDAOJDBC s = new StockDAOJDBC();
		double stockActualDeFiat=s.buscarStock(data, nFiat).getCantidadActual();
		if (stockActualDeFiat >= cantidad) {
			Moneda m = new Moneda(nFiat);
			Stock nuevoStock = new Stock(stockActualDeFiat-cantidad,m); 
			s.actualizarStock(data, nuevoStock);
			return true;
		}
		return false;
	}
	
	public static void comprarCriptomoneda() throws SQLException{				
		/*Criptomoneda c = cargarCriptomoneda();
		FIAT f = cargarFIAT();*/
		
		System.out.println("Ingrese la nomenclatura de la criptomoneda a comprar");
		String cripto = in.nextLine();				
		
		System.out.println("Ingrese la nomenclatura de la moneda FIAT");
		String fiat = in.nextLine();
				
		System.out.println("Ingrese la cantidad disponible de " + fiat);
		double cantidadDisponible = in.nextDouble();
		
		double cantidadEnCripto=equivalenciaDeFIATaCripto(cripto,fiat,cantidadDisponible);
		System.out.println("Cantidad equivalente en criptomoneda " +cripto+": "+cantidadEnCripto);
						
		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();

		if (confirmacion == 1) {
			Activo_CriptoDAOjdbc a_cJDBC = new Activo_CriptoDAOjdbc();
			Activo a = a_cJDBC.buscarActivoCripto(data, cripto);
			Moneda c = new Moneda (cripto);
			
			//si no existe el activo lo creo
			if(a==null) { 			
				a_cJDBC.insertarActivoCripto(data, new Activo(cantidadEnCripto,c));
			}
			
			//si ya existe el activo, entonces le sumo a la cripto 
			else {
				double cantidadActualDeCripto = a_cJDBC.buscarActivoCripto(data, cripto).getCantidad();			
				a = new Activo (cantidadEnCripto+cantidadActualDeCripto,c); //le sumo la cantidad
				a_cJDBC.actualizarActivoCripto(data, a); 
			}
			
			//en ambos casos actualizo la cantidad de fiat
			Activo_FIATDAOjdbc a_fJDBC = new Activo_FIATDAOjdbc();
			double cantidadActualDeFIAT = a_fJDBC.buscarActivoFIAT(data, fiat).getCantidad();			
			a = new Activo (cantidadActualDeFIAT-cantidadDisponible,c); //le resto la cantidad
			a_cJDBC.actualizarActivoCripto(data, a); 
		
			if(chequearYActualizarStock(fiat,cantidadDisponible)) {
				String resumen="Se compró "+cantidadEnCripto+" de "+cripto+", a partir de "+cantidadDisponible+" de "+fiat;
				Transaccion t = new Transaccion(resumen);
				TransaccionDAOjdbc t_jdbc = new TransaccionDAOjdbc();
				t_jdbc.insertarTransaccionSoloResumen(data, t);
				System.out.println("La transacción fue realizada exitosamente");
				return;
			}
			
			System.out.println("Stock insuficiente");
						
		}
		
		else {
			System.out.println("Operación cancelada.");
		}
		
	}






}

