package clases;

public class Usuario {

	private int idUsuario;
	private Persona persona;
	private String email;
	private String contraseña;
	private boolean tyc;

	public Usuario() {

	}

	public Usuario(int idUsuario, Persona persona, String email, String contraseña, boolean tyc) {
		this.idUsuario = idUsuario;
		this.persona = persona;
		this.email = email;
		this.contraseña = contraseña;
		this.tyc = tyc;
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
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
