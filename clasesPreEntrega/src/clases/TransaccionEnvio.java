package clases;

/**
 * TransaccionEnvio hereda de Transaccion
 * Cambia el toString y agrega el codigo del destino
 * @author Fausto Y Albertina
 */

public class TransaccionEnvio extends Transaccion {

	private String codigoDestino;
	
	public TransaccionEnvio() {
		
	}

	public TransaccionEnvio(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda,String resumen,
			String codigoDestino) {

		super(cantidad, comision, codigoDeTransaccion, moneda,resumen);
		this.codigoDestino = codigoDestino;
	}
	
	

	/**
	 * @return El codigoDestino
	 */
	public String getCodigoDestino() {
		return codigoDestino;
	}
	
	

	/**
	 * @param codigoDestino El codigoDestino para setear
	 */
	public void setCodigoDestino(String codigoDestino) {
		this.codigoDestino = codigoDestino;
	}



	public String toString() {

		return "Has enviado " + this.getCantidad() + " " + this.getMoneda().getNombre() + " a " + codigoDestino
				+ ". Comision " + this.getComision() + ". Fecha " + this.getFecha_hora().toString()
				+ ". Codigo de transaccion " + this.getCodigoDeTransaccion();
	}
}
