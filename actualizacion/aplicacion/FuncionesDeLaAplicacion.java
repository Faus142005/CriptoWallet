package aplicacion;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

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

		activosFIAT.add(new ActivoFIAT(1, null, monedasFIAT.get(0), 1000.0)); 	// USD
		activosFIAT.add(new ActivoFIAT(2, null, monedasFIAT.get(1), 800.0));	// EUR
		activosFIAT.add(new ActivoFIAT(3, null, monedasFIAT.get(2), 500.0)); 	// GBP
		activosFIAT.add(new ActivoFIAT(4, null, monedasFIAT.get(3), 100000.0)); // JPY
		activosFIAT.add(new ActivoFIAT(5, null, monedasFIAT.get(4), 1500.0)); 	// CAD
		activosFIAT.add(new ActivoFIAT(6, null, monedasFIAT.get(5), 2000.0)); 	// CHF

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

	}
	
	// Sumar activo de Criptomoneda en la billetera del usuario
	private static void sumarActivoCriptoUsuario(ActivoCripto activoCripto, UnidadDeCompra unidadDeCompra, ActivoCriptoDAO<ActivoCripto> activoCriptoDAO) throws SQLException {
		
		if (activoCripto == null) { // Crea el activo

			activoCripto = new ActivoCripto();
			activoCripto.setCantidad(unidadDeCompra.getCantidadCriptomoneda());
			activoCripto.setUsuario(unidadDeCompra.getUsuario());

			activoCriptoDAO.insertarActivoCripto(activoCripto);
		} else {
			activoCripto.setCantidad(unidadDeCompra.getCantidadCriptomoneda() + activoCripto.getCantidad());

			activoCriptoDAO.actualizarActivoCriptoConID(activoCripto);
		}
	}			
	
	// Sumar Stock de FIAT a la base de datos
	private static void sumarStockFIATBD(Stock stockFIAT, UnidadDeCompra unidadDeCompra, StockDAO<Stock> stockDAO) throws SQLException {
		
		stockFIAT = stockDAO.buscarStock(unidadDeCompra.getFiat().getNomenclatura());
		
		//si entra al if es porque todavía no había stock de esta moneda fiat
		if (stockFIAT == null) {
			
			stockFIAT = new Stock();
			stockFIAT.setCantidad(unidadDeCompra.getCantidadFIAT());
			stockFIAT.setMoneda(unidadDeCompra.getFiat());
						
			stockDAO.insertarStock(stockFIAT);
		}
		//Si no, ya estaba creado y se actualizan las cantidades
		else {
			stockFIAT.setCantidad(unidadDeCompra.getCantidadFIAT() + stockFIAT.getCantidad());
			
			stockDAO.actualizarStock(stockFIAT);
		}				
	}
	
	//Resta el Stock de Criptomoneda en la base de datos
	private static void restarStockCriptoBD(Stock stockCripto, UnidadDeCompra unidadDeCompra, StockDAO<Stock> stockDAO) throws SQLException {		
		
		stockCripto = stockDAO.buscarStock(unidadDeCompra.getCriptomoneda().getNomenclatura());
		//Si entra al if es porque aún no había stock de esta cripto en el sistema
		if(stockCripto == null) {
			stockCripto = new Stock();
			stockCripto.setCantidad(unidadDeCompra.getCantidadCriptomoneda());
			stockCripto.setMoneda(unidadDeCompra.getCriptomoneda());
			
			stockDAO.insertarStock(stockCripto);
		}
		//Si no, ya estaba creado y se actualizan las cantidades
		else {
			stockCripto.setCantidad(stockCripto.getCantidad() - unidadDeCompra.getCantidadCriptomoneda());
			
			stockDAO.actualizarStock(stockCripto);
		}
	}
	
	//Resta el activo de Fiat en la billetera del usuario
	private static void restarActivoFIATUsuario(ActivoFIAT activoFIAT, UnidadDeCompra unidadDeCompra, ActivoFIATDAO<ActivoFIAT> activoFIATDAO) throws SQLException {
		if (activoFIAT == null) 
			System.out.println("El usuario no posee "+unidadDeCompra.getFiat().getNombre()+".");		
		
		else if( (activoFIAT.getCantidad() - unidadDeCompra.getCantidadFIAT()) < 0) 
			System.out.println("El usuario no posee suficiente "+unidadDeCompra.getFiat().getNombre()+" para la transacción."); 					
		
		else {
			// Restar activo de FIAT al usuario
			activoFIAT.setCantidad(activoFIAT.getCantidad() - unidadDeCompra.getCantidadFIAT());
			activoFIATDAO.actualizarActivoFIAT(activoFIAT);
		}
	}

	public static Transaccion comprarCriptomoneda(UnidadDeCompra unidadDeCompra) throws SQLException {

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

			//Suma activo de cripto al usuario
			sumarActivoCriptoUsuario(activoCripto, unidadDeCompra, activoCriptoDAO);
			//Suma stock del FIAT en el sistema
			sumarStockFIATBD(stockFIAT, unidadDeCompra, stockDAO);
			//Resta activo Fiat al usuario
			restarActivoFIATUsuario(activoFIAT, unidadDeCompra, activoFIATDAO);
			//Resta Stock de cripto en el sistema
			restarStockCriptoBD(stockCripto, unidadDeCompra, stockDAO);								
			
			mensaje = "Compra de criptomonedas\n" + "Criptomoneda: "
					+ unidadDeCompra.getCriptomoneda().getNomenclatura() + "\n" + "Cantidad de la criptomoneda: "
					+ unidadDeCompra.getCantidadCriptomoneda() + "\n" + "FIAT: "
					+ unidadDeCompra.getFiat().getNomenclatura() + "\n" + "Cantidad de la FIAT: "
					+ unidadDeCompra.getCantidadFIAT() + "\n" + "Precio por cada criptomoneda: "
					+ unidadDeCompra.getCantidadFIAT() / unidadDeCompra.getCantidadCriptomoneda();

			transaccion = new Transaccion(-1, mensaje, new Date(System.currentTimeMillis()),
					unidadDeCompra.getUsuario());
			
			transaccionDAO.insertarTransaccion(transaccion);
			
			return transaccion;
		} finally {
			return null;
		}
	}
	
	private static List<Integer> generarArregloDeNumerosRandomSinRepetir(List<Moneda> monedasDisponibles) {
		
		// Crear una lista con los números de los IDs de las monedas disponibles
		List<Integer> numeros = new ArrayList<>();
		List<Integer> ids = new ArrayList<>();
		for (int i = 1; i <= monedasDisponibles.size(); i++)
			ids.add(monedasDisponibles.get(i).getIdMoneda());
		

		// Barajar la lista de ids
		Collections.shuffle(ids);

		return ids;		
	}
	
	public static void generarActivos(Usuario usuario) throws SQLException {
		
		Random random = new Random();
		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();
		
		//lista de monedas disponibles en la base de datos
		List<Moneda> monedasDisponibles = monedaDAO.listarMonedas();
		
		//genero un número random teniendo en cuenta la cantidad de monedas disponibles
		int cantidadDeActivosRandom = random.nextInt(monedasDisponibles.size());		
		
		//lista de ids de las monedas disponibles, en orden random sin repetir
		List<Integer> idsMonedas = generarArregloDeNumerosRandomSinRepetir(monedasDisponibles);
		
		for(int i = 0; i<cantidadDeActivosRandom; i++) {			
			Moneda moneda = monedaDAO.buscarMonedaPorID(idsMonedas.get(i));			
			//Si es cripto genero un número random de 0.01 a 100.00, y redondeo a dos decimales
			if (moneda.getTipo()=='C') {
		        double cantidadCripto = 0.01 + (100.00 - 0.01) * random.nextDouble();
		        cantidadCripto = Math.round(cantidadCripto * 100.0) / 100.0;
		        Criptomoneda cripto = monedaDAO.buscarCriptomoneda(moneda.getNomenclatura());
		        ActivoCripto activoCripto = new ActivoCripto(-1, usuario, cripto, cantidadCripto);
		        activoCriptoDAO.insertarActivoCripto(activoCripto);
			}
			//Si es fiat entonces genero un número random de 0.01 a 1000000.00, y redondeo a dos decimales
			else {
				double cantidadFIAT = 0.01 + (1000000.00 - 0.01) * random.nextDouble();
				cantidadFIAT = Math.round(cantidadFIAT * 100.0) / 100.0;
				FIAT fiat = monedaDAO.buscarFIAT(moneda.getNomenclatura());
				ActivoFIAT activoFIAT = new ActivoFIAT(-1, usuario, fiat, cantidadFIAT);
		        activoFIATDAO.insertarActivoFIAT(activoFIAT);
				
			}						
		}								
	}

	public static boolean exportarCSV() {
		
		return false;
	}
}
