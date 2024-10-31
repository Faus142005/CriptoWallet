package clases;

/**
 * TransaccionVenta hereda de Transaccion
 * Cambia el toString
 * @author Fausto Y Albertina
 */

public class TransaccionVenta extends Transaccion {
	
	public TransaccionVenta() {
		
	}

	public TransaccionVenta(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda, String resumen) {

		super(cantidad, comision, codigoDeTransaccion, moneda, resumen);
	}

	public String toString() {

		return "Se ha vendido " + this.getCantidad() + " " + this.getMoneda().getNombre() + " a "
				+ this.getMoneda().getPrecio() + ". Comision " + this.getComision() + ". Fecha "
				+ this.getFecha_hora().toString() + ". Codigo de transaccion " + this.getCodigoDeTransaccion();
	}
}
