package clases;

/**
 * Moneda contiene el nombre de esta misma y su precio
 * @author Fausto Y Albertina
 */

public class Moneda {
	
	private String nombre;
	private double precio;
	
	
	/**
	 * @return El nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre: El nombre para setear
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return El precio
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * @param precio: El precio para setear
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
		
}
