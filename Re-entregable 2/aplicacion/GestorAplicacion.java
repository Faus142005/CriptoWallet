package aplicacion;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import clases.Activo;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Stock;
import clases.Transaccion;
import comparators.activo.ComparadorCantidadActivo;
import comparators.activo.ComparadorNomenclaturaActivo;
import comparators.moneda.ComparadorNomenclaturaMoneda;
import comparators.moneda.ComparadorPrecioEnDolar;
import comparators.stock.ComparadorCantidadStock;
import comparators.stock.ComparadorNomenclaturaStock;
import interfacesDAO.Activo_CriptoDAO;
import interfacesDAO.Activo_FIATDAO;
import interfacesDAO.MonedaDAO;
import interfacesDAO.MonedaDAO.TipoDeMoneda;
import interfacesDAO.StockDAO;
import interfacesDAO.TransaccionDAO;

public final class GestorAplicacion {


	private static Scanner in;

	static {
		in = new Scanner(System.in);
	}

	public static void openScanner() {
		in = new Scanner(System.in);
	}

	public static void closeScanner() {
		in.close();
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

		System.out.println("Ingrese el precio de " + nombre + " en dolares");
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
		MonedaDAO<Moneda> mDAO = FactoryDao.getMonedaDAO();
		StockDAO<Stock> sDAO = FactoryDao.getStockDAO();

		Stock s = new Stock(cantidad, c);
		mDAO.insertarCriptomoneda(c);
		sDAO.insertarStock(s);
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

		MonedaDAO<Moneda> mDAO = FactoryDao.getMonedaDAO();
		StockDAO<Stock> sDAO = FactoryDao.getStockDAO();

		Stock s = new Stock(cantidad, f);
		mDAO.insertarFIAT(f);
		sDAO.insertarStock(s);
		System.out.println("La moneda fue creada exitosamente");

	}

	public static List<Moneda> listarMonedasPrecioEnDolar() throws SQLException {
		MonedaDAO<Moneda> mDAO = FactoryDao.getMonedaDAO();
		List<Moneda> listaDeMonedas = mDAO.listarMonedas();
		Collections.sort(listaDeMonedas, new ComparadorPrecioEnDolar());
		return listaDeMonedas;
	}

	public static List<Moneda> listarMonedasNomenclatura() throws SQLException {
		MonedaDAO<Moneda> mDAO = FactoryDao.getMonedaDAO();
		List<Moneda> listaDeMonedas = mDAO.listarMonedas();
		Collections.sort(listaDeMonedas, new ComparadorNomenclaturaMoneda());
		return listaDeMonedas;
	}

	// 3 y 4

	public static void generarStock() throws SQLException {

		StockDAO<Stock> sDAO = FactoryDao.getStockDAO();
		sDAO.generarStock();
		System.out.println("Stock generado aleatoriamente para todas las criptomonedas.");

	}

	public static List<Stock> listarStockNomenclatura() throws SQLException {

		StockDAO<Stock> sDAO = FactoryDao.getStockDAO();
		List<Stock> listaDeStock = sDAO.listarStock();
		Collections.sort(listaDeStock, new ComparadorNomenclaturaStock());
		return listaDeStock;

	}

