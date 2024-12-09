package funcionesAplicacion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Persona;
import clases.Stock;
import clases.Transaccion;
import clases.UnidadDeCompra;
import clases.Usuario;
import daos.ActivoCriptoDAO;
import daos.ActivoFIATDAO;
import daos.FactoryDAO;
import daos.MonedaDAO;
import daos.PersonaDAO;
import daos.StockDAO;
import daos.TransaccionDAO;
import daos.UsuarioDAO;

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

	public static List<ActivoCripto> listarActivosCripto(Usuario usuario) {

		List<ActivoCripto> activosCripto;

		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();

		try {
			activosCripto = activoCriptoDAO.listarActivoCriptoUsuario(usuario.getIdUsuario());
			return activosCripto;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return null;
		}
	}

	public static List<ActivoFIAT> listarActivosFIAT(Usuario usuario) {

		List<ActivoFIAT> activosFIAT;

		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();

		try {
			activosFIAT = activoFIATDAO.listarActivoFIAT(usuario.getIdUsuario());
			return activosFIAT;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return null;
		}
	}

	public static List<Transaccion> listarTransacciones(Usuario usuario) {

		List<Transaccion> transacciones;

		TransaccionDAO<Transaccion> transaccionDAO = FactoryDAO.getTransaccionDAO();

		try {
			transacciones = transaccionDAO.listarTransacciones(usuario.getIdUsuario());
			return transacciones;
		} catch (SQLException e) {
			System.err.println("Error" + e.getMessage());
			return null;
		}
	}
	
	public static double calcularEquivalenciaEnUSD(Moneda monedaAComprar, Moneda monedaAUsar) {
		
		return monedaAComprar.getValorDolar() / monedaAUsar.getValorDolar();
	}

	public static double calcularBalanceEnDolaresDeUsuario(Usuario usuario) {

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
			throws SQLException {

		stockCripto = stockDAO.buscarStock(unidadDeCompra.getCriptomoneda().getNomenclatura());
		// Si entra al if es porque aún no había stock de esta cripto en el sistema
		if (stockCripto == null) {
			stockCripto = new Stock();
			stockCripto.setCantidad(unidadDeCompra.getCantidadCriptomoneda());
			stockCripto.setMoneda(unidadDeCompra.getCriptomoneda());
			stockDAO.insertarStock(stockCripto);
		}
		// Si no, ya estaba creado y se actualizan las cantidades
		else {
			stockCripto.setCantidad(stockCripto.getCantidad() - unidadDeCompra.getCantidadCriptomoneda());
			stockDAO.actualizarStock(stockCripto);
		}
	}

	// Resta el activo de Fiat en la billetera del usuario
	private static void restarActivoFIATUsuario(ActivoFIAT activoFIAT, UnidadDeCompra unidadDeCompra,
			ActivoFIATDAO<ActivoFIAT> activoFIATDAO) throws SQLException {
		if (activoFIAT == null)
			System.out.println("El usuario no posee " + unidadDeCompra.getFiat().getNombre() + ".");

		else if ((activoFIAT.getCantidad() - unidadDeCompra.getCantidadFIAT()) < 0)
			System.out.println(
					"El usuario no posee suficiente " + unidadDeCompra.getFiat().getNombre() + " para la transacción.");

		else {
			// Restar activo de FIAT al usuario
			activoFIAT.setCantidad(activoFIAT.getCantidad() - unidadDeCompra.getCantidadFIAT());
			activoFIATDAO.actualizarActivoFIAT(activoFIAT);
		}
	}

	public static Transaccion comprarCriptomoneda(UnidadDeCompra unidadDeCompra){

		// Variables
		Transaccion transaccion;
		String mensaje;
		ActivoCripto activoCripto;
		ActivoFIAT activoFIAT;
		Stock stockFIAT = null;
		Stock stockCripto = null;

		// DAOS
		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();
		StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();
		TransaccionDAO<Transaccion> transaccionDAO = FactoryDAO.getTransaccionDAO();

		try {

			activoFIAT = activoFIATDAO.buscarActivoFIAT(unidadDeCompra.getUsuario().getIdUsuario(),
					unidadDeCompra.getFiat().getIdMoneda());

			activoCripto = activoCriptoDAO.buscarActivoCripto(unidadDeCompra.getUsuario().getIdUsuario(),
					unidadDeCompra.getCriptomoneda().getIdMoneda());
			
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

			transaccion = new Transaccion(-1, mensaje, new Date(System.currentTimeMillis()),
					unidadDeCompra.getUsuario());
						
			System.out.println(transaccion.getUsuario().getIdUsuario());

			transaccionDAO.insertarTransaccion(transaccion);

			return transaccion;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
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
