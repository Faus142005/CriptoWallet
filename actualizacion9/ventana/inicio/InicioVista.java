package ventana.inicio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class InicioVista {

	private Dimension dimensiones = new Dimension(700, 400);
	private JPanel panel = new JPanel();
	
	private JButton botonCerrarSesion = new JButton("Cerrar sesion");	
	private JButton botonVisualizarActivos = new JButton("Ver activos");
	private JButton botonVerCotizacionCriptomonedas = new JButton("Ver cotizacion");
	private JButton botonComprarCriptomonedas = new JButton("Comprar criptomonedas");
	private JButton botonVisualizarTransaccion = new JButton("Ver transacciones");
	
	private JButton botonGenerarStock = new JButton("Generar stock");
	private JButton botonGenerarActivos = new JButton("Generar activos");
	
	//Decorativos
	
	private JLabel etiquetaMenu = new JLabel("Menu");
	private JLabel fotoUsuario = new JLabel(new ImageIcon("iconos/logo.png"));

	public InicioVista() {
		
		this.panel.setLayout(null);
		this.panel.setBackground(new Color(255,255,255));		
		
		this.botonCerrarSesion.setBounds(30, 180, 150, 30);
		this.botonCerrarSesion.setBackground(new Color(255,0,0));
		this.botonCerrarSesion.setForeground(new Color(0,0,0));
		this.panel.add(botonCerrarSesion);
		
		this.botonVisualizarActivos.setBounds(225,90,250,30);
		this.panel.add(botonVisualizarActivos);
		
		this.botonVerCotizacionCriptomonedas.setBounds(225,150,250,30);
		this.panel.add(botonVerCotizacionCriptomonedas);
		
		this.botonComprarCriptomonedas.setBounds(225,210,250,30);
		this.panel.add(botonComprarCriptomonedas);
		
		this.botonVisualizarTransaccion.setBounds(225,270,250,30);
		this.panel.add(botonVisualizarTransaccion);
		
		this.botonGenerarStock.setBounds(520,250, 150, 30);
		this.panel.add(botonGenerarStock);
		
		this.botonGenerarActivos.setBounds(520,310, 150, 30);
		this.panel.add(botonGenerarActivos);
		
		//Decorativo
		
		this.etiquetaMenu.setBounds(225, 30, 250, 30);
		this.etiquetaMenu.setFont(new Font("Arial", 1, 30));
		this.etiquetaMenu.setHorizontalAlignment(JLabel.CENTER);
		this.panel.add(etiquetaMenu);
		
		this.fotoUsuario.setBounds(30, 30, 140, 140);
		this.panel.add(fotoUsuario);
		
		this.panel.setName("Inicio");
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
	 * @return the botonCerrarSesion
	 */
	public JButton getBotonCerrarSesion() {
		return botonCerrarSesion;
	}

	/**
	 * @param botonCerrarSesion the botonCerrarSesion to set
	 */
	public void setBotonCerrarSesion(JButton botonCerrarSesion) {
		this.botonCerrarSesion = botonCerrarSesion;
	}

	/**
	 * @return the botonVisualizarActivos
	 */
	public JButton getBotonVisualizarActivos() {
		return botonVisualizarActivos;
	}

	/**
	 * @param botonVisualizarActivos the botonVisualizarActivos to set
	 */
	public void setBotonVisualizarActivos(JButton botonVisualizarActivos) {
		this.botonVisualizarActivos = botonVisualizarActivos;
	}

	/**
	 * @return the botonVerCotizacionCriptomonedas
	 */
	public JButton getBotonVerCotizacionCriptomonedas() {
		return botonVerCotizacionCriptomonedas;
	}

	/**
	 * @param botonVerCotizacionCriptomonedas the botonVerCotizacionCriptomonedas to set
	 */
	public void setBotonVerCotizacionCriptomonedas(JButton botonVerCotizacionCriptomonedas) {
		this.botonVerCotizacionCriptomonedas = botonVerCotizacionCriptomonedas;
	}

	/**
	 * @return the botonComprarCriptomonedas
	 */
	public JButton getBotonComprarCriptomonedas() {
		return botonComprarCriptomonedas;
	}

	/**
	 * @param botonComprarCriptomonedas the botonComprarCriptomonedas to set
	 */
	public void setBotonComprarCriptomonedas(JButton botonComprarCriptomonedas) {
		this.botonComprarCriptomonedas = botonComprarCriptomonedas;
	}

	/**
	 * @return the botonVisualizarTransaccion
	 */
	public JButton getBotonVisualizarTransaccion() {
		return botonVisualizarTransaccion;
	}

	/**
	 * @param botonVisualizarTransaccion the botonVisualizarTransaccion to set
	 */
	public void setBotonVisualizarTransaccion(JButton botonVisualizarTransaccion) {
		this.botonVisualizarTransaccion = botonVisualizarTransaccion;
	}

	/**
	 * @return the botonGenerarStock
	 */
	public JButton getBotonGenerarStock() {
		return botonGenerarStock;
	}

	/**
	 * @param botonGenerarStock the botonGenerarStock to set
	 */
	public void setBotonGenerarStock(JButton botonGenerarStock) {
		this.botonGenerarStock = botonGenerarStock;
	}

	/**
	 * @return the botonGenerarActivos
	 */
	public JButton getBotonGenerarActivos() {
		return botonGenerarActivos;
	}

	/**
	 * @param botonGenerarActivos the botonGenerarActivos to set
	 */
	public void setBotonGenerarActivos(JButton botonGenerarActivos) {
		this.botonGenerarActivos = botonGenerarActivos;
	}

	/**
	 * @return the etiquetaMenu
	 */
	public JLabel getEtiquetaMenu() {
		return etiquetaMenu;
	}

	/**
	 * @param etiquetaMenu the etiquetaMenu to set
	 */
	public void setEtiquetaMenu(JLabel etiquetaMenu) {
		this.etiquetaMenu = etiquetaMenu;
	}

	/**
	 * @return the fotoUsuario
	 */
	public JLabel getFotoUsuario() {
		return fotoUsuario;
	}

	/**
	 * @param fotoUsuario the fotoUsuario to set
	 */
	public void setFotoUsuario(JLabel fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}	
	
	
}
