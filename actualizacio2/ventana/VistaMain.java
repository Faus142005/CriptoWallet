package ventana;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ventana.compraCriptomoneda.CompraCriptomonedaControlador;
import ventana.compraCriptomoneda.CompraCriptomonedaVista;
import ventana.cotizacionesCriptomonedas.CotizacionesCriptomonedasControlador;
import ventana.cotizacionesCriptomonedas.CotizacionesCriptomonedasVista;
import ventana.ingresar.IngresarControlador;
import ventana.ingresar.IngresarVista;
import ventana.inicio.InicioControlador;
import ventana.inicio.InicioVista;
import ventana.registrarse.RegistrarseControlador;
import ventana.registrarse.RegistrarseVista;
import ventana.transacciones.TransaccionesControlador;
import ventana.transacciones.TransaccionesVista;
import ventana.visualizacionActivos.VisualizarActivosControlador;
import ventana.visualizacionActivos.VisualizarActivosVista;

public class VistaMain extends JFrame {

	private CardLayout cardLayout = new CardLayout();
	private JPanel cardPanel = new JPanel(cardLayout);
	private Stack<String> pilaDePaneles = new Stack<String>();

	// Clases vista

	private RegistrarseVista registrarseVista = new RegistrarseVista();
	private IngresarVista ingresarVista = new IngresarVista();
	private InicioVista inicioVista = new InicioVista();
	private CotizacionesCriptomonedasVista cotizacionesCriptomonedasVista = new CotizacionesCriptomonedasVista();
	private CompraCriptomonedaVista compraCriptomonedaVista = new CompraCriptomonedaVista();
	private VisualizarActivosVista visualizarActivosVista = new VisualizarActivosVista();
	private TransaccionesVista transaccionesVista = new TransaccionesVista();

	// Clases Controlador

	private RegistrarseControlador registrarseControlador = new RegistrarseControlador(registrarseVista, this);
	private IngresarControlador ingresarControlador = new IngresarControlador(ingresarVista, this);
	private InicioControlador inicioControlador = new InicioControlador(inicioVista, this);
	private CotizacionesCriptomonedasControlador cotizacionesCriptomonedasControlador = new CotizacionesCriptomonedasControlador(
			cotizacionesCriptomonedasVista, this);
	private CompraCriptomonedaControlador compraCriptomonedaControlador = new CompraCriptomonedaControlador(
			compraCriptomonedaVista, this);
	private VisualizarActivosControlador visualizarActivosControlador = new VisualizarActivosControlador(
			visualizarActivosVista, this);
	private TransaccionesControlador transaccionesControlador = new TransaccionesControlador(transaccionesVista, this);

	public VistaMain() {

		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		cardPanel.add(registrarseVista.getPanel(), registrarseVista.getPanel().getName());
		cardPanel.add(ingresarVista.getPanel(), ingresarVista.getPanel().getName());
		cardPanel.add(inicioVista.getPanel(), inicioVista.getPanel().getName());
		cardPanel.add(cotizacionesCriptomonedasVista.getPanel(), cotizacionesCriptomonedasVista.getPanel().getName());
		cardPanel.add(compraCriptomonedaVista.getPanel(), compraCriptomonedaVista.getPanel().getName());
		cardPanel.add(visualizarActivosVista.getPanel(), visualizarActivosVista.getPanel().getName());
		cardPanel.add(transaccionesVista.getPanel(), transaccionesVista.getPanel().getName());

		// this.cambiarPanel(registrarseVista.getPanel().getName());
		this.cambiarPanel(ingresarVista.getPanel().getName());
		// this.cambiarPanel(inicioVista.getPanel().getName());
		// this.cambiarPanel(compraCriptomonedaVista.getPanel().getName());
		// this.cambiarPanel(registrarseVista.getPanel().getName());
		// this.cambiarPanel(cotizacionesCriptomonedasVista.getPanel().getName());
		// this.cambiarPanel(visualizarActivosVista.getPanel().getName());

		this.add(cardPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void cambiarPanel(String id) {
		pilaDePaneles.push(id);
		this.setTitle("CriptoWallet - " + id);
		cardLayout.show(cardPanel, id);

		Dimension d = obtenerDimensionesDelPanel(id);
		this.setSize(d);
		this.setLocationRelativeTo(null);
	}

	public void panelAnterior() {
		pilaDePaneles.pop();
		String id = pilaDePaneles.pop();
		this.cambiarPanel(id);
	}

	private Dimension obtenerDimensionesDelPanel(String id) {
		for (Component comp : cardPanel.getComponents()) {

			if (comp.getName().equals(id)) {
				return comp.getPreferredSize();
			}
		}

		return null;
	}

	/**
	 * @return the cardLayout
	 */
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	/**
	 * @param cardLayout the cardLayout to set
	 */
	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	/**
	 * @return the cardPanel
	 */
	public JPanel getCardPanel() {
		return cardPanel;
	}

	/**
	 * @param cardPanel the cardPanel to set
	 */
	public void setCardPanel(JPanel cardPanel) {
		this.cardPanel = cardPanel;
	}

	/**
	 * @return the registrarseVista
	 */
	public RegistrarseVista getRegistrarseVista() {
		return registrarseVista;
	}

	/**
	 * @param registrarseVista the registrarseVista to set
	 */
	public void setRegistrarseVista(RegistrarseVista registrarseVista) {
		this.registrarseVista = registrarseVista;
	}

	/**
	 * @return the ingresarVista
	 */
	public IngresarVista getIngresarVista() {
		return ingresarVista;
	}

	/**
	 * @param ingresarVista the ingresarVista to set
	 */
	public void setIngresarVista(IngresarVista ingresarVista) {
		this.ingresarVista = ingresarVista;
	}

	/**
	 * @return the compraCriptomonedaVista
	 */
	public CompraCriptomonedaVista getCompraCriptomonedaVista() {
		return compraCriptomonedaVista;
	}

	/**
	 * @param compraCriptomonedaVista the compraCriptomonedaVista to set
	 */
	public void setCompraCriptomonedaVista(CompraCriptomonedaVista compraCriptomonedaVista) {
		this.compraCriptomonedaVista = compraCriptomonedaVista;
	}

	/**
	 * @return the registrarseControlador
	 */
	public RegistrarseControlador getRegistrarseControlador() {
		return registrarseControlador;
	}

	/**
	 * @param registrarseControlador the registrarseControlador to set
	 */
	public void setRegistrarseControlador(RegistrarseControlador registrarseControlador) {
		this.registrarseControlador = registrarseControlador;
	}

	/**
	 * @return the ingresarControlador
	 */
	public IngresarControlador getIngresarControlador() {
		return ingresarControlador;
	}

	/**
	 * @param ingresarControlador the ingresarControlador to set
	 */
	public void setIngresarControlador(IngresarControlador ingresarControlador) {
		this.ingresarControlador = ingresarControlador;
	}

	/**
	 * @return the compraCriptomonedaControlador
	 */
	public CompraCriptomonedaControlador getCompraCriptomonedaControlador() {
		return compraCriptomonedaControlador;
	}

	/**
	 * @param compraCriptomonedaControlador the compraCriptomonedaControlador to set
	 */
	public void setCompraCriptomonedaControlador(CompraCriptomonedaControlador compraCriptomonedaControlador) {
		this.compraCriptomonedaControlador = compraCriptomonedaControlador;
	}

}
