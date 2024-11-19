package clases;

import java.sql.Date;

/**
 * Transaccion contiene informacion de
 * -Cantidad de monedas involucradas
 * -Tipo de moneda
 * -Comision
 * -Fecha
 * -Codigo de operacion
 * @author Fausto Y Albertina
 */

public class Transaccion {

	private double cantidad;
	private double comision;
	private String codigoDeTransaccion;
	private String resumen;
	// Otras clases hechas por nosotros
	private Moneda moneda; // Una copia con el precio fijado del momento!
	private FechaCompleta fechaCompleta;
	private Date fecha_hora;

	// Constructor
	
	public Transaccion() {
		
	}

	public Transaccion(String resumen) {
		this.resumen=resumen;		
	}
	
	public Transaccion(String resumen, Date fecha_hora) {
		this.resumen=resumen;
		this.fecha_hora=fecha_hora;
	}
	
	public Transaccion(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda, String resumen) {
		this.cantidad = cantidad;
		this.comision = comision;
		this.codigoDeTransaccion = codigoDeTransaccion;
		this.moneda = moneda;
		this.resumen = resumen;
	}		

	/**
	 * @return La cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad La cantidad para setear
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
	 * @param comision La comision para setear
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
	 * @param codigoDeTransaccion El codigoDeTransaccion para setear
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
	 * @param moneda La moneda para setear
	 */
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return the resumen
	 */
	public String getResumen() {
		return resumen;
	}
	/**
	 * @param resumen the resumen to set
	 */
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	/**
	 * @return the fechaCompleta
	 */
	public FechaCompleta getFechaCompleta() {
		return fechaCompleta;
	}
	/**
	 * @param fechaCompleta the fechaCompleta to set
	 */
	public void setFechaCompleta(FechaCompleta fechaCompleta) {
		this.fechaCompleta = fechaCompleta;
	}

	/**
	 * @return the fecha_hora
	 */
	public Date getFecha_hora() {
		return fecha_hora;
	}
	/**
	 * @param fecha_hora the fecha_hora to set
	 */
	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	
}
