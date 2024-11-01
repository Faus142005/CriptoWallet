package clases;

/**
 * Criptomoneda contiene la volatilidad de esta misma
 * 
 * @author Fausto Y Albertina
 */

public class Criptomoneda extends Moneda {
	private double volatilidad;

	/**
	 * @return the volatilidad
	 */
	public double getVolatilidad() {
		return volatilidad;
	}

	/**
	 * @param volatilidad the volatilidad to set
	 */
	public void setVolatilidad(double volatilidad) {
		this.volatilidad = volatilidad;
	}

	public Criptomoneda(String nombre, String nomenclatura, double precio, double volatilidad) {
		super(nombre, nomenclatura, precio);
		this.volatilidad = volatilidad;
	}

	public Criptomoneda() {

	}

}
