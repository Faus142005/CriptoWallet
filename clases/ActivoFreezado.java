package clases;

/**
 * Activo freezado hereda lo de activo
 * 
 * Se le agrega en que protocolo se ubican las monedas
 * @author Fausto Y Albertina
 */


public class ActivoFreezado extends Activo{
	
	private String nombreProtocolo;

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
