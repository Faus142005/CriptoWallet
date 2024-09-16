package clases;

/**
 * PreguntaYRespuesta sirve para las preguntas de seguridad
 * Contiene la pregunta y la respuesta que debe ser respondida
 * @author Fausto Y Albertina
 */

public class PreguntaYRespuesta {

	private String pregunta;
	private String respuesta;
	
	/**
	 * @return: La pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}
	/**
	 * @param pregunta: La pregunta para setear
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	/**
	 * @return: La respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}
	/**
	 * @param respuesta: La respuesta para setear
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
