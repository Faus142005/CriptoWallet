package ventana.PRUEBALOCA;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PruebaVista {

	private Dimension dimensiones = new Dimension(600, 400);
	private JPanel panel = new JPanel();
	
	private JButton boton = new JButton("Boton");

	public PruebaVista() {
		
		this.panel.setLayout(null);
		
		this.boton.setBounds(50,50,100,30);		
		this.panel.add(boton);

		this.panel.setName("Panel1");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);
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
	 * @return the boton
	 */
	public JButton getBoton() {
		return boton;
	}

	/**
	 * @param boton the boton to set
	 */
	public void setBoton(JButton boton) {
		this.boton = boton;
	}
	
	
}
