package clases;

/**
 * Moneda contiene el nombre de esta misma y su precio
 * @author Fausto Y Albertina
 */

public class Moneda {

	private String nombre;
	private String nomenclatura;
	private double precio;	
	
	/**
	 * @return El nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre El nombre para setear
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
	 * @param precio El precio para setear
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return La nomenclatura
	 */
	public String getNomenclatura() {
		return nomenclatura;
	}
	/**
	 * @param nomenclatura La nomenclatura a setear
	 */
	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}
	
	public Moneda() {};
	
	public Moneda(String nombre, String nomenclatura, double precio) {
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.precio = precio;
	}		
	
		
}
