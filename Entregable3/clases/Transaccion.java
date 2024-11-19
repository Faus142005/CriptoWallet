package clases;

import java.util.Date;

public class Transaccion {

	private String resumen;
	private Date fecha;

	public Transaccion() {
	}

	public Transaccion(String resumen, Date fecha) {
		this.resumen = resumen;
		this.fecha = fecha;
	}

	/**
	 * @return the resumen
	 */
	public String getResumen() {
		return resumen;
	}

	/**
	 * @param resumen the resumen to set
	 */
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
