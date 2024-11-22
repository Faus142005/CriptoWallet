package clases;

/**
 * Criptomoneda contiene la volatilidad de esta misma
 * 
 * @author Fausto Y Albertina
 */

public class Criptomoneda extends Moneda {
	private double volatilidad;

	//Constructor
	public Criptomoneda(String nombre, String nomenclatura, double valorDolar, double volatilidad,
			String nombreIcono, double stock) {		
		
		//el tipo se inicializa en C automáticamente
		super('C', nombre, nomenclatura, valorDolar, nombreIcono, stock);		
		this.volatilidad=volatilidad;
	}
	
	public Criptomoneda() {	}
	
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

}
