package clases;

import java.util.List;

/**
 * Chat representa una lista de mensajes
 * @author Fausto Y Albertina
 */

public class Chat extends Asistencia{

	//Otras clases hechas por nosotros
	private List <Mensaje> mensajes;

	/**
	 * @return Los mensajes
	 */
	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	/**
	 * @param mensajes: Los mensajes para setear
	 */
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
}
