package funcionesAplicacion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Stock;
import clases.Transaccion;
import clases.UnidadDeCompra;
import clases.Usuario;
import daos.ActivoCriptoDAO;
import daos.ActivoFIATDAO;
import daos.FactoryDAO;
import daos.MonedaDAO;
import daos.StockDAO;
import daos.TransaccionDAO;
import excepciones.ExcepcionRara;
import excepciones.FondosInsuficientesException;
import excepciones.StockInsuficienteException;

public class FuncionesDeLaAplicacion {

	public static List<Criptomoneda> listarCriptomonedas() {

		List<Criptomoneda> criptomonedas = new LinkedList<Criptomoneda>();

		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
		try {
			criptomonedas = monedaDAO.listarCriptomonedas();
			return criptomonedas;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return null;
		}
	}

	public static List<FIAT> listarFIATS() {
		List<FIAT> fiats;

		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
		try {
			fiats = monedaDAO.listarFIATS();
			return fiats;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return null;
		}
	}

	public static List<Stock> listarStock() {
		List<Stock> stock;

		StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();
		try {
			stock = stockDAO.listarStock();
			return stock;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return null;
		}
	}

	public static List<ActivoCripto> listarActivosCripto(Usuario usuario) {

		if (usuario == null)
			return new LinkedList<ActivoCripto>();

		List<ActivoCripto> activosCripto;

		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();

		try {
			activosCripto = activoCriptoDAO.listarActivoCriptoUsuario(usuario.getIdUsuario());
			return activosCripto;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return new LinkedList<ActivoCripto>();
		}
	}

	public static List<ActivoFIAT> listarActivosFIAT(Usuario usuario) {

		if (usuario == null)
			return new LinkedList<ActivoFIAT>();

		List<ActivoFIAT> activosFIAT;

		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();

		try {
			activosFIAT = activoFIATDAO.listarActivoFIAT(usuario.getIdUsuario());
			return activosFIAT;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return new LinkedList<ActivoFIAT>();
		}
	}

	public static HashMap<String, Criptomoneda> mapearCriptos() {

		HashMap<String, Criptomoneda> criptomonedas = new HashMap<String, Criptomoneda>();
		List<Criptomoneda> listaCripto;
		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
		try {
			listaCripto = monedaDAO.listarCriptomonedas();

		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return criptomonedas;
		}

		for (Criptomoneda c : listaCripto)
			criptomonedas.put(c.getNomenclatura(), c);

		return criptomonedas;
	}

	public static HashMap<String, FIAT> mapearFIATS() {

		HashMap<String, FIAT> fiats = new HashMap<String, FIAT>();
		List<FIAT> listaFIAT;
		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
		try {
			listaFIAT = monedaDAO.listarFIATS();
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return fiats;
		}

		for (FIAT f : listaFIAT) {
			fiats.put(f.getNomenclatura(), f);
		}

		return fiats;
	}

	public static HashMap<String, Stock> mapearStock() {
		HashMap<String, Stock> stock = new HashMap<String, Stock>();
		List<Stock> listaStock;

		StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();
		try {
			listaStock = stockDAO.listarStock();
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return stock;
		}

		for (Stock s : listaStock) {
			stock.put(s.getMoneda().getNomenclatura(), s);
		}

		return stock;
	}

	public static HashMap<String, ActivoCripto> mapearActivosCripto(Usuario usuario) {
		HashMap<String, ActivoCripto> activosCripto = new HashMap<String, ActivoCripto>();

		if (usuario == null)
			return activosCripto;

		List<ActivoCripto> listaActivosCripto;

		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();

		try {
			listaActivosCripto = activoCriptoDAO.listarActivoCriptoUsuario(usuario.getIdUsuario());

		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return activosCripto;
		}

		for (ActivoCripto ac : listaActivosCripto) {
			activosCripto.put(ac.getMoneda().getNomenclatura(), ac);
		}

		return activosCripto;
	}

	public static HashMap<String, ActivoFIAT> mapearActivosFIAT(Usuario usuario) {
		HashMap<String, ActivoFIAT> activosFIAT = new HashMap<String, ActivoFIAT>();

		if (usuario == null)
			return activosFIAT;

		List<ActivoFIAT> listaActivosFIAT;

		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();

		try {
			listaActivosFIAT = activoFIATDAO.listarActivoFIAT(usuario.getIdUsuario());

		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return activosFIAT;
		}

		for (ActivoFIAT ac : listaActivosFIAT) {
			activosFIAT.put(ac.getMoneda().getNomenclatura(), ac);
		}

		return activosFIAT;
	}

	public static List<Transaccion> listarTransacciones(Usuario usuario) {

		if (usuario == null)
			return new LinkedList<Transaccion>();

		List<Transaccion> transacciones;

		TransaccionDAO<Transaccion> transaccionDAO = FactoryDAO.getTransaccionDAO();

		try {
			transacciones = transaccionDAO.listarTransacciones(usuario.getIdUsuario());
			return transacciones;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return new LinkedList<Transaccion>();

		}
	}

