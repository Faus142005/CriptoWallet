package clases;

/**
 * TransaccionDefi hereda de Transaccion Agrega el nombre del protocolo
 * involucrado
 * 
 * @author Fausto Y Albertina
 */

public abstract class TransaccionDefi extends Transaccion {

	private String nombreProtocolo;

	public TransaccionDefi() {

	}

	public TransaccionDefi(double cantidad, double comision, String codigoDeTransaccion, Moneda moneda,
			String nombreProtocolo) {

		super(cantidad, comision, codigoDeTransaccion, moneda);
		this.nombreProtocolo = nombreProtocolo;
	}

	/**
	 * @return El nombreProtocolo
	 */
	public String getNombreProtocolo() {
		return nombreProtocolo;
	}

	/**
	 * @param nombreProtocolo: El nombreProtocolo para setear
	 */
	public void setNombreProtocolo(String nombreProtocolo) {
		this.nombreProtocolo = nombreProtocolo;
	}

}
