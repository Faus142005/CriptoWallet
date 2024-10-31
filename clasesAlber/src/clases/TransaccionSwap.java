package clases;

/**
 * TransaccionSwap hereda de Transaccion
 * Cambia el toString y agrega a que moneda se cambio y cuantas
 * @author Fausto Y Albertina
 */

public class TransaccionSwap extends Transaccion {

	// Otras clases hechas por nosotros
	private Moneda monedaACambiar;
	private double cantidadMonedasResultantes;
	
	public TransaccionSwap() {
		
	}

	public TransaccionSwap(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda, String resumen,
			Moneda monedaACambiar, double cantidadMonedasResultantes) {

		super(cantidad, comision, codigoDeTransaccion, moneda, resumen);
		this.monedaACambiar = monedaACambiar;
		this.cantidadMonedasResultantes = cantidadMonedasResultantes;
	}
	
	

	/**
	 * @return La monedaACambiar
	 */
	public Moneda getMonedaACambiar() {
		return monedaACambiar;
	}
	
	/**
	 * @param monedaACambiar La monedaACambiar para setear
	 */
	public void setMonedaACambiar(Moneda monedaACambiar) {
		this.monedaACambiar = monedaACambiar;
	}

	/**
	 * @return La cantidadMonedasResultantes
	 */
	public double getCantidadMonedasResultantes() {
		return cantidadMonedasResultantes;
	}

	/**
	 * @param cantidadMonedasResultantes La cantidadMonedasResultantes para setear
	 */
	public void setCantidadMonedasResultantes(double cantidadMonedasResultantes) {
		this.cantidadMonedasResultantes = cantidadMonedasResultantes;
	}



	public String toString() {

		return "Has cambiado " + this.getCantidad() + " " + this.getMoneda().getNombre() + " con valor "
				+ this.getMoneda().getPrecio() + " a " + cantidadMonedasResultantes + " " + monedaACambiar.getNombre() + " con valor "
				+ monedaACambiar.getPrecio() + ". Comision " + this.getComision() + ". Fecha "
				+ this.getFecha_hora().toString() + ". Codigo de transaccion " + this.getCodigoDeTransaccion();
	}
}
