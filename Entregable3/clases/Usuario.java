package clases;

public class Usuario {

	private Persona persona;
	private String email;
	private String contraseña;
	private boolean tyc;

	public Usuario() {

	}

	public Usuario(Persona persona, String email, String contraseña, boolean tyc) {

		this.persona = persona;
		this.email = email;
		this.contraseña = contraseña;
		this.tyc = tyc;
	}

	/**
	 * @return the idPersona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}

	/**
	 * @param contraseña the contraseña to set
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	/**
	 * @return the tyc
	 */
	public boolean isTyc() {
		return tyc;
	}

	/**
	 * @param tyc the tyc to set
	 */
	public void setTyc(boolean tyc) {
		this.tyc = tyc;
	}
	
	
}
