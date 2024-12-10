package ventana.ingresar;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import estilos.EncabezadoCriptoWallet;

public class IngresarVista {

	private Dimension dimensiones = new Dimension(600, 400);
	private JPanel panel = new JPanel();
	private static JPanel encabezado = EncabezadoCriptoWallet.crearEncabezado("CriptoWallet", "iconos/logo.png", 600);
	
	private JLabel etiquetaEmail = new JLabel("Email:");
	private JTextField campoEmail = new JTextField();
	private JLabel etiquetaContraseña = new JLabel("Contraseña:");
	private JPasswordField campoContraseña = new JPasswordField();
	private JButton botonLogin = new JButton("Login");

	private JButton botonVerContraseña = new JButton("*");

	private JLabel etiquetaRegistracion = new JLabel("¿No estás registrado? Registrate: ");
	private JButton botonRegistrarse = new JButton("Registrarse");

	public IngresarVista() {

		panel.setLayout(null);

		etiquetaEmail.setBounds(30, 130, 50, 30);
		campoEmail.setBounds(160, 130, 290, 30);
		etiquetaContraseña.setBounds(30, 190, 90, 30);
		campoContraseña.setBounds(160, 190, 290, 30);
		
		botonVerContraseña.setBounds(510,190,40,30);
		
		botonLogin.setBounds(250, 250, 100, 30);
		etiquetaRegistracion.setBounds(115, 310, 250, 30);
		botonRegistrarse.setBounds(365, 310, 120, 30);

		panel.add(etiquetaEmail);
		panel.add(campoEmail);
		panel.add(etiquetaContraseña);
		panel.add(campoContraseña);
		panel.add(botonVerContraseña);
		panel.add(botonLogin);
		panel.add(etiquetaRegistracion);
		panel.add(botonRegistrarse);		

		panel.setName("Ingresar");
		panel.setSize(dimensiones);
		panel.setPreferredSize(dimensiones);
		
		panel.add(encabezado);
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
	 * @return the etiquetaEmail
	 */
	public JLabel getEtiquetaEmail() {
		return etiquetaEmail;
	}

	/**
	 * @param etiquetaEmail the etiquetaEmail to set
	 */
	public void setEtiquetaEmail(JLabel etiquetaEmail) {
		this.etiquetaEmail = etiquetaEmail;
	}

	/**
	 * @return the campoEmail
	 */
	public JTextField getCampoEmail() {
		return campoEmail;
	}

	/**
	 * @param campoEmail the campoEmail to set
	 */
	public void setCampoEmail(JTextField campoEmail) {
		this.campoEmail = campoEmail;
	}

	/**
	 * @return the etiquetaContraseña
	 */
	public JLabel getEtiquetaContraseña() {
		return etiquetaContraseña;
	}

	/**
	 * @param etiquetaContraseña the etiquetaContraseña to set
	 */
	public void setEtiquetaContraseña(JLabel etiquetaContraseña) {
		this.etiquetaContraseña = etiquetaContraseña;
	}

	/**
	 * @return the campoContraseña
	 */
	public JPasswordField getCampoContraseña() {
		return campoContraseña;
	}

	/**
	 * @param campoContraseña the campoContraseña to set
	 */
	public void setCampoContraseña(JPasswordField campoContraseña) {
		this.campoContraseña = campoContraseña;
	}

	/**
	 * @return the botonLogin
	 */
	public JButton getBotonLogin() {
		return botonLogin;
	}

	/**
	 * @param botonLogin the botonLogin to set
	 */
	public void setBotonLogin(JButton botonLogin) {
		this.botonLogin = botonLogin;
	}

	/**
	 * @return the botonVerContraseña
	 */
	public JButton getBotonVerContraseña() {
		return botonVerContraseña;
	}

	/**
	 * @param botonVerContraseña the botonVerContraseña to set
	 */
	public void setBotonVerContraseña(JButton botonVerContraseña) {
		this.botonVerContraseña = botonVerContraseña;
	}

	/**
	 * @return the etiquetaRegistracion
	 */
	public JLabel getEtiquetaRegistracion() {
		return etiquetaRegistracion;
	}

	/**
	 * @param etiquetaRegistracion the etiquetaRegistracion to set
	 */
	public void setEtiquetaRegistracion(JLabel etiquetaRegistracion) {
		this.etiquetaRegistracion = etiquetaRegistracion;
	}

	/**
	 * @return the botonRegistrarse
	 */
	public JButton getBotonRegistrarse() {
		return botonRegistrarse;
	}

	/**
	 * @param botonRegistrarse the botonRegistrarse to set
	 */
	public void setBotonRegistrarse(JButton botonRegistrarse) {
		this.botonRegistrarse = botonRegistrarse;
	}

	
	
}
