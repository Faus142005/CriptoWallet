package clases;

import java.awt.Image;
import java.util.List;

/**
 * Datos de seguridad contiene
 * -Fotos de documento (3)
 * -Si esta verificado
 * -Si acepto los Terminos Y Condiciones
 * -Si esta baneado
 * -Preguntas de seguridad
 * -Que tipo de verificacion para el inicio de sesion tiene
 * -Notificaciones
 * @author Fausto Y Albertina
 */

public class DatosDeSeguridad {

	private boolean notificacionesInicioDeSesion;
	private boolean notificacionesTransaccionRealizada;
	private Image[] fotosDeDocumento;
	private boolean verificado;
	private boolean aceptacionTyC;
	private boolean banned;
	//Otras clases hechas por nosotros
	private List <PreguntaYRespuesta> preguntasDeSeguridad;
	private TipoDeVerificacion tipoVerificacion;
	/**
	 * @return Las notificacionesInicioDeSesion
	 */
	public boolean isNotificacionesInicioDeSesion() {
		return notificacionesInicioDeSesion;
	}
	/**
	 * @param notificacionesInicioDeSesion Las notificacionesInicioDeSesion para setear
	 */
	public void setNotificacionesInicioDeSesion(boolean notificacionesInicioDeSesion) {
		this.notificacionesInicioDeSesion = notificacionesInicioDeSesion;
	}
	/**
	 * @return Las notificacionesTransaccionRealizada
	 */
	public boolean isNotificacionesTransaccionRealizada() {
		return notificacionesTransaccionRealizada;
	}
	/**
	 * @param notificacionesTransaccionRealizada Las notificacionesTransaccionRealizada para setear
	 */
	public void setNotificacionesTransaccionRealizada(boolean notificacionesTransaccionRealizada) {
		this.notificacionesTransaccionRealizada = notificacionesTransaccionRealizada;
	}
	/**
	 * @return Las fotosDeDocumento
	 */
	public Image[] getFotosDeDocumento() {
		return fotosDeDocumento;
	}
	/**
	 * @param fotosDeDocumento Las fotosDeDocumento para setear
	 */
	public void setFotosDeDocumento(Image[] fotosDeDocumento) {
		this.fotosDeDocumento = fotosDeDocumento;
	}
	/**
	 * @return El verificado
	 */
	public boolean isVerificado() {
		return verificado;
	}
	/**
	 * @param verificado El verificado para setear
	 */
	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
	/**
	 * @return La aceptacionTyC
	 */
	public boolean isAceptacionTyC() {
		return aceptacionTyC;
	}
	/**
	 * @param aceptacionTyC La aceptacionTyC para setear
	 */
	public void setAceptacionTyC(boolean aceptacionTyC) {
		this.aceptacionTyC = aceptacionTyC;
	}
	/**
	 * @return El banned
	 */
	public boolean isBanned() {
		return banned;
	}
	/**
	 * @param banned El banned para setear
	 */
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	/**
	 * @return Las preguntasDeSeguridad
	 */
	public List<PreguntaYRespuesta> getPreguntasDeSeguridad() {
		return preguntasDeSeguridad;
	}
	/**
	 * @param preguntasDeSeguridad Las preguntasDeSeguridad para setear
	 */
	public void setPreguntasDeSeguridad(List<PreguntaYRespuesta> preguntasDeSeguridad) {
		this.preguntasDeSeguridad = preguntasDeSeguridad;
	}
	/**
	 * @return El tipoVerificacion
	 */
	public TipoDeVerificacion getTipoVerificacion() {
		return tipoVerificacion;
	}
	/**
	 * @param tipoVerificacion El tipoVerificacion para setear
	 */
	public void setTipoVerificacion(TipoDeVerificacion tipoVerificacion) {
		this.tipoVerificacion = tipoVerificacion;
	}	
}
