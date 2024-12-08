package ventana.cotizacionesCriptomonedas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import aplicacion.CalculosGenerales;
import clases.Criptomoneda;
import clases.Stock;
import daos.FactoryDAO;
import daos.StockDAO;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import funcionesAplicacion.FuncionesRecursosPrograma;

public class CotizacionesCriptomonedasVista {

	private Dimension dimensiones = new Dimension(600, 500);
	private JPanel panel = new JPanel();

	private JButton botonAtras = new JButton("Atras");

	private JPanel panelScrollPaneCriptomonedas = new JPanel();
	private JScrollPane scrollPaneCriptomonedas = new JScrollPane(panelScrollPaneCriptomonedas);

	private HashMap<String, ImageIcon> imagenesEscaladas = new HashMap<>();
	private HashMap<String, JLabel> etiquetasPrecios = new HashMap<String, JLabel>();
	private HashMap<String, JLabel> etiquetasVolatilidad = new HashMap<String, JLabel>();
	private HashMap<String, JLabel> etiquetasStock = new HashMap<String, JLabel>();
	private HashMap<Criptomoneda, JButton> botonesCompra = new HashMap<Criptomoneda, JButton>();

	public CotizacionesCriptomonedasVista() {
		this.panel.setLayout(null);
		this.panel.add(scrollPaneCriptomonedas);

		this.panelScrollPaneCriptomonedas.setLayout(new BoxLayout(panelScrollPaneCriptomonedas, BoxLayout.Y_AXIS));
		this.scrollPaneCriptomonedas.setBounds(50, 50, 500, 400);

		this.botonAtras.setBounds(15, 15, 100, 30);
		this.panel.add(botonAtras);

		this.panel.setName("Cotizaciones Criptomonedas");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);

