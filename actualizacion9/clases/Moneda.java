package clases;

public class Moneda {
	
	private int idMoneda;
	private String nombre;
	private String nomenclatura;
	private double valorDolar;	
	private String nombreIcono;
	
	public Moneda() {
		
	}

	public Moneda(int idMoneda, String nombre, String nomenclatura, double valorDolar, String nombreIcono) {
		this.idMoneda = idMoneda;
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.valorDolar = valorDolar;		
		this.nombreIcono = nombreIcono;		
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


	@Override
	public String toString() {
		return "Moneda [nombre=" + nombre + ", nomenclatura=" + nomenclatura + ", valorDolar="
				+ valorDolar + ", nombreIcono=" + nombreIcono + "]";
	}
		
}
