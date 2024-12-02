package aplicacion;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private static HashMap<String, ImageIcon> iconos = new HashMap<>();
	
	public static void cargarImagenes() {
		iconos.clear();
		List<Criptomoneda> monedas = new ArrayList<>();
        monedas.add(new Criptomoneda(1, "Bitcoin", "BTC", 50000.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Etherium", "ETH", 10.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "DogeCoin", "DOGE", 55.5555555551, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Solana", "SOL", 250.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Ripple", "XRP", 1.20, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Ripple.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Litecoin", "LTC", 180.5, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Litecoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Cardano", "ADA", 0.45, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Cardano.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Polkadot", "DOT", 7.50, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Polkadot.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Chainlink", "LINK", 30.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Chainlink.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Bitcoin Cash", "BCH", 700.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/BitcoinCash.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Stellar", "XLM", 0.25, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Stellar.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "TRON", "TRX", 0.08, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Tron.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "VeChain", "VET", 0.10, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/VeChain.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Shiba Inu", "SHIB", 0.000007, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/ShibaInu.png", 10.0, 0));
        
        for (Criptomoneda c : monedas) {
        	iconos.put(c.getNomenclatura(), new ImageIcon(c.getNombreIcono()));
		}
	}
	
	public static HashMap<String, ImageIcon> obtenerIconsDeCriptomonedas() {
		
		return iconos;
	}
	
	public static List<Criptomoneda> listarCriptomonedas(){
		
		List<Criptomoneda> monedas = new ArrayList<>();
        monedas.add(new Criptomoneda(1, "Bitcoin", "BTC", 50000.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Etherium", "ETH", 10.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "DogeCoin", "DOGE", 55.5555555551, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Solana", "SOL", 250.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Ripple", "XRP", 1.20, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Ripple.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Litecoin", "LTC", 180.5, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Litecoin.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Cardano", "ADA", 0.45, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Cardano.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Polkadot", "DOT", 7.50, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Polkadot.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Chainlink", "LINK", 30.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Chainlink.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Bitcoin Cash", "BCH", 700.0, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/BitcoinCash.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Stellar", "XLM", 0.25, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Stellar.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "TRON", "TRX", 0.08, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Tron.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "VeChain", "VET", 0.10, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/VeChain.png", 10.0, 0));
        monedas.add(new Criptomoneda(1, "Shiba Inu", "SHIB", 0.000007, "/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/ShibaInu.png", 10.0, 0));
        return monedas;
	}
	
	public static double calcularBalanceEnDolaresDeUsuario(Usuario usuario) throws SQLException {

		double balance = 0;

		ActivoCriptoDAO activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFIATDAO activoFIATDAO = FactoryDAO.getActivoFIATDAO();

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
			
		} finally {
			
			return -1;
		}

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
		ActivoCriptoDAO activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFIATDAO activoFIATDAO = FactoryDAO.getActivoFIATDAO();
		FIATDAO fiatDAO = FactoryDAO.getFiatDAO();
		CriptomonedaDAO criptomonedaDAO = FactoryDAO.getCriptomonedaDAO();

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

			transaccion = new Transaccion(-1, mensaje, new Date(System.currentTimeMillis()));

			return transaccion;
		} finally {
			return null;
		}
	}
}
