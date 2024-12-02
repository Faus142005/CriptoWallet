package clases;

/**
 * Criptomoneda contiene la volatilidad de esta misma
 * 
 * @author Fausto Y Albertina
 */

public class Criptomoneda extends Moneda {
	private double volatilidad;

	// Constructor
	public Criptomoneda(int idMoneda, String nombre, String nomenclatura, double valorDolar, String nombreIcono,
			double stock, double volatilidad) {

		// el tipo se inicializa en C automáticamente
		super(idMoneda, 'C', nombre, nomenclatura, valorDolar, nombreIcono, stock);
		this.volatilidad = volatilidad;
	}

	public Criptomoneda(Criptomoneda criptomoneda) {
		super(criptomoneda.getIdMoneda(), criptomoneda.getTipo(), criptomoneda.getNombre(),
				criptomoneda.getNomenclatura(), criptomoneda.getValorDolar(), criptomoneda.getNombreIcono(),
				criptomoneda.getStock());
		this.volatilidad = criptomoneda.volatilidad;
	}

	public Criptomoneda() {
	}

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
