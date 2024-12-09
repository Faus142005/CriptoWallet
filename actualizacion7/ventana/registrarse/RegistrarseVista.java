package ventana.registrarse;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrarseVista {

	private Dimension dimensiones = new Dimension(560, 520);
	private JPanel panel = new JPanel();

	private JLabel etiquetaNombre = new JLabel("Nombre:");
	private JTextField campoNombre = new JTextField();

	private JLabel etiquetaApellidos = new JLabel("Apellido:");
	private JTextField campoApellidos = new JTextField();

	private JLabel etiquetaEmail = new JLabel("Email:");
	private JTextField campoEmail = new JTextField();

	private JLabel etiquetaContraseña = new JLabel("Contraseña:");
	private JPasswordField campoContraseña = new JPasswordField();
	
	private JLabel etiquetaContraseñasDiferentes = new JLabel("Contraseñas diferentes");

	private JLabel etiquetaContraseñaNuevamente = new JLabel("Contraseña nuevamente:");
	private JPasswordField campoContraseñaNuevamente = new JPasswordField();

	private JButton botonRegistrarse = new JButton("Registrarse");

	private JLabel etiquetaLogin = new JLabel("¿Ya estas registrado? Inicia sesion:");
	private JButton botonLogin = new JButton("Login");

	public RegistrarseVista() {

		this.panel.setLayout(null);
		
		this.etiquetaNombre.setBounds(30, 30, 60, 30);
		this.campoNombre.setBounds(250, 30, 290, 30);
		
		this.etiquetaApellidos.setBounds(30, 90, 90, 30);
		this.campoApellidos.setBounds(250, 90, 290, 30);

		this.etiquetaEmail.setBounds(30, 150, 50, 30);
		this.campoEmail.setBounds(250, 150, 290, 30);
		
		this.etiquetaContraseña.setBounds(30, 210, 90, 30);
		this.campoContraseña.setBounds(250, 210, 290, 30);
		
		this.etiquetaContraseñasDiferentes.setBounds(250, 240, 200, 30);
		this.etiquetaContraseñasDiferentes.setForeground(new Color(255,0,0));
		this.etiquetaContraseñasDiferentes.setVisible(false);
		
		this.etiquetaContraseñaNuevamente.setBounds(30, 270, 180, 30);
		this.campoContraseñaNuevamente.setBounds(250, 270, 290, 30);
		
		this.botonRegistrarse.setBounds(210, 330, 120, 30);

		this.etiquetaLogin.setBounds(80, 390, 260, 30);
		this.botonLogin.setBounds(380, 390, 100, 30);
		
		this.panel.add(etiquetaNombre);
		this.panel.add(campoNombre);
		
		this.panel.add(etiquetaApellidos);
		this.panel.add(campoApellidos);
		
		this.panel.add(etiquetaEmail);
		this.panel.add(campoEmail);
		
		this.panel.add(etiquetaContraseña);
		this.panel.add(campoContraseña);
		
		this.panel.add(etiquetaContraseñasDiferentes)
		;		
		this.panel.add(etiquetaContraseñaNuevamente);
		this.panel.add(campoContraseñaNuevamente);
		
		this.panel.add(botonRegistrarse);
		
		this.panel.add(etiquetaLogin);
		this.panel.add(botonLogin);
		
		this.panel.setName("Registrarse");
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
