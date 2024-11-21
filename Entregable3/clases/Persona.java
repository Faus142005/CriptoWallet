package clases;

public class Persona {

	private String nombres;
	private String apellidos;
	
	public Persona() {
		
	}
	
	public Persona(String nombres, String apellidos) {
		this.nombres = nombres;
		this.apellidos = apellidos;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return the apellido
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
}
