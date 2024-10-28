package clases;

import java.util.List;

/**
 * Billetera representa cuantos activos liquidos/freezados tiene el usuario
 * Tambien cuenta con el historial de transacciones del usuario
 * @author Fausto Y Albertina
 */

public class Billetera {

	private double balance;
	//Otras clases hechas por nosotros
	private List <ActivoLiquido> activosLiquidos;
	private List <ActivoFreezado> activosFreezados;
	private List <Transaccion> historialDeTransacciones;
	
	
	/**
	 * @return El balance
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * @param balance: El balance para setear
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * @return Los activosLiquidos
	 */
	public List<ActivoLiquido> getActivosLiquidos() {
		return activosLiquidos;
	}
	/**
	 * @param activosLiquidos: Los activosLiquidos para setear
	 */
	public void setActivosLiquidos(List<ActivoLiquido> activosLiquidos) {
		this.activosLiquidos = activosLiquidos;
	}
	/**
	 * @return Los activosFreezados
	 */
	public List<ActivoFreezado> getActivosFreezados() {
		return activosFreezados;
	}
	/**
	 * @param activosFreezados: Los activosFreezados para setear
	 */
	public void setActivosFreezados(List<ActivoFreezado> activosFreezados) {
		this.activosFreezados = activosFreezados;
	}
	/**
	 * @return El historialDeTransacciones
	 */
	public List<Transaccion> getHistorialDeTransacciones() {
		return historialDeTransacciones;
	}
	/**
	 * @param historialDeTransacciones: El historialDeTransacciones para setear
	 */
	public void setHistorialDeTransacciones(List<Transaccion> historialDeTransacciones) {
		this.historialDeTransacciones = historialDeTransacciones;
	}
	
	
}
