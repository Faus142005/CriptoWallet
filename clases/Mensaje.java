package clases;

/**
 * Mensaje tiene un mensaje, si fue visto y cuando fue enviado
 * @author Fausto Y Albertina
 */

public class Mensaje {

	private String mensaje;
	private boolean visto;
	
	//Otras clases hechas por nosotros
	private FechaCompleta fecha;

	/**
	 * @return El mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje: El mensaje para setear
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return El visto
	 */
	public boolean isVisto() {
		return visto;
	}

	/**
	 * @param visto: El visto para setear
	 */
	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	/**
	 * @return La fecha
	 */
	public FechaCompleta getFecha() {
		return fecha;
	}

	/**
	 * @param fecha: La fecha para setear
	 */
	public void setFecha(FechaCompleta fecha) {
		this.fecha = fecha;
	}
	
	
}
