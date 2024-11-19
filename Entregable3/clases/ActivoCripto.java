package clases;

public class ActivoCripto {

	private int idUsuario;
	private int idMoneda;
	private double cantidad;

	public ActivoCripto() {
	}

	public ActivoCripto(int idUsuario, int idMoneda, double cantidad) {
		this.idUsuario = idUsuario;
		this.idMoneda = idMoneda;
		this.cantidad = cantidad;
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the idMoneda
	 */
	public int getIdMoneda() {
		return idMoneda;
	}

	/**
	 * @param idMoneda the idMoneda to set
	 */
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}

	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
