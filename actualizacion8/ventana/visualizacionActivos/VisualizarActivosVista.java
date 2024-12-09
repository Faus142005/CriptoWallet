package ventana.visualizacionActivos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import aplicacion.CalculosGenerales;
import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Stock;
import daos.FactoryDAO;
import daos.StockDAO;
import estilos.EncabezadoCriptoWallet;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import funcionesAplicacion.FuncionesRecursosPrograma;

public class VisualizarActivosVista {

	private Dimension dimensiones = new Dimension(700, 550);
	private JPanel panel = new JPanel();

	// FUNCIONAMIENTO SCROLL PANE
	private HashMap<String, ImageIcon> imagenesEscaladas = new HashMap<>();
	private HashMap<String, JLabel> etiquetasPrecios = new HashMap<String, JLabel>();
	private HashMap<String, JLabel> etiquetasVolatilidad = new HashMap<String, JLabel>();
	private HashMap<String, JLabel> etiquetasStock = new HashMap<String, JLabel>();

	private JPanel panelScrollPaneActivos = new JPanel();
	private JScrollPane scrollPaneActivos = new JScrollPane(panelScrollPaneActivos);

	// OTRAS COSAS
	private static JPanel encabezado = EncabezadoCriptoWallet.crearEncabezado("CriptoWallet", "iconos/logo.png");	
	
	private JLabel etiquetaBalance = new JLabel();
	private JButton botonAtras = new JButton("Atras");
	private JButton botonExportarCVS = new JButton("Exportar CVS");

	public VisualizarActivosVista() {

		this.panel.setLayout(null);

		this.panelScrollPaneActivos.setLayout(new BoxLayout(panelScrollPaneActivos, BoxLayout.Y_AXIS));
		this.panel.add(scrollPaneActivos);

		this.scrollPaneActivos.setBounds(50, 50, 600, 400);

		this.etiquetaBalance.setBounds(50, 460, 300, 30);
		this.panel.add(etiquetaBalance);

		this.botonAtras.setBounds(10, 10, 100, 30);
		this.panel.add(botonAtras);

		this.botonExportarCVS.setBounds(400, 460, 150, 30);
		this.panel.add(botonExportarCVS);

		this.panel.setName("Visualizar Activos");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);
		
		// Encabezado en la parte superior
		this.panel.add(encabezado, BorderLayout.NORTH); 

