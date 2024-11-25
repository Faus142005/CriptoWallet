package clases;

public class Moneda {

	private char tipo;
	private String nombre;
	private String nomenclatura;
	private double valorDolar;	
	private String nombreIcono;
	private double stock;	
	//la volatilidad sólo la tiene la subclase Criptomoneda
	
	public Moneda() {
		
	}

	public Moneda(char tipo, String nombre, String nomenclatura, double valorDolar, String nombreIcono, double stock) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.valorDolar = valorDolar;		
		this.nombreIcono = nombreIcono;
		this.stock = stock;
	}	

	/**
	 * @return the tipo
	 */
	public char getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nomenclatura
	 */
	public String getNomenclatura() {
		return nomenclatura;
	}

	/**
	 * @param nomenclatura the nomenclatura to set
	 */
	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	/**
	 * @return the valorDolar
	 */
	public double getValorDolar() {
		return valorDolar;
	}

	/**
	 * @param valorDolar the valorDolar to set
	 */
	public void setValorDolar(double valorDolar) {
		this.valorDolar = valorDolar;
	}	

	/**
	 * @return the nombreIcono
	 */
	public String getNombreIcono() {
		return nombreIcono;
	}

	/**
	 * @param nombreIcono the nombreIcono to set
	 */
	public void setNombreIcono(String nombreIcono) {
		this.nombreIcono = nombreIcono;
	}

	/**
	 * @return the stock
	 */
	public double getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(double stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Moneda [tipo=" + tipo + ", nombre=" + nombre + ", nomenclatura=" + nomenclatura + ", valorDolar="
				+ valorDolar + ", nombreIcono=" + nombreIcono + ", stock=" + stock + "]";
	}
		
}