	public static List<Stock> listarStockCantidad() throws SQLException {

		StockDAO<Stock> sDAO = FactoryDao.getStockDAO();
		List<Stock> listaDeStock = sDAO.listarStock();
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

		// Ya que confirmo se crea las variables DAOS y las usa

		MonedaDAO<Moneda> mDAO = FactoryDao.getMonedaDAO();

		// Comienza a comunicarse con la base de datos

		TipoDeMoneda resul = mDAO.buscarMonedaPorNomenclatura(nomenclatura); // Ver si existe la moneda

		if (resul.equals(TipoDeMoneda.NULO)) {
			System.out.println("ERROR: La moneda " + nomenclatura + " no existe en la base de datos");
			return;
		}

		if (resul.equals(TipoDeMoneda.CRIPTOMONEDA)) {

			Activo_CriptoDAO<Activo> activoCriptoDAO = FactoryDao.getActivoCriptoDAO();
			Activo activo = activoCriptoDAO.buscarActivoCripto(nomenclatura);

			if (activo != null) { // Si ya la tiene en su billetera
				activo.setCantidad(activo.getCantidad() + cantidad);
				activoCriptoDAO.actualizarActivoCripto(activo);
			}

			else { // Si no la tiene
				activo = new Activo(cantidad, new Moneda(nomenclatura));
				activoCriptoDAO.insertarActivoCripto(activo);
			}
		}

		else {
			Activo_FIATDAO<Activo> activoFIATDAO = FactoryDao.getActivoFIATDAO();
			Activo activo = activoFIATDAO.buscarActivoFIAT(nomenclatura);
			if (activo != null) {// Si ya la tiene en su billetera
				activo.setCantidad(activo.getCantidad() + cantidad);
				activoFIATDAO.actualizarActivoFIAT(activo);
			}

			else {// Si no la tiene
				activo = new Activo(cantidad, new Moneda(nomenclatura));
				activoFIATDAO.insertarActivoFIAT(activo);
			}
		}

		System.out.println("El activo fue guardado exitosamente");
	}

	public static List<Activo> listarActivoNomenclatura() throws SQLException {

		Activo_CriptoDAO<Activo> activoCriptoDAO = FactoryDao.getActivoCriptoDAO();
		Activo_FIATDAO<Activo> activoFIATDAO = FactoryDao.getActivoFIATDAO();

		List<Activo> listaActivosTotales = new LinkedList<Activo>();
		listaActivosTotales.addAll(activoCriptoDAO.listarActivoCripto());
		listaActivosTotales.addAll(activoFIATDAO.listarActivoFIAT());

		Collections.sort(listaActivosTotales, new ComparadorNomenclaturaActivo());
		return listaActivosTotales;

	}

	public static List<Activo> listarActivoCantidad() throws SQLException {

		Activo_CriptoDAO<Activo> activoCriptoDAO = FactoryDao.getActivoCriptoDAO();
		Activo_FIATDAO<Activo> activoFIATDAO = FactoryDao.getActivoFIATDAO();

		List<Activo> listaActivosTotales = new LinkedList<Activo>();
		listaActivosTotales.addAll(activoCriptoDAO.listarActivoCripto());
		listaActivosTotales.addAll(activoFIATDAO.listarActivoFIAT());

		Collections.sort(listaActivosTotales, new ComparadorCantidadActivo());
		return listaActivosTotales;

	}

	// 7 y 8

