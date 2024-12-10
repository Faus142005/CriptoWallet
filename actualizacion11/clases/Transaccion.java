package clases;

import java.sql.Timestamp;

public class Transaccion {

	private int idTransaccion;
	private String resumen;
	private Timestamp fecha;
	private Usuario usuario;

	public Transaccion() {
	}

	public Transaccion(int idTransaccion, String resumen, Timestamp fecha, Usuario usuario) {
		this.idTransaccion = idTransaccion;
		this.resumen = resumen;
		this.fecha = fecha;
		this.usuario = usuario;
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
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
