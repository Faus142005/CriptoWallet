package ventana.registrarse;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import estilos.EncabezadoCriptoWallet;

public class RegistrarseVista {

	private Dimension dimensiones = new Dimension(650, 620);
	private JPanel panel = new JPanel();
	private static JPanel encabezado = EncabezadoCriptoWallet.crearEncabezado("CriptoWallet", "iconos/logo.png", 650);

	private JLabel etiquetaNombre = new JLabel("Nombre:");
	private JTextField campoNombre = new JTextField();

	private JLabel etiquetaApellidos = new JLabel("Apellido:");
	private JTextField campoApellidos = new JTextField();

	private JLabel etiquetaEmail = new JLabel("Email:");
	private JTextField campoEmail = new JTextField();
	
	private JLabel etiquetaDebeIngresarEmail = new JLabel("Debe ingresar un Email");

	private JLabel etiquetaContraseña = new JLabel("Contraseña:");
	private JPasswordField campoContraseña = new JPasswordField();
	private JButton botonVerContraseña1 = new JButton("*");
	
	private JLabel etiquetaContraseñasDiferentes = new JLabel("Contraseñas diferentes");		

	private JLabel etiquetaContraseñaNuevamente = new JLabel("Contraseña nuevamente:");
	private JPasswordField campoContraseñaNuevamente = new JPasswordField();
	private JButton botonVerContraseña2 = new JButton("*");

	private JButton botonRegistrarse = new JButton("Registrarse");

	private JLabel etiquetaLogin = new JLabel("¿Ya estas registrado? Inicia sesion:");
	private JButton botonLogin = new JButton("Login");	

	public RegistrarseVista() {

		this.panel.setLayout(null);
		
		this.etiquetaNombre.setBounds(30, 130, 60, 30);
		this.campoNombre.setBounds(250, 130, 290, 30);
		
		this.etiquetaApellidos.setBounds(30, 190, 90, 30);
		this.campoApellidos.setBounds(250, 190, 290, 30);

		this.etiquetaEmail.setBounds(30, 250, 50, 30);
		this.campoEmail.setBounds(250, 250, 290, 30);
		
		this.etiquetaContraseña.setBounds(30, 310, 90, 30);
		this.campoContraseña.setBounds(250, 310, 290, 30);
		
		this.botonVerContraseña1.setBounds(600, 310, 40, 30);
		
		this.etiquetaContraseñasDiferentes.setBounds(250, 340, 200, 30);
		this.etiquetaContraseñasDiferentes.setForeground(new Color(255,0,0));
		this.etiquetaContraseñasDiferentes.setVisible(false);
		
		this.etiquetaDebeIngresarEmail.setBounds(250, 280, 200, 30);
		this.etiquetaDebeIngresarEmail.setForeground(new Color(255,0,0));
		this.etiquetaDebeIngresarEmail.setVisible(false);
		
		this.etiquetaContraseñaNuevamente.setBounds(30, 370, 180, 30);
		this.campoContraseñaNuevamente.setBounds(250, 370, 290, 30);
		
		this.botonVerContraseña2.setBounds(600, 370, 40, 30);
		
		this.botonRegistrarse.setBounds(265, 430, 120, 30);

		this.etiquetaLogin.setBounds(120, 490, 260, 30);
		this.botonLogin.setBounds(420, 490, 100, 30);
		
		this.panel.add(etiquetaNombre);
		this.panel.add(campoNombre);
		
		this.panel.add(etiquetaApellidos);
		this.panel.add(campoApellidos);
		
		this.panel.add(etiquetaEmail);
		this.panel.add(campoEmail);		
		this.panel.add(etiquetaDebeIngresarEmail);
		
		this.panel.add(etiquetaContraseña);
		this.panel.add(campoContraseña);
		
		this.panel.add(botonVerContraseña1);
		
		this.panel.add(etiquetaContraseñasDiferentes);		
		this.panel.add(etiquetaContraseñaNuevamente);
		this.panel.add(campoContraseñaNuevamente);	
		
		this.panel.add(botonVerContraseña2);
		
		this.panel.add(botonRegistrarse);
		
		this.panel.add(etiquetaLogin);
		this.panel.add(botonLogin);
		
		this.panel.setName("Registrarse");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);
		
		this.panel.add(encabezado);
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
	 * @return the etiquetaNombre
	 */
	public JLabel getEtiquetaNombre() {
		return etiquetaNombre;
	}


