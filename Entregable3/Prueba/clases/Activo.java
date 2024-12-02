package clases;

public class Activo {
	private int idActivo;
	private Usuario usuario;
	private Moneda moneda;
	private double cantidad;
	
	public Activo(int idActivo, Usuario usuario, Moneda moneda, double cantidad) {		
		this.idActivo = idActivo;
		this.usuario = usuario;
		this.moneda = moneda;
		this.cantidad = cantidad;
	}
	
	public Activo() {}
	
	/**
	 * @return the idActivo
	 */
	public int getIdActivo() {
		return idActivo;
	}

	/**
	 * @param idActivo the idActivo to set
	 */
	public void setIdActivo(int idActivo) {
		this.idActivo = idActivo;
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

	/**
	 * @return the moneda
	 */
	public Moneda getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
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

	@Override
	public String toString() {
		return "Activo [usuario=" + usuario + ", moneda=" + moneda + ", cantidad=" + cantidad + "]";
	}

}