		this.nuevasCriptomonedas();
	}

	public void cambiarDatosCriptomonedas() {

		try {

			List<Criptomoneda> criptomonedas = FuncionesDeLaAplicacion.listarCriptomonedas();
			StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();

			for (Criptomoneda criptomoneda : criptomonedas) {
				Stock s = stockDAO.buscarStock(criptomoneda.getNomenclatura());
				etiquetasStock.get(criptomoneda.getNomenclatura()).setText(String.valueOf(s.getCantidad()));
				etiquetasVolatilidad.get(criptomoneda.getNomenclatura())
						.setText(String.valueOf(criptomoneda.getVolatilidad()));
				etiquetasPrecios.get(criptomoneda.getNomenclatura())
						.setText(String.valueOf(criptomoneda.getValorDolar()));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void nuevasCriptomonedas() {
		
		try {

		imagenesEscaladas.clear();

		etiquetasPrecios.clear();
		etiquetasVolatilidad.clear();
		etiquetasStock.clear();
		botonesCompra.clear();

		panelScrollPaneCriptomonedas.removeAll();

		List<Criptomoneda> criptomonedas = FuncionesDeLaAplicacion.listarCriptomonedas();
		StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();

		HashMap<String, ImageIcon> auxImagenes = FuncionesRecursosPrograma.obtenerIconsDeCriptomonedas();

		for (Criptomoneda criptomoneda : criptomonedas) {

			imagenesEscaladas.put(criptomoneda.getNomenclatura(),
					CalculosGenerales.escalarImagenAlto(auxImagenes.get(criptomoneda.getNomenclatura()), 50));

			JPanel monedaPanel = new JPanel(new BorderLayout());
			monedaPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			JPanel iconoPanel = new JPanel();
			iconoPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			JLabel imagenLabel = new JLabel(imagenesEscaladas.get(criptomoneda.getNomenclatura()));
			iconoPanel.add(imagenLabel, gbc);

			JPanel informacionCriptoPanel = new JPanel();
			informacionCriptoPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc2 = new GridBagConstraints();
			gbc2.anchor = GridBagConstraints.CENTER;
			gbc2.gridx = 0;

			// Etiqueta del nombre de la criptomoneda
			JLabel etiquetaTexto = new JLabel(criptomoneda.getNombre() + " (" + criptomoneda.getNomenclatura() + ")");
			gbc2.gridy = 0;
			informacionCriptoPanel.add(etiquetaTexto, gbc2);

			// Etiqueta del precio actual en dólares
			JLabel etiquetaPrecio = new JLabel(String.valueOf(criptomoneda.getValorDolar()));
			gbc2.gridy = 1;
			informacionCriptoPanel.add(etiquetaPrecio, gbc2);
			etiquetasPrecios.put(criptomoneda.getNomenclatura(), etiquetaPrecio);

			// Nueva etiqueta: Porcentaje de cambio diario
			JLabel etiquetaVolatilidad = new JLabel("Volatilidad: " + criptomoneda.getVolatilidad() + "%");
			gbc2.gridy = 2;
			informacionCriptoPanel.add(etiquetaVolatilidad, gbc2);
			etiquetasVolatilidad.put(criptomoneda.getNomenclatura(), etiquetaVolatilidad);
			
			Stock s = stockDAO.buscarStock(criptomoneda.getNomenclatura());

			JLabel etiquetaStock = new JLabel("Stock: " + s.getCantidad());
			gbc2.gridy = 3;
			informacionCriptoPanel.add(etiquetaStock, gbc2);
			etiquetasStock.put(criptomoneda.getNomenclatura(), etiquetaStock);

			JButton boton = new JButton("Comprar");
			botonesCompra.put(criptomoneda, boton);

			monedaPanel.add(iconoPanel, BorderLayout.WEST);
			monedaPanel.add(informacionCriptoPanel, BorderLayout.CENTER);
			monedaPanel.add(boton, BorderLayout.EAST);

			panelScrollPaneCriptomonedas.add(monedaPanel);
		}
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @return the dimensiones
	 */
	public Dimension getDimensiones() {
		return dimensiones;
	}

	/**
	 * @param dimensiones the dimensiones to set
	 */
	public void setDimensiones(Dimension dimensiones) {
		this.dimensiones = dimensiones;
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * @return the botonAtras
	 */
	public JButton getBotonAtras() {
		return botonAtras;
	}

	/**
	 * @param botonAtras the botonAtras to set
	 */
	public void setBotonAtras(JButton botonAtras) {
		this.botonAtras = botonAtras;
	}

	/**
	 * @return the panelScrollPaneCriptomonedas
	 */
	public JPanel getPanelScrollPaneCriptomonedas() {
		return panelScrollPaneCriptomonedas;
	}

	/**
	 * @param panelScrollPaneCriptomonedas the panelScrollPaneCriptomonedas to set
	 */
	public void setPanelScrollPaneCriptomonedas(JPanel panelScrollPaneCriptomonedas) {
		this.panelScrollPaneCriptomonedas = panelScrollPaneCriptomonedas;
	}

	/**
	 * @return the scrollPaneCriptomonedas
	 */
	public JScrollPane getScrollPaneCriptomonedas() {
		return scrollPaneCriptomonedas;
	}

	/**
	 * @param scrollPaneCriptomonedas the scrollPaneCriptomonedas to set
	 */
	public void setScrollPaneCriptomonedas(JScrollPane scrollPaneCriptomonedas) {
		this.scrollPaneCriptomonedas = scrollPaneCriptomonedas;
	}

	/**
	 * @return the imagenesEscaladas
	 */
	public HashMap<String, ImageIcon> getImagenesEscaladas() {
		return imagenesEscaladas;
	}

	/**
	 * @param imagenesEscaladas the imagenesEscaladas to set
	 */
	public void setImagenesEscaladas(HashMap<String, ImageIcon> imagenesEscaladas) {
		this.imagenesEscaladas = imagenesEscaladas;
	}

	/**
	 * @return the etiquetasPrecios
	 */
	public HashMap<String, JLabel> getEtiquetasPrecios() {
		return etiquetasPrecios;
	}

	/**
	 * @param etiquetasPrecios the etiquetasPrecios to set
	 */
	public void setEtiquetasPrecios(HashMap<String, JLabel> etiquetasPrecios) {
		this.etiquetasPrecios = etiquetasPrecios;
	}

	/**
	 * @return the etiquetasVolatilidad
	 */
	public HashMap<String, JLabel> getEtiquetasVolatilidad() {
		return etiquetasVolatilidad;
	}

	/**
	 * @param etiquetasVolatilidad the etiquetasVolatilidad to set
	 */
	public void setEtiquetasVolatilidad(HashMap<String, JLabel> etiquetasVolatilidad) {
		this.etiquetasVolatilidad = etiquetasVolatilidad;
	}

	/**
	 * @return the etiquetasStock
	 */
	public HashMap<String, JLabel> getEtiquetasStock() {
		return etiquetasStock;
	}

	/**
	 * @param etiquetasStock the etiquetasStock to set
	 */
	public void setEtiquetasStock(HashMap<String, JLabel> etiquetasStock) {
		this.etiquetasStock = etiquetasStock;
	}

	/**
	 * @return the botonesCompra
	 */
	public HashMap<Criptomoneda, JButton> getBotonesCompra() {
		return botonesCompra;
	}

	/**
	 * @param botonesCompra the botonesCompra to set
	 */
	public void setBotonesCompra(HashMap<Criptomoneda, JButton> botonesCompra) {
		this.botonesCompra = botonesCompra;
	}

}