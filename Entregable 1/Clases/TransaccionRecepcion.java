package clases;

/**
 * TransaccionRecepcion hereda de Transaccion
 * Cambia el toString y agrega el codigo de donde proviene el dinero
 * @author Fausto Y Albertina
 */

public class TransaccionRecepcion extends Transaccion {

	private String codigoOrigen;
	
	public TransaccionRecepcion() {
		
	}

	public TransaccionRecepcion(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda,
			String codigoOrigen) {

		super(cantidad, comision, codigoDeTransaccion, moneda);
		this.codigoOrigen = codigoOrigen;
	}	

	/**
	 * @return El codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}
	
	

	/**
	 * @param codigoOrigen El codigoOrigen para setear
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	public String toString() {

		return "Has recibido " + this.getCantidad() + " " + this.getMoneda().getNombre() + " de " + codigoOrigen
				+ ". Comision del emisor " + this.getComision() + ". Fecha " + this.getFecha().toString()
				+ ". Codigo de transaccion " + this.getCodigoDeTransaccion();
	}
}
