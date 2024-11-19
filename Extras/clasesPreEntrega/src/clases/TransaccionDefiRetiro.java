package clases;

/**
 * TransaccionDefiRetiro hereda de TransaccionDefi Cambia el toString
 * 
 * @author Fausto Y Albertina
 */

public class TransaccionDefiRetiro extends TransaccionDefi {

	public TransaccionDefiRetiro() {

	}

	public TransaccionDefiRetiro(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda,String resumen,
			String nombreProtocolo) {

		super(cantidad, comision, codigoDeTransaccion, moneda, resumen, nombreProtocolo);
	}

	public String toString() {
		return "Has retirado " + this.getCantidad() + " " + this.getMoneda().getNombre() + " del protocolo "
				+ this.getNombreProtocolo() + ". Comision " + this.getComision() + ". Fecha "
				+ this.getFecha_hora().toString() + ". Codigo de transaccion " + this.getCodigoDeTransaccion();
	}
}
