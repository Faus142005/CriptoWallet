package clases;

/**
 * Fecha es un registro de fecha basico
 * Contiene dia, mes y año
 * @author Fausto Y Albertina
 */

public class Fecha {
	private int dia;
	private int mes;
	private int anio;
	
	/**
	 * @return El dia
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * @param dia El dia para setear
	 */
	public void setDia(int dia) {
		this.dia = dia;
	}

	/**
	 * @return El mes
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param mes El mes para setear
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * @return El anio
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * @param anio El anio para setear
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * 
	 * El metodo se fija si la fecha es menor a la fecha enviada como parametro
	 * 
	 * @param f Fecha a comparar
	 * @return True si se cumple que la fecha es menor a la fecha mandada como parametro
	 */
	public boolean esMenorQue(Fecha f) {
		
		if(this.anio < f.anio) return true;
		else if(this.anio > f.anio) return false;
		
		if(this.mes < f.mes) return true;
		else if(this.mes > f.mes) return false;
		
		if(this.dia < f.dia) return true;
		
		return false;
	}
	
	/**
	 * 
	 * El metodo se fija si las fecha son iguales
	 * 
	 * @param f Fecha a comparar
	 * @return True si se cumple que la fecha es igual a la fecha mandada como parametro
	 */
	
	public boolean equals(Fecha f) {
		return (this.anio == f.anio) && (this.mes == f.mes) && (this.dia == f.dia);
	}
	
	/**
	 * 
	 * El metodo devuelve "Dia/Mes/Año"
	 * 
	 * @return Fecha en String
	 */
	
	public String toString() {
		return this.dia + "/" + this.mes +  "/" + this.anio;
	}
}
