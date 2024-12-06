package clases;

public class UnidadDeCompra {

	private Usuario usuario;
	private Criptomoneda criptomoneda;
	private double cantidadCriptomoneda;
	private FIAT fiat;
	private double cantidadFIAT;

	public UnidadDeCompra(Usuario usuario, Criptomoneda criptomoneda, double cantidadCriptomoneda, FIAT fiat,
			double cantidadFIAT) {
		this.usuario = usuario;
		this.criptomoneda = criptomoneda;
		this.cantidadCriptomoneda = cantidadCriptomoneda;
		this.fiat = fiat;
		this.cantidadFIAT = cantidadFIAT;
	}
	
	public UnidadDeCompra() {
		
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
	 * @return the criptomoneda
	 */
	public Criptomoneda getCriptomoneda() {
		return criptomoneda;
	}

	/**
	 * @param criptomoneda the criptomoneda to set
	 */
	public void setCriptomoneda(Criptomoneda criptomoneda) {
		this.criptomoneda = criptomoneda;
	}

	/**
	 * @return the cantidadCriptomoneda
	 */
	public double getCantidadCriptomoneda() {
		return cantidadCriptomoneda;
	}

	/**
	 * @param cantidadCriptomoneda the cantidadCriptomoneda to set
	 */
	public void setCantidadCriptomoneda(double cantidadCriptomoneda) {
		this.cantidadCriptomoneda = cantidadCriptomoneda;
	}

	/**
	 * @return the fiat
	 */
	public FIAT getFiat() {
		return fiat;
	}

	/**
	 * @param fiat the fiat to set
	 */
	public void setFiat(FIAT fiat) {
		this.fiat = fiat;
	}

	/**
	 * @return the cantidadFIAT
	 */
	public double getCantidadFIAT() {
		return cantidadFIAT;
	}

	/**
	 * @param cantidadFIAT the cantidadFIAT to set
	 */
	public void setCantidadFIAT(double cantidadFIAT) {
		this.cantidadFIAT = cantidadFIAT;
	}
	
		
}
