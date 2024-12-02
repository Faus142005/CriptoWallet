package clases;

import java.sql.Date;

public class Transaccion {

	private int idTransaccion;
	private String resumen;
	private Date fecha;

	public Transaccion() {
	}

	public Transaccion(int idTransaccion, String resumen, Date fecha) {
		this.idTransaccion = idTransaccion;
		this.resumen = resumen;
		this.fecha = fecha;
	}

	/**
	 * @return the idTransaccion
	 */
	public int getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
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