		this.nuevosActivos();
	}

	public void actualizarDatosActivos() {

		try {

			List<ActivoCripto> activosCripto = FuncionesDeLaAplicacion
					.listarActivosCripto(GestorDeDatosDeLaAplicacion.getUsuarioConectado());
			StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();

			for (ActivoCripto activoCripto : activosCripto) {
				Criptomoneda criptomoneda = (Criptomoneda) activoCripto.getMoneda();
				Stock s = stockDAO.buscarStock(criptomoneda.getNomenclatura());
				
				etiquetasStock.get(criptomoneda.getNomenclatura()).setText(String.valueOf(s.getCantidad()));
				etiquetasStock.get(criptomoneda.getNomenclatura()).setText("ADDLS");
				etiquetasVolatilidad.get(criptomoneda.getNomenclatura())
						.setText(String.valueOf(criptomoneda.getVolatilidad()));
				etiquetasPrecios.get(criptomoneda.getNomenclatura())
						.setText(String.valueOf(criptomoneda.getValorDolar()));
			}

			DecimalFormat formato = new DecimalFormat("#.##########");
			
			etiquetaBalance.setText("Balance: " + formato.format(FuncionesDeLaAplicacion
					.calcularBalanceEnDolaresDeUsuario(GestorDeDatosDeLaAplicacion.getUsuarioConectado())) + "USD");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void nuevosActivos() {
		
		try {

		imagenesEscaladas.clear();

		etiquetasPrecios.clear();
		etiquetasVolatilidad.clear();
		etiquetasStock.clear();

		panelScrollPaneActivos.removeAll();

		List<ActivoCripto> activosCripto = FuncionesDeLaAplicacion
				.listarActivosCripto(GestorDeDatosDeLaAplicacion.getUsuarioConectado());

		List<ActivoFIAT> activosFIAT = FuncionesDeLaAplicacion
				.listarActivosFIAT(GestorDeDatosDeLaAplicacion.getUsuarioConectado());

		HashMap<String, ImageIcon> auxImagenes = FuncionesRecursosPrograma.obtenerIconosDeCriptomonedas();
		StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();
		
		DecimalFormat formato = new DecimalFormat("#.##########");

		for (ActivoCripto activoCripto : activosCripto) {

			Criptomoneda criptomoneda = (Criptomoneda) activoCripto.getMoneda();

			imagenesEscaladas.put(criptomoneda.getNomenclatura(),
					CalculosGenerales.escalarImagenAlto(auxImagenes.get(criptomoneda.getNomenclatura()), 50));

			// Panel izquierda(icono)
			JPanel monedaPanel = new JPanel(new BorderLayout());
			monedaPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			JPanel iconoPanel = new JPanel();
			iconoPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			JLabel imagenLabel = new JLabel(imagenesEscaladas.get(criptomoneda.getNomenclatura()));

			iconoPanel.add(imagenLabel, gbc);

			// Panel central(Informacion moneda)
			JPanel informacionCriptoPanel = new JPanel();
			informacionCriptoPanel.setLayout(new GridBagLayout());
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridx = 0;

			JLabel etiquetaTexto = new JLabel(criptomoneda.getNombre() + " (" + criptomoneda.getNomenclatura() + ")");
			gbc.gridy = 0;
			informacionCriptoPanel.add(etiquetaTexto, gbc);
			
			JLabel etiquetaPrecio = new JLabel(formato.format(criptomoneda.getValorDolar()));
			gbc.gridy = 1;
			informacionCriptoPanel.add(etiquetaPrecio, gbc);
			etiquetasPrecios.put(criptomoneda.getNomenclatura(), etiquetaPrecio);
			JLabel etiquetaVolatilidad = new JLabel("Volatilidad: " + formato.format(criptomoneda.getVolatilidad()) + "%");
			gbc.gridy = 2;
			informacionCriptoPanel.add(etiquetaVolatilidad, gbc);
			
			Stock s = stockDAO.buscarStock(criptomoneda.getNomenclatura());
			JLabel etiquetaStock = new JLabel("Stock: " + formato.format(s.getCantidad()));
			gbc.gridy = 3;
			informacionCriptoPanel.add(etiquetaStock, gbc);

			// Panel derecho(informacion activo)
			JPanel informacionActivo = new JPanel();
			informacionActivo.setLayout(new GridBagLayout());
			gbc.anchor = GridBagConstraints.CENTER;

			JLabel extraLabel1 = new JLabel("Cantidad: " + formato.format(activoCripto.getCantidad()));
			gbc.gridy = 0;
			extraLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			informacionActivo.add(extraLabel1, gbc);

			JLabel extraLabel2 = new JLabel(
					"Monto total en USD: " + formato.format(activoCripto.getCantidad() * criptomoneda.getValorDolar()));
			gbc.gridy = 1;
			extraLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			informacionActivo.add(extraLabel2, gbc);

			monedaPanel.add(iconoPanel, BorderLayout.WEST);
			monedaPanel.add(informacionCriptoPanel, BorderLayout.CENTER);
			monedaPanel.add(informacionActivo, BorderLayout.EAST);

			panelScrollPaneActivos.add(monedaPanel);
		}

		auxImagenes = FuncionesRecursosPrograma.obtenerIconosDeFIATS();

		for (ActivoFIAT activoFIAT : activosFIAT) {
			FIAT fiat = (FIAT) activoFIAT.getMoneda();

			imagenesEscaladas.put(fiat.getNomenclatura(),
					CalculosGenerales.escalarImagenAlto(auxImagenes.get(fiat.getNomenclatura()), 50));

			// Panel izquierda(icono)
			JPanel monedaPanel = new JPanel(new BorderLayout());
			monedaPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			JPanel iconoPanel = new JPanel();
			iconoPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			JLabel imagenLabel = new JLabel(imagenesEscaladas.get(fiat.getNomenclatura()));

			iconoPanel.add(imagenLabel, gbc);

			// Panel central(Informacion moneda)
			JPanel informacionCriptoPanel = new JPanel();
			informacionCriptoPanel.setLayout(new GridBagLayout());
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridx = 0;

			JLabel etiquetaTexto = new JLabel(fiat.getNombre() + " (" + fiat.getNomenclatura() + ")");
			gbc.gridy = 0;
			informacionCriptoPanel.add(etiquetaTexto, gbc);
			
			JLabel etiquetaPrecio = new JLabel(formato.format(fiat.getValorDolar()));
			gbc.gridy = 1;
			informacionCriptoPanel.add(etiquetaPrecio, gbc);
			
			Stock s = stockDAO.buscarStock(fiat.getNomenclatura());
			JLabel etiquetaStock = new JLabel("Stock: " + formato.format(s.getCantidad()));
			gbc.gridy = 2;
			informacionCriptoPanel.add(etiquetaStock, gbc);

			// Panel derecho(informacion activo)
			JPanel informacionActivo = new JPanel();
			informacionActivo.setLayout(new GridBagLayout());
			gbc.anchor = GridBagConstraints.CENTER;

			JLabel extraLabel1 = new JLabel("Cantidad: " + formato.format(activoFIAT.getCantidad()));
			gbc.gridy = 0;
			extraLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			informacionActivo.add(extraLabel1, gbc);

			JLabel extraLabel2 = new JLabel("Monto total en USD: " + formato.format(activoFIAT.getCantidad() * fiat.getValorDolar()));
			gbc.gridy = 1;
			extraLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			informacionActivo.add(extraLabel2, gbc);

			monedaPanel.add(iconoPanel, BorderLayout.WEST);
			monedaPanel.add(informacionCriptoPanel, BorderLayout.CENTER);
			monedaPanel.add(informacionActivo, BorderLayout.EAST);

			panelScrollPaneActivos.add(monedaPanel);
		}
		
		etiquetaBalance.setText("Balance: " + formato.format(FuncionesDeLaAplicacion
				.calcularBalanceEnDolaresDeUsuario(GestorDeDatosDeLaAplicacion.getUsuarioConectado())) + "USD");
		
		} catch (SQLException e) {
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
	 * @return the panelScrollPaneActivos
	 */
	public JPanel getPanelScrollPaneActivos() {
		return panelScrollPaneActivos;
	}

	/**
	 * @param panelScrollPaneActivos the panelScrollPaneActivos to set
	 */
	public void setPanelScrollPaneActivos(JPanel panelScrollPaneActivos) {
		this.panelScrollPaneActivos = panelScrollPaneActivos;
	}

	/**
	 * @return the scrollPaneActivos
	 */
	public JScrollPane getScrollPaneActivos() {
		return scrollPaneActivos;
	}

	/**
	 * @param scrollPaneActivos the scrollPaneActivos to set
	 */
	public void setScrollPaneActivos(JScrollPane scrollPaneActivos) {
		this.scrollPaneActivos = scrollPaneActivos;
	}

	/**
	 * @return the etiquetaBalance
	 */
	public JLabel getEtiquetaBalance() {
		return etiquetaBalance;
	}

	/**
	 * @param etiquetaBalance the etiquetaBalance to set
	 */
	public void setEtiquetaBalance(JLabel etiquetaBalance) {
		this.etiquetaBalance = etiquetaBalance;
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
	 * @return the botonExportarCVS
	 */
	public JButton getBotonExportarCVS() {
		return botonExportarCVS;
	}

	/**
	 * @param botonExportarCVS the botonExportarCVS to set
	 */
	public void setBotonExportarCVS(JButton botonExportarCVS) {
		this.botonExportarCVS = botonExportarCVS;
	}

}
