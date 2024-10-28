package clases;

/**
 * Activo representa la tenencia de
 * un solo tipo de monedas y la cantidad
 * @author Fausto Y Albertina
 */

public class Activo {

	private double cantidad;
	//Otras clases hechas por nosotros
	private Moneda moneda;
	
	
	/**
	 * @return El cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad: El cantidad para setear
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return La moneda
	 */
	public Moneda getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda: La moneda para setear
	 */
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}	
}
