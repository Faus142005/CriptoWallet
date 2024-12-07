package aplicacion;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Transaccion;
import clases.UnidadDeCompra;
import clases.Usuario;
import daos.ActivoCriptoDAO;
import daos.ActivoFIATDAO;
import daos.CriptomonedaDAO;
import daos.FIATDAO;
import daos.FactoryDAO;

public class FuncionesDeLaAplicacion {

	private static HashMap<String, ImageIcon> iconosCriptomoneda = new HashMap<>();
	private static HashMap<String, ImageIcon> iconosFIAT = new HashMap<>();

	public static void cargarImagenesCriptomonedas() {
		iconosCriptomoneda.clear();
		List<Criptomoneda> monedas = new ArrayList<>();
		monedas.add(new Criptomoneda(1, "Bitcoin", "BTC", 50000.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Etherium", "ETH", 10.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "DogeCoin", "DOGE", 55.5555555551,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Solana", "SOL", 250.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Ripple", "XRP", 1.20,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Ripple.png", 0));
		monedas.add(new Criptomoneda(1, "Litecoin", "LTC", 180.5,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Litecoin.png", 0));
		monedas.add(new Criptomoneda(1, "Cardano", "ADA", 0.45,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Cardano.png", 0));
		monedas.add(new Criptomoneda(1, "Polkadot", "DOT", 7.50,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Polkadot.png", 0));
		monedas.add(new Criptomoneda(1, "Chainlink", "LINK", 30.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Chainlink.png", 0));
		monedas.add(new Criptomoneda(1, "Bitcoin Cash", "BCH", 700.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/BitcoinCash.png", 0));
		monedas.add(new Criptomoneda(1, "Stellar", "XLM", 0.25,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Stellar.png", 0));
		monedas.add(new Criptomoneda(1, "TRON", "TRX", 0.08,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Tron.png", 0));
		monedas.add(new Criptomoneda(1, "VeChain", "VET", 0.10,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/VeChain.png", 0));
		monedas.add(new Criptomoneda(1, "Shiba Inu", "SHIB", 0.000007,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/ShibaInu.png", 0));

		for (Criptomoneda c : monedas) {
			iconosCriptomoneda.put(c.getNomenclatura(), new ImageIcon(c.getNombreIcono()));
		}
	}

	public static HashMap<String, ImageIcon> obtenerIconsDeCriptomonedas() {

		return iconosCriptomoneda;
	}
	
	public static void cargarImagenesFIAT() {
		
	}

	public static HashMap<String, ImageIcon> obtenerIconsDeFIATS(){
		return iconosFIAT;
	}
	
	public static List<Criptomoneda> listarCriptomonedas() {

		List<Criptomoneda> monedas = new ArrayList<>();
		monedas.add(new Criptomoneda(1, "Bitcoin", "BTC", 50000.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Etherium", "ETH", 10.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "DogeCoin", "DOGE", 55.5555555551,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Solana", "SOL", 250.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Ripple", "XRP", 1.20,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Ripple.png", 0));
		monedas.add(new Criptomoneda(1, "Litecoin", "LTC", 180.5,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Litecoin.png", 0));
		monedas.add(new Criptomoneda(1, "Cardano", "ADA", 0.45,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Cardano.png", 0));
		monedas.add(new Criptomoneda(1, "Polkadot", "DOT", 7.50,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Polkadot.png", 0));
		monedas.add(new Criptomoneda(1, "Chainlink", "LINK", 30.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Chainlink.png", 0));
		monedas.add(new Criptomoneda(1, "Bitcoin Cash", "BCH", 700.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/BitcoinCash.png", 0));
		monedas.add(new Criptomoneda(1, "Stellar", "XLM", 0.25,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Stellar.png", 0));
		monedas.add(new Criptomoneda(1, "TRON", "TRX", 0.08,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Tron.png", 0));
		monedas.add(new Criptomoneda(1, "VeChain", "VET", 0.10,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/VeChain.png", 0));
		monedas.add(new Criptomoneda(1, "Shiba Inu", "SHIB", 0.000007,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/ShibaInu.png", 0));
		return monedas;
	}

	public static List <FIAT> listarFIATS(){
		return new LinkedList<FIAT>();
	}
	
	public static List<ActivoCripto> listarActivosCripto(Usuario usuario) {

		Criptomoneda bitcoin = new Criptomoneda(1, "Bitcoin", "BTC", 40000.0, "bitcoin_icon.png", 0.05);
		Criptomoneda ethereum = new Criptomoneda(2, "Ethereum", "ETH", 2500.0, "ethereum_icon.png", 0.07);
		Criptomoneda cardano = new Criptomoneda(3, "Cardano", "ADA", 1.2, "cardano_icon.png", 0.10);

		List<ActivoCripto> activosCripto = new ArrayList<>();

		activosCripto.add(new ActivoCripto(1, null, bitcoin, 0.5));
		activosCripto.add(new ActivoCripto(2, null, ethereum, 2.0));
		activosCripto.add(new ActivoCripto(3, null, cardano, 1000.0));

		return activosCripto;
	}

	public static List<ActivoFIAT> listarActivosFIAT(Usuario usuario) {

		List<FIAT> monedasFIAT = new ArrayList<>();

		monedasFIAT.add(new FIAT(1, "Dólar Estadounidense", "USD", 1.0, "usd_icon.png"));
		monedasFIAT.add(new FIAT(2, "Euro", "EUR", 1.1, "eur_icon.png"));
		monedasFIAT.add(new FIAT(3, "Libra Esterlina", "GBP", 1.25, "gbp_icon.png"));
		monedasFIAT.add(new FIAT(4, "Yen Japonés", "JPY", 0.0075, "jpy_icon.png"));
		monedasFIAT.add(new FIAT(5, "Dólar Canadiense", "CAD", 0.75, "cad_icon.png"));
		monedasFIAT.add(new FIAT(6, "Franco Suizo", "CHF", 1.05, "chf_icon.png"));
		monedasFIAT.add(new FIAT(7, "Dólar Australiano", "AUD", 0.65, "aud_icon.png"));
		monedasFIAT.add(new FIAT(8, "Yuan Chino", "CNY", 0.14, "cny_icon.png"));
		monedasFIAT.add(new FIAT(9, "Peso Mexicano", "MXN", 0.05, "mxn_icon.png"));
		monedasFIAT.add(new FIAT(10, "Real Brasileño", "BRL", 0.2, "brl_icon.png"));

		List<ActivoFIAT> activosFIAT = new ArrayList<>();

		activosFIAT.add(new ActivoFIAT(1, null, monedasFIAT.get(0), 1000.0)); // USD
		activosFIAT.add(new ActivoFIAT(2, null, monedasFIAT.get(1), 800.0)); // EUR
		activosFIAT.add(new ActivoFIAT(3, null, monedasFIAT.get(2), 500.0)); // GBP
		activosFIAT.add(new ActivoFIAT(4, null, monedasFIAT.get(3), 100000.0)); // JPY
		activosFIAT.add(new ActivoFIAT(5, null, monedasFIAT.get(4), 1500.0)); // CAD
		activosFIAT.add(new ActivoFIAT(6, null, monedasFIAT.get(5), 2000.0)); // CHF

		return activosFIAT;
	}

	public static double calcularBalanceEnDolaresDeUsuario(Usuario usuario){

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

		// Acá antes habías tirado un finally, pero eclipse me daba que cerraba mal el
		// bloque del finally (?

	}

	public static Transaccion comprarCriptomoneda(UnidadDeCompra unidadDeCompra) throws SQLException {

		// Variables
		Transaccion transaccion;
		String mensaje;
		ActivoCripto activoCripto;
		ActivoFIAT activoFIAT;
		FIAT fiatResultado = new FIAT(unidadDeCompra.getFiat());
		Criptomoneda criptomonedaResultado = new Criptomoneda(unidadDeCompra.getCriptomoneda());

		// DAOS
		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();
		FIATDAO<FIAT> fiatDAO = FactoryDAO.getFiatDAO();
		CriptomonedaDAO<Criptomoneda> criptomonedaDAO = FactoryDAO.getCriptomonedaDAO();

		try {

			activoFIAT = activoFIATDAO.buscarActivoFIAT(unidadDeCompra.getUsuario().getIdUsuario(),
					unidadDeCompra.getFiat().getIdMoneda());

			activoCripto = activoCriptoDAO.buscarActivoCripto(unidadDeCompra.getUsuario().getIdUsuario(),
					unidadDeCompra.getCriptomoneda().getIdMoneda());

			// Sumar Criptomoneda al usuario

			if (activoCripto == null) { // Crea el activo

				activoCripto = new ActivoCripto();
				activoCripto.setCantidad(unidadDeCompra.getCantidadCriptomoneda());
				activoCripto.setUsuario(unidadDeCompra.getUsuario());

				activoCriptoDAO.insertarActivoCripto(activoCripto);
			} else {
				activoCripto.setCantidad(unidadDeCompra.getCantidadCriptomoneda() + activoCripto.getCantidad());

				activoCriptoDAO.actualizarActivoCriptoConID(activoCripto);
			}

			// Sumar FIAT a la base de datos
			fiatResultado.setStock(fiatResultado.getStock() + unidadDeCompra.getCantidadFIAT());
			fiatDAO.actualizarFIAT(fiatResultado);

			// Restar FIAT al usuario
			activoFIAT.setCantidad(activoFIAT.getCantidad() - unidadDeCompra.getCantidadFIAT());

			activoFIATDAO.actualizarActivoFIAT(activoFIAT);

			// Restar Criptomoneda a la base de datos

			criptomonedaResultado.setStock(criptomonedaResultado.getStock() - unidadDeCompra.getCantidadCriptomoneda());
			criptomonedaDAO.actualizarCriptomoneda(criptomonedaResultado);

			mensaje = "Compra de criptomonedas\n" + "Criptomoneda: "
					+ unidadDeCompra.getCriptomoneda().getNomenclatura() + "\n" + "Cantidad de la criptomoneda: "
					+ unidadDeCompra.getCantidadCriptomoneda() + "\n" + "FIAT: "
					+ unidadDeCompra.getFiat().getNomenclatura() + "\n" + "Cantidad de la FIAT: "
					+ unidadDeCompra.getCantidadFIAT() + "\n" + "Precio por cada criptomoneda: "
					+ unidadDeCompra.getCantidadFIAT() / unidadDeCompra.getCantidadCriptomoneda();

			transaccion = new Transaccion(-1, mensaje, new Date(System.currentTimeMillis()),
					unidadDeCompra.getUsuario());

			return transaccion;
		} finally {
			return null;
		}
	}

	public static boolean exportarCSV() {
		
		return false;
	}
}
