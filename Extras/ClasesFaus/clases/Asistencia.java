package clases;

/**
 * Asistencia representa que tipo 
 * de ayuda recibio el usuario y cuando
 * @author Fausto Y Albertina
 */

public class Asistencia {

	private String tipoDeAsistencia;
	//Otras clases hechas por nosotros
	private FechaCompleta fecha;
	
	/**
	 * @return El tipoDeAsistencia
	 */
	public String getTipoDeAsistencia() {
		return tipoDeAsistencia;
	}
	/**
	 * @param tipoDeAsistencia El tipoDeAsistencia para setear
	 */
	public void setTipoDeAsistencia(String tipoDeAsistencia) {
		this.tipoDeAsistencia = tipoDeAsistencia;
	}
	/**
	 * @return La fecha
	 */
	public FechaCompleta getFecha() {
		return fecha;
	}
	/**
	 * @param fecha La fecha para setear
	 */
	public void setFecha(FechaCompleta fecha) {
		this.fecha = fecha;
	}
	
	
}
