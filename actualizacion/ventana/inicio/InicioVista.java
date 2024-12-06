package ventana.inicio;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InicioVista {

	private Dimension dimensiones = new Dimension(700, 700);
	private JPanel panel = new JPanel();
	
	private JLabel etiquetaBalance;
	
	public InicioVista() {
		
		panel.setName("Inicio");
		panel.setSize(dimensiones);
		panel.setPreferredSize(dimensiones);
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
}
