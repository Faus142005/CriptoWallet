package clases;

public class Persona {

	private String nombres;
	private String apellidos;
	
	public Persona() {
		
	}
	
	public Persona(String nombre, String apellido) {
		this.nombres = nombre;
		this.apellidos = apellido;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombres;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombres = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellidos;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellidos = apellido;
	}
	
	
}