	public static void comprarCriptomoneda() throws SQLException {

		// Variables

		FIAT fiat;
		Criptomoneda criptomoneda;
		Activo activoCriptoDelUsuario;
		Activo activoFIATdelUsuario;
		Stock stockFIAT;
		Stock stockCripto;
		String mensaje;
		Transaccion transaccion;

		// Nomenclaturas

		String criptoNomenclatura;
		String fiatNomenclatura;

		// Gestores

		MonedaDAO<Moneda> mDAO = FactoryDao.getMonedaDAO();
		Activo_CriptoDAO<Activo> acDAO = FactoryDao.getActivoCriptoDAO();
		Activo_FIATDAO<Activo> afDAO = FactoryDao.getActivoFIATDAO();
		StockDAO<Stock> sDAO = FactoryDao.getStockDAO();
		TransaccionDAO tDAO = FactoryDao.getTransaccionDAO();

		//

		System.out.println("Ingrese la nomenclatura de la criptomoneda a comprar");
		criptoNomenclatura = in.nextLine();
		criptomoneda = mDAO.buscarCriptomoneda(criptoNomenclatura);

		if (criptomoneda == null) {
			System.out.println("No existe esa criptomoneda");
			return;
		}

		System.out.println("Ingrese la nomenclatura de la moneda FIAT");
		fiatNomenclatura = in.nextLine();

		activoFIATdelUsuario = afDAO.buscarActivoFIAT(fiatNomenclatura);
		fiat = mDAO.buscarFIAT(fiatNomenclatura);

		if (activoFIATdelUsuario == null || fiat == null) {
			System.out.println("No tenes esa moneda");
			return;
		}

		System.out.println("Ingrese la cantidad disponible de " + fiatNomenclatura);
		double cantidadDisponible = in.nextDouble();

		if (activoFIATdelUsuario.getCantidad() < cantidadDisponible) {
			System.out.println("No tenes esa cantidad de monedas");
			return;
		}

		double cantidadCriptoAComprar = fiat.getPrecio() * cantidadDisponible / criptomoneda.getPrecio();

		stockCripto = sDAO.buscarStock(criptoNomenclatura);

		if (stockCripto.getCantidadActual() < cantidadCriptoAComprar) {
			System.out.println("No hay stock disponible");
		}

		// Ya se tienen todos los datos asi que se procesa si se confirma

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

		activoCriptoDelUsuario = acDAO.buscarActivoCripto(criptoNomenclatura);

		boolean tieneElActivo = activoCriptoDelUsuario != null;

		// SUMAR CRIPTOMONEDA AL USUARIO

		if (tieneElActivo) {
			Activo a = new Activo();
			a.setCantidad(cantidadCriptoAComprar + activoCriptoDelUsuario.getCantidad());
			a.setMoneda(criptomoneda);
			acDAO.actualizarActivoCripto(a);
		} else {
			Activo a = new Activo();
			a.setCantidad(cantidadCriptoAComprar);
			a.setMoneda(criptomoneda);
			acDAO.insertarActivoCripto(a);
		}

		// SUMAR FIAT AL SISTEMA
		stockFIAT = sDAO.buscarStock(fiatNomenclatura);
		stockFIAT.setCantidadActual(stockFIAT.getCantidadActual() + cantidadDisponible);
		sDAO.actualizarStock(stockFIAT);

		// RESTAR FIAT AL USUARIO
		activoFIATdelUsuario.setCantidad(activoFIATdelUsuario.getCantidad() - cantidadDisponible);
		afDAO.actualizarActivoFIAT(activoFIATdelUsuario);

		// RESTAR STOCK DE CRIPTO AL SISTEMA
		stockCripto.setCantidadActual(stockCripto.getCantidadActual() - cantidadCriptoAComprar);
		sDAO.actualizarStock(stockCripto);

		// Agregar Transaccion
		mensaje = "Compra | Moneda dada: " + fiatNomenclatura + " | Cantidad: " + cantidadDisponible
				+ " | Moneda recibida: " + criptoNomenclatura + " | Cantidad: " + cantidadCriptoAComprar;

		transaccion = new Transaccion(mensaje, new Date(System.currentTimeMillis()));
		tDAO.insertarTransaccion(transaccion);
	}

