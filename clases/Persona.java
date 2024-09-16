package clases;

import java.awt.Image;

/**
 * Person tiene
 * -Billetera
 * -Usuario
 * -DatosBancarios
 * -Fecha: fecha de nacimiento
 * -Nombre
 * -Apellidos
 * -Pais de nacimiento
 * -Pais a operar
 * -Telefono
 * -Email
 * -Foto de perfil
 * @author Fausto Y Albertina
 */

public class Persona {

	private String nombres;
	private String apellidos;
	private String paisDeNacimiento;
	private String paisAOperar;
	private String telefono;
	private String email;
	private Image fotoDePerfil;
	
	//Otras clases hechas por nosotros
	private Billetera billeteraCriptos;
	private Usuario usuario;
	private DatosBancarios datosBancarios;
	private Fecha fechaDeNacimiento;
	
	/**
	 * @return: Los nombres
	 */
	public String getNombres() {
		return nombres;
	}
	
	/**
	 * @param nombres: Los nombres para poner
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	/**
	 * @return: Los apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * @param apellidos: Los apellidos para poner
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return: El paisDeNacimiento
	 */
	public String getPaisDeNacimiento() {
		return paisDeNacimiento;
	}
	/**
	 * @param paisDeNacimiento: El paisDeNacimiento para poner
	 */
	public void setPaisDeNacimiento(String paisDeNacimiento) {
		this.paisDeNacimiento = paisDeNacimiento;
	}
	/**
	 * @return: El paisAOperar
	 */
	public String getPaisAOperar() {
		return paisAOperar;
	}
	/**
	 * @param paisAOperar: El paisAOperar para poner
	 */
	public void setPaisAOperar(String paisAOperar) {
		this.paisAOperar = paisAOperar;
	}
	/**
	 * @return: El telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono: El telefono para poner
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return: El email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email: El email para poner
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return: La fotoDePerfil
	 */
	public Image getFotoDePerfil() {
		return fotoDePerfil;
	}
	/**
	 * @param fotoDePerfil: La fotoDePerfil para poner
	 */
	public void setFotoDePerfil(Image fotoDePerfil) {
		this.fotoDePerfil = fotoDePerfil;
	}
	/**
	 * @return: La billeteraCriptos
	 */
	public Billetera getBilleteraCriptos() {
		return billeteraCriptos;
	}
	/**
	 * @param billeteraCriptos: La billeteraCriptos para poner
	 */
	public void setBilleteraCriptos(Billetera billeteraCriptos) {
		this.billeteraCriptos = billeteraCriptos;
	}
	/**
	 * @return: El usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario: El usuario para poner
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return: Los datosBancarios
	 */
	public DatosBancarios getDatosBancarios() {
		return datosBancarios;
	}
	/**
	 * @param datosBancarios: Los datosBancarios para poner
	 */
	public void setDatosBancarios(DatosBancarios datosBancarios) {
		this.datosBancarios = datosBancarios;
	}
	/**
	 * @return: La fechaDeNacimiento
	 */
	public Fecha getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	/**
	 * @param fechaDeNacimiento: La fechaDeNacimiento para poner
	 */
	public void setFechaDeNacimiento(Fecha fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}	
}
