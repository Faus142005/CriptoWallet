package clases;

/**
 * Usuario tiene un 
 * -ID : Identificador unico
 * -Contraseña de inicio de sesion
 * -Si esta dado de baja
 * -Datos de seguridad
 * @author Fausto Y Albertina
 */

public class Usuario {
	
	private String id;
	private String contraseña;
	private boolean dadoDeBaja;
	//Otras clases hecha por nosotros
	private DatosDeSeguridad seguridad;
	
	
	/**
	 * @return: El id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id: El id para setear
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return: La contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}
	/**
	 * @param contraseña: La contraseña para setear
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	/**
	 * @return: El dadoDeBaja
	 */
	public boolean isDadoDeBaja() {
		return dadoDeBaja;
	}
	/**
	 * @param dadoDeBaja: El dadoDeBaja para setear
	 */
	public void setDadoDeBaja(boolean dadoDeBaja) {
		this.dadoDeBaja = dadoDeBaja;
	}
	/**
	 * @return: La seguridad
	 */
	public DatosDeSeguridad getSeguridad() {
		return seguridad;
	}
	/**
	 * @param seguridad: La seguridad para setear
	 */
	public void setSeguridad(DatosDeSeguridad seguridad) {
		this.seguridad = seguridad;
	}
	
	
	
}
