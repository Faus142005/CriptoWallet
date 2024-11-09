package clases;

/**
 * Stock representa la cantidad de 
 * monedas que hay disponible para comprar
 * @author Fausto Y Albertina
 */

public class Stock {

	private double cantidadActual;
	//Otras clases hechas por nosotros
	private Moneda moneda;
	
	
	/**
	 * @return La cantidadActual
	 */
	public double getCantidadActual() {
		return cantidadActual;
	}
	/**
	 * @param cantidadActual La cantidadActual para setear
	 */
	public void setCantidadActual(double cantidadActual) {
		this.cantidadActual = cantidadActual;
	}
	/**
	 * @return La moneda
	 */
	public Moneda getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda La moneda para setear
	 */
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	
	
}