	public static double calcularEquivalenciaEnUSD(Moneda monedaAComprar, Moneda monedaAUsar) {

		return monedaAComprar.getValorDolar() / monedaAUsar.getValorDolar();
	}

	public static double calcularBalanceEnDolaresDeUsuario(Usuario usuario) {

		if (usuario == null)
			return -1;

		double balance = 0;

		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();

		try {

			List<ActivoCripto> activosCriptomoneda = activoCriptoDAO.listarActivoCriptoUsuario(usuario.getIdUsuario());

			for (ActivoCripto ac : activosCriptomoneda) {
				balance += ac.getMoneda().getValorDolar() * ac.getCantidad();
			}

			List<ActivoFIAT> activosFIAT = activoFIATDAO.listarActivoFIAT(usuario.getIdUsuario());

			for (ActivoFIAT af : activosFIAT) {
				balance += af.getMoneda().getValorDolar() * af.getCantidad();
			}

			return balance;

		} catch (SQLException e) {
			// En caso de excepción, registra el error y devuelve un valor especial.
			System.err.println("Error al calcular el balance en dólares: " + e.getMessage());
			return -1;
		}

	}

	// Sumar activo de Criptomoneda en la billetera del usuario
	private static void sumarActivoCriptoUsuario(ActivoCripto activoCripto, UnidadDeCompra unidadDeCompra,
			ActivoCriptoDAO<ActivoCripto> activoCriptoDAO) throws SQLException {

		if (activoCripto == null) { // Crea el activo

			activoCripto = new ActivoCripto();
			activoCripto.setMoneda(unidadDeCompra.getCriptomoneda());
			activoCripto.setCantidad(unidadDeCompra.getCantidadCriptomoneda());
			activoCripto.setUsuario(unidadDeCompra.getUsuario());

			activoCriptoDAO.insertarActivoCripto(activoCripto);
		} else {
			activoCripto.setCantidad(unidadDeCompra.getCantidadCriptomoneda() + activoCripto.getCantidad());
			activoCriptoDAO.actualizarActivoCriptoConID(activoCripto);
		}
	}

	// Sumar Stock de FIAT a la base de datos
	private static void sumarStockFIATBD(Stock stockFIAT, UnidadDeCompra unidadDeCompra, StockDAO<Stock> stockDAO)
			throws SQLException {

		stockFIAT = stockDAO.buscarStock(unidadDeCompra.getFiat().getNomenclatura());

		// si entra al if es porque todavía no había stock de esta moneda fiat
		if (stockFIAT == null) {

			stockFIAT = new Stock();
			stockFIAT.setCantidad(unidadDeCompra.getCantidadFIAT());
			stockFIAT.setMoneda(unidadDeCompra.getFiat());

			stockDAO.insertarStock(stockFIAT);
		}
		// Si no, ya estaba creado y se actualizan las cantidades
		else {
			stockFIAT.setCantidad(unidadDeCompra.getCantidadFIAT() + stockFIAT.getCantidad());

			stockDAO.actualizarStock(stockFIAT);
		}
	}

	// Resta el Stock de Criptomoneda en la base de datos
	private static void restarStockCriptoBD(Stock stockCripto, UnidadDeCompra unidadDeCompra, StockDAO<Stock> stockDAO)
			throws SQLException, FondosInsuficientesException {

		stockCripto.setCantidad(stockCripto.getCantidad() - unidadDeCompra.getCantidadCriptomoneda());
		stockDAO.actualizarStock(stockCripto);
	}

	// Resta el activo de Fiat en la billetera del usuario
	private static void restarActivoFIATUsuario(ActivoFIAT activoFIAT, UnidadDeCompra unidadDeCompra,
			ActivoFIATDAO<ActivoFIAT> activoFIATDAO) throws SQLException {
		// Restar activo de FIAT al usuario
		activoFIAT.setCantidad(activoFIAT.getCantidad() - unidadDeCompra.getCantidadFIAT());
		activoFIATDAO.actualizarActivoFIAT(activoFIAT);
	}

