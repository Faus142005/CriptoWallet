package clases;

import java.awt.Image;

/**
 * MonedaCompleta hereda a moneda
 * y le agrega abreviatura e icono
 * @author Fausto Y Albertina
 */

public class MonedaCompleta extends Moneda{
	
	
	private String abreviatura;
	private Image icono;
	
	/**
	 * @return La abreviatura
	 */
	public String getAbreviatura() {
		return abreviatura;
	}
	/**
	 * @param abreviatura La abreviatura para setear
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	/**
	 * @return El icono
	 */
	public Image getIcono() {
		return icono;
	}
	/**
	 * @param icono El icono para setear
	 */
	public void setIcono(Image icono) {
		this.icono = icono;
	}
	
	
}
