package ventana.compraCriptomoneda;

import java.sql.SQLException;
import java.util.Date;

import clases.ActivoCripto;
import clases.Transaccion;
import clases.UnidadDeCompra;
import daos.ActivoCriptoDAO;
import daos.FactoryDAO;
import daos.MonedaDAO;
import daos.TransaccionDAO;

public class CompraCriptomonedaModelo {

	// SE NECESITA SABER:
	// CRIPTOMONEDA

	public double obtenerPrecio(String nomenclatura) {
		MonedaDAO monedaDAO = FactoryDAO.getMonedaDAO();

		try {
			return monedaDAO.buscarCriptomoneda(nomenclatura).getValorDolar();

		} catch (SQLException e) {

			System.err.println(e.getMessage());
			return -1;
		}
	}

	public double obtenerStock(String nomenclatura) {

		MonedaDAO monedaDAO = FactoryDAO.getMonedaDAO();
		try {
			return monedaDAO.buscarCriptomoneda(nomenclatura).getStock();

		} catch (SQLException e) {

			System.err.println(e.getMessage());
			return -1;
		}
	}

	// SE NECESITA SABER:
	// USUARIO
	// CRIPTOMONEDA
	// FIAT
	// CANTIDAD CRIPTOMONEDA
	// CANTIDAD FIAT
	// HAY QUE PASAR AMBAS CANTIDADES PORQUE LA TRANSACCION LA HIZO A UN PRECIO Y
	// ENTRE QUE VA AL SERVIDOR Y ESO PUEDE CAMBIAR EL PRECIO POR ENDE LA CANTIDAD
	public Transaccion comprarCriptomoneda(UnidadDeCompra unidad) {

		ActivoCriptoDAO activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		MonedaDAO monedaDAO = FactoryDAO.getMonedaDAO();
		TransaccionDAO transaccionDAO = FactoryDAO.getTransaccionDAO();

		Transaccion transaccion = null;

		try {

			ActivoCripto activoCripto = activoCriptoDAO.buscarActivoCripto(unidad.getUsuario(),
					unidad.getCriptomoneda().getNomenclatura()); // Como: Buscar usuario | Buscar Criptomoneda

			// Se le añade al usuario

			if (activoCripto == null) {

				activoCripto = new ActivoCripto(unidad.getUsuario(), unidad.getCriptomoneda(),
						unidad.getCantidadCriptomoneda());
				activoCriptoDAO.insertarActivoCripto(activoCripto);
			}

			else {
				activoCripto.setCantidad(activoCripto.getCantidad() + unidad.getCantidadCriptomoneda());
				activoCriptoDAO.actualizarActivoCripto(activoCripto);
			}

			// Se le resta al stock

			double stockActual = monedaDAO.buscarCriptomoneda(unidad.getCriptomoneda().getNomenclatura()).getStock();
			double stockResultante = stockActual - unidad.getCantidadCriptomoneda();

			// monedaDAO.actualizarCriptomoneda();

			String resumen = "Compra de " + unidad.getCantidadCriptomoneda() + " "
					+ unidad.getCriptomoneda().getNomenclatura() + "\n" + "Pago con " + unidad.getCantidadFIAT() + " "
					+ unidad.getFiat().getNomenclatura();

			Date fecha = new Date();

			transaccion = new Transaccion(resumen, fecha);
			transaccionDAO.insertarTransaccion(transaccion);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return transaccion;
	}
}
