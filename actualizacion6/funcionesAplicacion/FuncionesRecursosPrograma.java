package funcionesAplicacion;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import daos.FactoryDAO;
import daos.MonedaDAO;

public class FuncionesRecursosPrograma {

	private static HashMap<String, ImageIcon> iconosCriptomoneda = new HashMap<>();	
	private static HashMap<String, ImageIcon> iconosFIAT = new HashMap<>();

	public static void cargarImagenesCriptomonedas() {
		iconosCriptomoneda.clear();
		List<Criptomoneda> criptomonedas;
		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();

		try {
			criptomonedas = monedaDAO.listarCriptomonedas();

			for (Criptomoneda c : criptomonedas)
				iconosCriptomoneda.put(c.getNomenclatura(), new ImageIcon(c.getNombreIcono()));

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static HashMap<String, ImageIcon> obtenerIconosDeCriptomonedas() {
		return iconosCriptomoneda;
	}

	public static void cargarImagenesFIAT() {
		iconosFIAT.clear();
		List<FIAT> fiats;
		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();

		try {
			fiats = monedaDAO.listarFIATS();

			for (FIAT f : fiats)
				iconosCriptomoneda.put(f.getNomenclatura(), new ImageIcon(f.getNombreIcono()));

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static HashMap<String, ImageIcon> obtenerIconosDeFIATS() {
		return iconosFIAT;
	}
	

}