	public static void swap() throws SQLException {

		// Variables

		Activo activoCriptomonedaADar;
		Activo activoCriptomonedaARecibir;
		Stock stockCriptomonedaADar;
		Stock stockCriptomonedaARecibir;
		Moneda monedaCriptoADar;
		Moneda monedaCripto2;
		Transaccion transaccion;
		String mensaje;

		// Gestores
		Activo_CriptoDAO<Activo> activoCriptoDAO = FactoryDao.getActivoCriptoDAO();
		StockDAO<Stock> stockDAO = FactoryDao.getStockDAO();
		MonedaDAO<Moneda> mDAO = FactoryDao.getMonedaDAO();
		TransaccionDAO tDAO = FactoryDao.getTransaccionDAO();

		//

		System.out.println("Ingrese la nomenclatura de la criptomoneda que queres dar");
		String nomenclaturaCriptoOrigen = in.nextLine();

		activoCriptomonedaADar = activoCriptoDAO.buscarActivoCripto(nomenclaturaCriptoOrigen);

		if (activoCriptomonedaADar == null) {
			System.out.println("No tenes esta criptomoneda");
			return;
		}

		System.out.println("Ingrese la nomenclatura de la criptomoneda que queres obtener");
		String nomenclaturaCriptoDestino = in.nextLine();

		stockCriptomonedaARecibir = stockDAO.buscarStock(nomenclaturaCriptoDestino);

		if (stockCriptomonedaARecibir == null) {
			System.out.println("No existe esta criptomoneda en el stock");
			return;
		}

		// Todos los datos agarrados

		System.out.println("Cuantas monedas " + activoCriptomonedaADar.getMoneda().getNomenclatura()
				+ " queres transformar a " + stockCriptomonedaARecibir.getMoneda().getNomenclatura() + "?");
		double cantidadCripto1 = in.nextDouble();
		in.nextLine();

		if (cantidadCripto1 > activoCriptomonedaADar.getCantidad()) {
			System.out.println("No tenes suficientes criptomonedas");
			return;
		}
		monedaCriptoADar = mDAO.buscarCriptomoneda(nomenclaturaCriptoOrigen);
		monedaCripto2 = mDAO.buscarCriptomoneda(nomenclaturaCriptoDestino);

		double cantidadCripto2 = monedaCriptoADar.getPrecio() * cantidadCripto1 / monedaCripto2.getPrecio();
		if (cantidadCripto2 > stockCriptomonedaARecibir.getCantidadActual()) {
			System.out.println("No hay suficiente stock");
			return;
		}

		System.out.println("Vas a gastar " + cantidadCripto1 + " " + nomenclaturaCriptoOrigen + " en " + cantidadCripto2
				+ " " + nomenclaturaCriptoDestino);

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
		activoCriptomonedaADar.setCantidad(activoCriptomonedaADar.getCantidad() - cantidadCripto1);
		activoCriptoDAO.actualizarActivoCripto(activoCriptomonedaADar);

		// Eliminar cripto2 al stock

		stockCriptomonedaARecibir.setCantidadActual(stockCriptomonedaARecibir.getCantidadActual() - cantidadCripto2);
		stockDAO.actualizarStock(stockCriptomonedaARecibir);

		// Agregar cripto2 al usuario

		activoCriptomonedaARecibir = activoCriptoDAO.buscarActivoCripto(nomenclaturaCriptoDestino);
		if (activoCriptomonedaARecibir == null) { // No existe
			activoCriptomonedaARecibir = new Activo(cantidadCripto2, new Moneda(nomenclaturaCriptoDestino));
			activoCriptoDAO.insertarActivoCripto(activoCriptomonedaARecibir);
		} else {
			activoCriptomonedaARecibir.setCantidad(activoCriptomonedaARecibir.getCantidad() + cantidadCripto2);
			activoCriptoDAO.actualizarActivoCripto(activoCriptomonedaARecibir);
		}

		// Agregar cripto1 al stock

		stockCriptomonedaADar = stockDAO.buscarStock(nomenclaturaCriptoOrigen);
		stockCriptomonedaADar.setCantidadActual(stockCriptomonedaADar.getCantidadActual() + cantidadCripto1);
		stockDAO.actualizarStock(stockCriptomonedaADar);

		// Agregar Transaccion
		mensaje = "Swap | Moneda dada: " + nomenclaturaCriptoOrigen + " | Cantidad: " + cantidadCripto1
				+ " | Moneda recibida: " + nomenclaturaCriptoDestino + " | Cantidad: " + cantidadCripto2;

		transaccion = new Transaccion(mensaje, new Date(System.currentTimeMillis()));
		tDAO.insertarTransaccion(transaccion);
	}
}
