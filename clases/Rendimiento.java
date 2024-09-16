package clases;

/**
 * Rendimiento tiene una cantidad de 
 * porcentaje de rendimiento y en que moneda es
 * @author Fausto Y Albertina
 */

public class Rendimiento {

	private double rendimiento;
	//Otras clases hechas por nosotros
	private Moneda moneda;
	
	
	/**
	 * @return: El rendimiento
	 */
	public double getRendimiento() {
		return rendimiento;
	}
	/**
	 * @param rendimiento: El rendimiento para setear
	 */
	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}
	/**
	 * @return: La moneda
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
