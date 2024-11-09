package aplicacion;

import java.sql.SQLException;
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
import clasesDAOjdbc.Activo_CriptoDAOjdbc;
import clasesDAOjdbc.Activo_FIATDAOjdbc;
import clasesDAOjdbc.MonedaDAOJDBC;
import clasesDAOjdbc.StockDAOJDBC;
import comparators.activo.ComparadorCantidadActivo;
import comparators.activo.ComparadorNomenclaturaActivo;
import comparators.moneda.ComparadorNomenclaturaMoneda;
import comparators.moneda.ComparadorPrecioEnDolar;
import comparators.stock.ComparadorCantidadStock;
import comparators.stock.ComparadorNomenclaturaStock;

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
		System.out.println("Ingrese el stock");
		double cantidad = in.nextDouble();
		in.nextLine();

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();
		in.nextLine();

		if (confirmacion != 1) {
			System.out.println("Operación cancelada.");
			return;
		}
		MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
		StockDAOJDBC sJDBC = new StockDAOJDBC();

		Stock s = new Stock(cantidad, c);
		mJDBC.insertarCriptomoneda(data, c);
		sJDBC.insertarStock(data, s);
	}

	public static void crearMonedaFiduciaria() throws SQLException {
		FIAT f = cargarFIAT();

		System.out.println("Ingrese el stock");
		double cantidad = in.nextDouble();

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();
		in.nextLine();

		if (confirmacion != 1) {
			System.out.println("Operación cancelada.");
			return;
		}

		MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
		StockDAOJDBC sJDBC = new StockDAOJDBC();

		Stock s = new Stock(cantidad, f);
		mJDBC.insertarFIAT(data, f);
		sJDBC.insertarStock(data, s);
		System.out.println("La moneda fue creada exitosamente");

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

	public static void generarStock() throws SQLException {

		StockDAOJDBC sJDBC = new StockDAOJDBC();
		sJDBC.generarStock(data);
		System.out.println("Stock generado aleatoriamente para todas las criptomonedas.");

	}

	public static List<Stock> listarStockNomenclatura() throws SQLException {

		StockDAOJDBC sJDBC = new StockDAOJDBC();
		List<Stock> listaDeStock = sJDBC.listarStock(data);
		Collections.sort(listaDeStock, new ComparadorNomenclaturaStock());
		return listaDeStock;

	}

	public static List<Stock> listarStockCantidad() throws SQLException {

		StockDAOJDBC sJDBC = new StockDAOJDBC();
		List<Stock> listaDeStock = sJDBC.listarStock(data);
		Collections.sort(listaDeStock, new ComparadorCantidadStock());
		return listaDeStock;
	}

	// 5 y 6

	public static void generarMisActivos() throws SQLException {
		System.out.println("Ingrese la nomenclatura de la moneda");
		String nomenclatura = in.nextLine();

		System.out.println("Ingrese la cantidad de " + nomenclatura);
		double cantidad = in.nextDouble();

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - para cancelar");
		int confirmacion = in.nextInt();
		in.nextLine();

		if (confirmacion != 1) {
			System.out.println("Operación cancelada.");
			return;
		}

		MonedaDAOJDBC mJDBC = new MonedaDAOJDBC();
		char resul = mJDBC.buscarMonedaPorNomenclatura(data, nomenclatura);

		if (resul == '0') {
			System.out.println("ERROR: La moneda " + nomenclatura + " no existe en la base de datos");
			return;
		}

		if (resul == 'C') {
			Activo_CriptoDAOjdbc a_cJDBC = new Activo_CriptoDAOjdbc();
			Activo a = a_cJDBC.buscarActivoCripto(data, nomenclatura);

			if (a != null) {
				a.setCantidad(a.getCantidad() + cantidad);
				a_cJDBC.actualizarActivoCripto(data, a);
			}

			else {
				a = new Activo(cantidad, new Moneda(nomenclatura));
				a_cJDBC.insertarActivoCripto(data, a);
			}
		}

		else {
			Activo_FIATDAOjdbc a_fJDBC = new Activo_FIATDAOjdbc();
			Activo a = a_fJDBC.buscarActivoFIAT(data, nomenclatura);
			if (a != null) {
				a.setCantidad(a.getCantidad() + cantidad);
				a_fJDBC.actualizarActivoFIAT(data, a);
			}

			else {
				a = new Activo(cantidad, new Moneda(nomenclatura));
				a_fJDBC.insertarActivoFIAT(data, a);
			}
		}

		System.out.println("El activo fue guardado exitosamente");
	}

	public static List<Activo> listarActivoNomenclatura() throws SQLException {

		Activo_CriptoDAOjdbc a_cJDBC = new Activo_CriptoDAOjdbc();
		Activo_FIATDAOjdbc a_fJDBC = new Activo_FIATDAOjdbc();

		List<Activo> listaActivosTotales = new LinkedList<Activo>();
		listaActivosTotales.addAll(a_cJDBC.listarActivoCripto(data));
		listaActivosTotales.addAll(a_fJDBC.listarActivoFIAT(data));

		Collections.sort(listaActivosTotales, new ComparadorNomenclaturaActivo());
		return listaActivosTotales;

	}

	public static List<Activo> listarActivoCantidad() throws SQLException {

		Activo_CriptoDAOjdbc a_cJDBC = new Activo_CriptoDAOjdbc();
		Activo_FIATDAOjdbc a_fJDBC = new Activo_FIATDAOjdbc();

		List<Activo> listaActivosTotales = new LinkedList<Activo>();
		listaActivosTotales.addAll(a_cJDBC.listarActivoCripto(data));
		listaActivosTotales.addAll(a_fJDBC.listarActivoFIAT(data));

		Collections.sort(listaActivosTotales, new ComparadorCantidadActivo());
		return listaActivosTotales;

	}

	public static void comprarCriptomoneda() throws SQLException {
		/*
		 * Criptomoneda c = cargarCriptomoneda(); FIAT f = cargarFIAT();
		 */
		Criptomoneda c = null;
		Activo af = null;
		FIAT f = null;

		System.out.println("Ingrese la nomenclatura de la criptomoneda a comprar");
		String cripto = in.nextLine();

		MonedaDAOJDBC mDAOJDBC = new MonedaDAOJDBC();
		c = mDAOJDBC.buscarCriptomoneda(data, cripto);
		if (c == null) {
			System.out.println("No existe esa criptomoneda");
			return;
		}

		System.out.println("Ingrese la nomenclatura de la moneda FIAT");
		String fiat = in.nextLine();

		Activo_FIATDAOjdbc afDAOJDBC = new Activo_FIATDAOjdbc();

		af = afDAOJDBC.buscarActivoFIAT(data, fiat);
		f = mDAOJDBC.buscarFIAT(data, fiat);

		if (af == null || f == null) {
			System.out.println("No tenes esa moneda");
			return;
		}

		System.out.println("Ingrese la cantidad disponible de " + fiat);
		double cantidadDisponible = in.nextDouble();

		if (af.getCantidad() < cantidadDisponible) {
			System.out.println("No tenes esa cantidad de monedas");
			return;
		}

		double cantidadCriptoAComprar = f.getPrecio() * cantidadDisponible / c.getPrecio();

		StockDAOJDBC sDAOJDBC = new StockDAOJDBC();
		Stock stockCripto = sDAOJDBC.buscarStock(data, cripto);

		if (stockCripto.getCantidadActual() < cantidadCriptoAComprar) {
			System.out.println("No hay stock disponible");
		}

		System.out.println("Vas a comprar: " + cantidadCriptoAComprar);

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();
		in.nextLine();

		if (confirmacion != 1) {
			System.out.println("Operación cancelada.");
			return;
		}

		// Chequear que este tenga el activo

		Activo_CriptoDAOjdbc acDAOJDBC = new Activo_CriptoDAOjdbc();

		Activo activoCriptoDelUsuario = acDAOJDBC.buscarActivoCripto(data, cripto);

		boolean tieneElActivo = activoCriptoDelUsuario != null;

		if (tieneElActivo) {
			Activo a = new Activo();
			a.setCantidad(cantidadCriptoAComprar + activoCriptoDelUsuario.getCantidad());
			a.setMoneda(c);
			acDAOJDBC.actualizarActivoCripto(data, a);
		} else {
			Activo a = new Activo();
			a.setCantidad(cantidadCriptoAComprar);
			a.setMoneda(c);
			acDAOJDBC.insertarActivoCripto(data, a);
		}

		// RESTAR MONEDA AL USUARIO
		af.setCantidad(af.getCantidad() - cantidadDisponible);
		afDAOJDBC.actualizarActivoFIAT(data, af);

		// RESTAR STOCK AL SISTEMA
		Stock s = new Stock(stockCripto.getCantidadActual() - cantidadCriptoAComprar, new Moneda(cripto));
		sDAOJDBC.actualizarStock(data, s);
	}

	public static void swap() throws SQLException {

		Activo activoCriptomoneda1;
		Activo activoCriptomoneda2;
		Stock stockcriptomoneda1;
		Stock stockcriptomoneda2;
		Moneda monedaCripto1;
		Moneda monedaCripto2;

		System.out.println("Ingrese la nomenclatura de la criptomoneda que queres dar");
		String nomenclaturaCriptoOrigen = in.nextLine();

		Activo_CriptoDAOjdbc activoCriptoDAOJDBC = new Activo_CriptoDAOjdbc();
		activoCriptomoneda1 = activoCriptoDAOJDBC.buscarActivoCripto(data, nomenclaturaCriptoOrigen);

		if (activoCriptomoneda1 == null) {
			System.out.println("No tenes esta criptomoneda");
			return;
		}

		System.out.println("Ingrese la nomenclatura de la criptomoneda que queres obtener");
		String nomenclaturaCriptoDestino = in.nextLine();

		StockDAOJDBC stockDAOJDBC = new StockDAOJDBC();
		stockcriptomoneda2 = stockDAOJDBC.buscarStock(data, nomenclaturaCriptoDestino);

		if (stockcriptomoneda2 == null) {
			System.out.println("No existe esta criptomoneda en el stock");
		}

		System.out.println("Cuantas monedas " + activoCriptomoneda1.getMoneda().getNomenclatura()
				+ " queres transformar a " + stockcriptomoneda2.getMoneda().getNomenclatura() + "?");
		double cantidadCripto1 = in.nextDouble();
		in.nextLine();

		if (cantidadCripto1 > activoCriptomoneda1.getCantidad()) {
			System.out.println("No tenes suficientes criptomonedas");
			return;
		}
		MonedaDAOJDBC mDAOJDBC = new MonedaDAOJDBC();
		monedaCripto1 = mDAOJDBC.buscarCriptomoneda(data, nomenclaturaCriptoOrigen);
		monedaCripto2 = mDAOJDBC.buscarCriptomoneda(data, nomenclaturaCriptoDestino);

		double cantidadCripto2 = monedaCripto1.getPrecio() * cantidadCripto1 / monedaCripto2.getPrecio();
		if (cantidadCripto2 > stockcriptomoneda2.getCantidadActual()) {
			System.out.println("No hay suficiente stock");
			return;
		}

		System.out.println("Vas a gastar " + cantidadCripto1 + " en " + cantidadCripto2);

		System.out.println("Desea confirmar?");
		System.out.println("Ingrese 1 para confirmar");
		System.out.println("Otro - Cancelar");
		int confirmacion = in.nextInt();
		in.nextLine();

		if (confirmacion != 1) {
			System.out.println("Operación cancelada.");
			return;
		}

		// Eliminar cripto1 al usuario
		activoCriptomoneda1.setCantidad(activoCriptomoneda1.getCantidad() - cantidadCripto1);
		activoCriptoDAOJDBC.actualizarActivoCripto(data, activoCriptomoneda1);

		// Eliminar cripto2 al stock

		stockcriptomoneda2.setCantidadActual(stockcriptomoneda2.getCantidadActual() - cantidadCripto2);
		stockDAOJDBC.actualizarStock(data, stockcriptomoneda2);

		// Agregar cripto2 al usuario

		activoCriptomoneda2 = activoCriptoDAOJDBC.buscarActivoCripto(data, nomenclaturaCriptoDestino);
		if (activoCriptomoneda2 == null) { // No existe
			activoCriptomoneda2 = new Activo(cantidadCripto2, new Moneda(nomenclaturaCriptoDestino));
			activoCriptoDAOJDBC.insertarActivoCripto(data, activoCriptomoneda2);
		} else {
			activoCriptomoneda2.setCantidad(activoCriptomoneda2.getCantidad() + cantidadCripto2);
			activoCriptoDAOJDBC.actualizarActivoCripto(data, activoCriptomoneda2);
		}

		// Agregar cripto1 al stock

		stockcriptomoneda1 = stockDAOJDBC.buscarStock(data, nomenclaturaCriptoOrigen);
		stockcriptomoneda1.setCantidadActual(stockcriptomoneda1.getCantidadActual() + cantidadCripto1);
		stockDAOJDBC.actualizarStock(data, stockcriptomoneda1);

	}

}
