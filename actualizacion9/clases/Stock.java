package clases;

public class Stock {
	private Moneda moneda;
	private double cantidad;
	private int idStock;
	
	public Stock() {};
	
	public Stock(int idStock, Moneda moneda, double cantidad) {
		super();
		this.idStock = idStock;
		this.moneda = moneda;
		this.cantidad = cantidad;
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

	/**
	 * @return the idStock
	 */
	public int getIdStock() {
		return idStock;
	}

	/**
	 * @param idStock the idStock to set
	 */
	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}			
	
}
