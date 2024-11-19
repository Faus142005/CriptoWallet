package clases;

public class Usuario {

	private int idPersona;
	private String email;
	private String contraseña;
	private boolean tyc;

	public Usuario() {

	}

	public Usuario(int idPersona, String email, String contraseña, boolean tyc) {

		this.idPersona = idPersona;
		this.email = email;
		this.contraseña = contraseña;
		this.tyc = tyc;
	}

	/**
	 * @return the idPersona
	 */
	public int getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
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
