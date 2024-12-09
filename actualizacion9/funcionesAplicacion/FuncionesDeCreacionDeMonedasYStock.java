package funcionesAplicacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Stock;
import clases.Usuario;
import daos.ActivoCriptoDAO;
import daos.ActivoFIATDAO;
import daos.FactoryDAO;
import daos.MonedaDAO;
import daos.StockDAO;

public class FuncionesDeCreacionDeMonedasYStock {

	public static void crearMonedas() {
		try {
			MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
			StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();
			Criptomoneda c;
			FIAT f;
			Stock s;

			// Criptomonedas
			c = new Criptomoneda(-1, "Bitcoin", "BTC", 0, "iconos/bitcoin.png", 1);
			if (monedaDAO.buscarCriptomoneda(c.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarCriptomoneda(c));
				stockDAO.insertarStock(new Stock(-1, c, 0));
			}

			c = new Criptomoneda(-1, "Ethereum", "ETH", 0, "iconos/ethereum.png", 1);
			if (monedaDAO.buscarCriptomoneda(c.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarCriptomoneda(c));
				stockDAO.insertarStock(new Stock(-1, c, 0));
			}

			c = new Criptomoneda(-1, "USD-COIN", "USDC", 0, "iconos/usdc.png", 1);
			if (monedaDAO.buscarCriptomoneda(c.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarCriptomoneda(c));
				stockDAO.insertarStock(new Stock(-1, c, 0));
			}

			c = new Criptomoneda(-1, "Tether", "USDT", 0, "iconos/tether.png", 1);
			if (monedaDAO.buscarCriptomoneda(c.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarCriptomoneda(c));
				stockDAO.insertarStock(new Stock(-1, c, 0));
			}

			c = new Criptomoneda(-1, "DogeCoin", "DOGE", 0, "iconos/dogecoin.png", 1);
			if (monedaDAO.buscarCriptomoneda(c.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarCriptomoneda(c));
				stockDAO.insertarStock(new Stock(-1, c, 0));
			}

			// FIATS

			f = new FIAT(-1, "Dolar", "USD", 1, "iconos/dolar.png");
			if (monedaDAO.buscarFIAT(f.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarFIAT(f));
				stockDAO.insertarStock(new Stock(-1, f, 0));
			}

			f = new FIAT(-1, "Peso Argentino", "ARS", 0.001, "iconos/pesoArgentino.png");
			if (monedaDAO.buscarFIAT(f.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarFIAT(f));
				stockDAO.insertarStock(new Stock(-1, f, 0));
			}

			f = new FIAT(-1, "Euro", "EUR", 1.05, "iconos/euro.png");
			if (monedaDAO.buscarFIAT(f.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarFIAT(f));
				stockDAO.insertarStock(new Stock(-1, f, 0));
			}
			
			f = new FIAT(-1, "Peso Uruguayo", "UYU", 0.023 , "iconos/pesoUruguayo.png");
			if (monedaDAO.buscarFIAT(f.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarFIAT(f));
				stockDAO.insertarStock(new Stock(-1, f, 0));
			}
			
			f = new FIAT(-1, "Peso Mexicano", "MXN", 0.049, "iconos/pesoMexicano.png");
			if (monedaDAO.buscarFIAT(f.getNomenclatura()) == null) {
				c.setIdMoneda(monedaDAO.insertarFIAT(f));
				stockDAO.insertarStock(new Stock(-1, f, 0));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void generarStock() {

		try {

			MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
			StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();
			Random r = new Random();

			List<Moneda> monedas = monedaDAO.listarMonedas();
			Stock stock;

			for (Moneda moneda : monedas) {
				stock = stockDAO.buscarStock(moneda.getNomenclatura());
				stock.setCantidad(stock.getCantidad() + r.nextDouble(1000));
				stockDAO.actualizarStock(stock);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static List<Integer> generarArregloDeNumerosRandomSinRepetir(List<Moneda> monedasDisponibles) {

		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < monedasDisponibles.size(); i++)
			ids.add(monedasDisponibles.get(i).getIdMoneda());

		// Barajar la lista de ids
		Collections.shuffle(ids);

		return ids;
	}

	public static void generarActivos(Usuario usuario) {

		Random random = new Random();
		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
		ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFIATDAO<ActivoFIAT> activoFIATDAO = FactoryDAO.getActivoFIATDAO();

		try {

			// lista de monedas disponibles en la base de datos
			List<Moneda> monedasDisponibles = monedaDAO.listarMonedas();

			// genero un número random teniendo en cuenta la cantidad de monedas disponibles
			int cantidadDeActivosRandom = random.nextInt(monedasDisponibles.size());

			// lista de ids de las monedas disponibles, en orden random sin repetir
			List<Integer> idsMonedas = generarArregloDeNumerosRandomSinRepetir(monedasDisponibles);

			for (int i = 0; i < cantidadDeActivosRandom; i++) {
				Moneda moneda = monedaDAO.buscarMonedaPorID(idsMonedas.get(i));
				// Si es cripto genero un número random de 0.01 a 100.00, y redondeo a dos
				// decimales
				if (moneda.getTipo() == 'C') {
					double cantidadCripto = 0.01 + (100.00 - 0.01) * random.nextDouble();
					cantidadCripto = Math.round(cantidadCripto * 100.0) / 100.0;
					Criptomoneda cripto = monedaDAO.buscarCriptomoneda(moneda.getNomenclatura());
					ActivoCripto activoCripto = activoCriptoDAO.buscarActivoCripto(usuario.getIdUsuario(),
							cripto.getIdMoneda());
					if (activoCripto == null) {
						activoCripto = new ActivoCripto(-1, usuario, cripto, cantidadCripto);
						activoCriptoDAO.insertarActivoCripto(activoCripto);
					}
					else {
						activoCripto.setCantidad(activoCripto.getCantidad() + cantidadCripto);
						activoCriptoDAO.actualizarActivoCripto(activoCripto);
					}
				}
				// Si es fiat entonces genero un número random de 0.01 a 1000000.00, y redondeo
				// a dos decimales
				else {
					double cantidadFIAT = 0.01 + (1000000.00 - 0.01) * random.nextDouble();
					cantidadFIAT = Math.round(cantidadFIAT * 100.0) / 100.0;
					FIAT fiat = monedaDAO.buscarFIAT(moneda.getNomenclatura());
					ActivoFIAT activoFIAT = activoFIATDAO.buscarActivoFIAT(usuario.getIdUsuario(),
							fiat.getIdMoneda());
					if (activoFIAT == null) {
						activoFIAT = new ActivoFIAT(-1, usuario, fiat, cantidadFIAT);
						activoFIATDAO.insertarActivoFIAT(activoFIAT);
					}
					else {
						activoFIAT.setCantidad(activoFIAT.getCantidad() + cantidadFIAT);
						activoFIATDAO.actualizarActivoFIAT(activoFIAT);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
