package ventana;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ventana.ingresar.IngresarControlador;
import ventana.ingresar.IngresarModelo;
import ventana.ingresar.IngresarVista;
import ventana.registrarse.RegistrarseControlador;
import ventana.registrarse.RegistrarseModelo;
import ventana.registrarse.RegistrarseVista;

public class VistaMain extends JFrame {

	private CardLayout cardLayout = new CardLayout();
	private JPanel cardPanel = new JPanel(cardLayout);

	// Clases vista

	private RegistrarseVista registrarseVista = new RegistrarseVista();
	private IngresarVista ingresarVista = new IngresarVista();

	// Clases modelo

	private RegistrarseModelo registrarseModelo = new RegistrarseModelo();
	private IngresarModelo ingresarModelo = new IngresarModelo();

	// Clases Controlador

	private RegistrarseControlador registrarseControlador = new RegistrarseControlador(registrarseVista,
			registrarseModelo, this);
	private IngresarControlador ingresarControlador = new IngresarControlador(ingresarVista, ingresarModelo, this);

	public VistaMain() {

		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		cardPanel.add(registrarseVista.getPanel(), "Registrarse");
		cardPanel.add(ingresarVista.getPanel(), "Ingresar");

		cardLayout.show(cardPanel, "Registrarse");
		this.setTitle("CriptoWallet - " + "Registrarse");
		this.setSize(registrarseVista.getDimensiones());
		
		this.add(cardPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void cambiarPanel(String id) {
		this.setTitle("CriptoWallet - " + id);
		cardLayout.show(cardPanel, id);
		
		Dimension d = obtenerDimensionesDelPanel(id);
		this.setSize(d);
		System.out.println("Dimensiones: " + d.toString());
		System.out.println("Ventana: " + this.getSize().toString());
		this.setLocationRelativeTo(null);
	}
	
	private Dimension obtenerDimensionesDelPanel(String id) {
		Dimension d = null;
		for (Component comp : cardPanel.getComponents()) {
			
            if (comp.getName().equals(id)) {
                d = comp.getPreferredSize();
            }
        }
		
		return d;
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
	 * @return the registrarseModelo
	 */
	public RegistrarseModelo getRegistrarseModelo() {
		return registrarseModelo;
	}

	/**
	 * @param registrarseModelo the registrarseModelo to set
	 */
	public void setRegistrarseModelo(RegistrarseModelo registrarseModelo) {
		this.registrarseModelo = registrarseModelo;
	}

	/**
	 * @return the ingresarModelo
	 */
	public IngresarModelo getIngresarModelo() {
		return ingresarModelo;
	}

	/**
	 * @param ingresarModelo the ingresarModelo to set
	 */
	public void setIngresarModelo(IngresarModelo ingresarModelo) {
		this.ingresarModelo = ingresarModelo;
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

}