	public static Transaccion comprarCriptomoneda(UnidadDeCompra unidadDeCompra)
			throws StockInsuficienteException, FondosInsuficientesException, ExcepcionRara {

		// Variables
		Transaccion transaccion;
		String mensaje;
		ActivoCripto activoCripto;
		ActivoFIAT activoFIAT;
		Stock stockFIAT;
		Stock stockCripto;

		// DAOS
		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();
		StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();
		TransaccionDAO<Transaccion> transaccionDAO = FactoryDAO.getTransaccionDAO();

		if (!unidadDeCompra.getUsuario().isTyc()) {
			System.out.println("Debe aceptar los términos y condiciones para realizar una compra.");
			return new Transaccion();
		}

		try {

			activoFIAT = activoFIATDAO.buscarActivoFIAT(unidadDeCompra.getUsuario().getIdUsuario(),
					unidadDeCompra.getFiat().getIdMoneda());

			activoCripto = activoCriptoDAO.buscarActivoCripto(unidadDeCompra.getUsuario().getIdUsuario(),
					unidadDeCompra.getCriptomoneda().getIdMoneda());

			stockFIAT = stockDAO.buscarStock(unidadDeCompra.getFiat().getNomenclatura());

			stockCripto = stockDAO.buscarStock(unidadDeCompra.getCriptomoneda().getNomenclatura());

			if (activoFIAT == null)
				throw new FondosInsuficientesException(
						"No tienes activos de esta fiat: " + unidadDeCompra.getFiat().getNomenclatura());

			if (activoFIAT.getCantidad() < unidadDeCompra.getCantidadFIAT())
				throw new FondosInsuficientesException(
						"No tienes suficientes activos de esta fiat: " + unidadDeCompra.getFiat().getNomenclatura());

			if (stockCripto == null)
				throw new StockInsuficienteException(
						"El sistema no tiene criptomoneda: " + unidadDeCompra.getCriptomoneda().getNomenclatura());

			if (stockCripto.getCantidad() < unidadDeCompra.getCantidadCriptomoneda())
				throw new StockInsuficienteException("El sistema no suficientes criptomonedas: "
						+ unidadDeCompra.getCriptomoneda().getNomenclatura());

			// Suma activo de cripto al usuario
			sumarActivoCriptoUsuario(activoCripto, unidadDeCompra, activoCriptoDAO);
			// Suma stock del FIAT en el sistema
			sumarStockFIATBD(stockFIAT, unidadDeCompra, stockDAO);
			// Resta activo Fiat al usuario
			restarActivoFIATUsuario(activoFIAT, unidadDeCompra, activoFIATDAO);
			// Resta Stock de cripto en el sistema
			restarStockCriptoBD(stockCripto, unidadDeCompra, stockDAO);

			mensaje = "Compra de criptomonedas\n" + "Criptomoneda: "
					+ unidadDeCompra.getCriptomoneda().getNomenclatura() + "\n" + "Cantidad de la criptomoneda: "
					+ unidadDeCompra.getCantidadCriptomoneda() + "\n" + "FIAT: "
					+ unidadDeCompra.getFiat().getNomenclatura() + "\n" + "Cantidad de la FIAT: "
					+ unidadDeCompra.getCantidadFIAT() + "\n" + "Precio por cada criptomoneda: "
					+ unidadDeCompra.getCantidadFIAT() / unidadDeCompra.getCantidadCriptomoneda();

			transaccion = new Transaccion(-1, mensaje, new Timestamp(System.currentTimeMillis()),
					unidadDeCompra.getUsuario());

			System.out.println(transaccion.getUsuario().getIdUsuario());

			transaccionDAO.insertarTransaccion(transaccion);

			return transaccion;

		} catch (SQLException e) {
			throw new ExcepcionRara();
		}
	}

	public static boolean exportarCSV(Usuario usuario) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar archivo");

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection != JFileChooser.APPROVE_OPTION) {
			return false;
		}

		File fileToSave = fileChooser.getSelectedFile();

		String filePath = fileToSave.getAbsolutePath();
		if (!filePath.endsWith(".cvs")) {
			fileToSave = new File(filePath + ".cvs");
		}

		System.out.println("Archivo seleccionado: " + fileToSave.getAbsolutePath());
		// Files.writeString(fileToSave.toPath(), "Contenido del archivo");

		try (FileWriter writer = new FileWriter(fileToSave)) {

			List<ActivoFIAT> activosFIAT = FuncionesDeLaAplicacion.listarActivosFIAT(usuario);
			List<ActivoCripto> activosCripto = FuncionesDeLaAplicacion.listarActivosCripto(usuario);
			// Se puede eliminar el id usuario...
			writer.append("ID USUARIO");
			writer.append(",");
			writer.append("TIPO DE ACTIVO");
			writer.append(",");
			writer.append("MONEDA");
			writer.append(",");
			writer.append("CANTIDAD");
			writer.append('\n');

			for (ActivoFIAT activoFIAT : activosFIAT) {
				writer.append(String.valueOf(activoFIAT.getUsuario().getIdUsuario()));
				writer.append(",");
				writer.append("F");
				writer.append(",");
				writer.append(activoFIAT.getMoneda().getNomenclatura());
				writer.append(",");
				writer.append(String.valueOf(activoFIAT.getCantidad()));
				writer.append('\n');
			}

			for (ActivoCripto activoCripto : activosCripto) {
				writer.append(String.valueOf(activoCripto.getUsuario().getIdUsuario()));
				writer.append(",");
				writer.append("C");
				writer.append(",");
				writer.append(activoCripto.getMoneda().getNomenclatura());
				writer.append(",");
				writer.append(String.valueOf(activoCripto.getCantidad()));
				writer.append('\n');
			}

			writer.close();

			JOptionPane.showMessageDialog(null, "Archivo guardado en: " + fileToSave.getAbsolutePath(),
					"Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
