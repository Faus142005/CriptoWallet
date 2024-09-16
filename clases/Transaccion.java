package clases;

/**
 * Transaccion contiene informacion de
 * -Cantidad de monedas involucradas
 * -Tipo de moneda
 * -Comision
 * -Fecha
 * -Codigo de operacion
 * @author Fausto Y Albertina
 */

public abstract class Transaccion {

	private double cantidad;
	private double comision;
	private String codigoDeTransaccion;
	// Otras clases hechas por nosotros
	private Moneda moneda; // Una copia con el precio fijado del momento!
	private FechaCompleta fecha;

	// Constructor

	public Transaccion(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda) {
		this.cantidad = cantidad;
		this.comision = comision;
		this.codigoDeTransaccion = codigoDeTransaccion;
		this.moneda = moneda;
	}
	
	

	/**
	 * @return La cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}



	/**
	 * @param cantidad: La cantidad para setear
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}



	/**
	 * @return La comision
	 */
	public double getComision() {
		return comision;
	}



	/**
	 * @param comision: La comision para setear
	 */
	public void setComision(double comision) {
		this.comision = comision;
	}



	/**
	 * @return El codigoDeTransaccion
	 */
	public String getCodigoDeTransaccion() {
		return codigoDeTransaccion;
	}



	/**
	 * @param codigoDeTransaccion: El codigoDeTransaccion para setear
	 */
	public void setCodigoDeTransaccion(String codigoDeTransaccion) {
		this.codigoDeTransaccion = codigoDeTransaccion;
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



	/**
	 * @return La fecha
	 */
	public FechaCompleta getFecha() {
		return fecha;
	}



	/**
	 * @param fecha: La fecha para setear
	 */
	public void setFecha(FechaCompleta fecha) {
		this.fecha = fecha;
	}



	public abstract String toString();
}
