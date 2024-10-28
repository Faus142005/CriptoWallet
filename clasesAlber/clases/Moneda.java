package clases;

/**
 * Moneda contiene el nombre de esta misma y su precio
 * @author Fausto Y Albertina
 */

public class Moneda {
	
	private char tipo;
	private String nombre;
	private String nomenclatura;
	private double precio;
	private double volatilidad; //va del 0 al 100 con posibles decimales	
	
	
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
	 * @return La volatilidad
	 */
	public double getVolatilidad() {
		return volatilidad;
	}
	/**
	 * @param volatilidad La volatilidad para setear
	 */
	public void setVolatilidad(double volatilidad) {
		this.volatilidad = volatilidad;
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
	/**
	 * @return el tipo (Cripto/FIAT)
	 */
	public char getTipo() {
		return tipo;
	}
	/**
	 * @param tipo El tipo a setear
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
		
}
