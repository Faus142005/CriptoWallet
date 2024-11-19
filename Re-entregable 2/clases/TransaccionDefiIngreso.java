package clases;

/**
 * TransaccionDefiIngreso hereda de TransccionDefi Cambia el toString
 * 
 * @author Fausto Y Albertina
 */

public class TransaccionDefiIngreso extends TransaccionDefi {

	public TransaccionDefiIngreso() {

	}

	public TransaccionDefiIngreso(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda,String resumen,
			String nombreProtocolo) {

		super(cantidad, comision, codigoDeTransaccion, moneda, resumen, nombreProtocolo);
	}

	public String toString() {
		return "Has ingresado " + this.getCantidad() + " " + this.getMoneda().getNombre() + " al protocolo "
				+ this.getNombreProtocolo() + ". Comision " + this.getComision() + ". Fecha "
				+ this.getFecha_hora().toString() + ". Codigo de transaccion " + this.getCodigoDeTransaccion();
	}
}