	/**
	 * @param etiquetaNombre the etiquetaNombre to set
	 */
	public void setEtiquetaNombre(JLabel etiquetaNombre) {
		this.etiquetaNombre = etiquetaNombre;
	}


	/**
	 * @return the campoNombre
	 */
	public JTextField getCampoNombre() {
		return campoNombre;
	}


	/**
	 * @param campoNombre the campoNombre to set
	 */
	public void setCampoNombre(JTextField campoNombre) {
		this.campoNombre = campoNombre;
	}


	/**
	 * @return the etiquetaApellidos
	 */
	public JLabel getEtiquetaApellidos() {
		return etiquetaApellidos;
	}


	/**
	 * @param etiquetaApellidos the etiquetaApellidos to set
	 */
	public void setEtiquetaApellidos(JLabel etiquetaApellidos) {
		this.etiquetaApellidos = etiquetaApellidos;
	}


	/**
	 * @return the campoApellidos
	 */
	public JTextField getCampoApellidos() {
		return campoApellidos;
	}


	/**
	 * @param campoApellidos the campoApellidos to set
	 */
	public void setCampoApellidos(JTextField campoApellidos) {
		this.campoApellidos = campoApellidos;
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
	 * @return the etiquetaDebeIngresarEmail
	 */
	public JLabel getEtiquetaDebeIngresarEmail() {
		return etiquetaDebeIngresarEmail;
	}


	/**
	 * @param etiquetaDebeIngresarEmail the etiquetaDebeIngresarEmail to set
	 */
	public void setEtiquetaDebeIngresarEmail(JLabel etiquetaDebeIngresarEmail) {
		this.etiquetaDebeIngresarEmail = etiquetaDebeIngresarEmail;
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
	 * @return the botonVerContraseña1
	 */
	public JButton getBotonVerContraseña1() {
		return botonVerContraseña1;
	}


	/**
	 * @param botonVerContraseña1 the botonVerContraseña1 to set
	 */
	public void setBotonVerContraseña1(JButton botonVerContraseña1) {
		this.botonVerContraseña1 = botonVerContraseña1;
	}


	/**
	 * @return the etiquetaContraseñasDiferentes
	 */
	public JLabel getEtiquetaContraseñasDiferentes() {
		return etiquetaContraseñasDiferentes;
	}


	/**
	 * @param etiquetaContraseñasDiferentes the etiquetaContraseñasDiferentes to set
	 */
	public void setEtiquetaContraseñasDiferentes(JLabel etiquetaContraseñasDiferentes) {
		this.etiquetaContraseñasDiferentes = etiquetaContraseñasDiferentes;
	}


	/**
	 * @return the etiquetaContraseñaNuevamente
	 */
	public JLabel getEtiquetaContraseñaNuevamente() {
		return etiquetaContraseñaNuevamente;
	}


	/**
	 * @param etiquetaContraseñaNuevamente the etiquetaContraseñaNuevamente to set
	 */
	public void setEtiquetaContraseñaNuevamente(JLabel etiquetaContraseñaNuevamente) {
		this.etiquetaContraseñaNuevamente = etiquetaContraseñaNuevamente;
	}


	/**
	 * @return the campoContraseñaNuevamente
	 */
	public JPasswordField getCampoContraseñaNuevamente() {
		return campoContraseñaNuevamente;
	}


	/**
	 * @param campoContraseñaNuevamente the campoContraseñaNuevamente to set
	 */
	public void setCampoContraseñaNuevamente(JPasswordField campoContraseñaNuevamente) {
		this.campoContraseñaNuevamente = campoContraseñaNuevamente;
	}


	/**
	 * @return the botonVerContraseña2
	 */
	public JButton getBotonVerContraseña2() {
		return botonVerContraseña2;
	}


	/**
	 * @param botonVerContraseña2 the botonVerContraseña2 to set
	 */
	public void setBotonVerContraseña2(JButton botonVerContraseña2) {
		this.botonVerContraseña2 = botonVerContraseña2;
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


	/**
	 * @return the etiquetaLogin
	 */
	public JLabel getEtiquetaLogin() {
		return etiquetaLogin;
	}


	/**
	 * @param etiquetaLogin the etiquetaLogin to set
	 */
	public void setEtiquetaLogin(JLabel etiquetaLogin) {
		this.etiquetaLogin = etiquetaLogin;
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

}
