package clases;

public class Moneda {

	private char tipo;
	private String nombre;
	private String nomenclatura;
	private double valorDolar;
	private double volatilidad;
	private double stock;
	private String nombreIcono;
	
	public Moneda() {
		
	}

	public Moneda(char tipo, String nombre, String nomenclatura, double valorDolar, double volatilidad, double stock,
			String nombreIcono) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.valorDolar = valorDolar;
		this.volatilidad = volatilidad;
		this.stock = stock;
		this.nombreIcono = nombreIcono;
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
	 * @return the volatilidad
	 */
	public double getVolatilidad() {
		return volatilidad;
	}

	/**
	 * @param volatilidad the volatilidad to set
	 */
	public void setVolatilidad(double volatilidad) {
		this.volatilidad = volatilidad;
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
}
