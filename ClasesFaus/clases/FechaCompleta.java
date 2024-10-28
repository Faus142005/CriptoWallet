package clases;

/**
 * FechaCompleta hereda de fecha
 * Agrega minuto y hora
 * @author Fausto Y Albertina
 */

public class FechaCompleta extends Fecha {

	private int min;
	private int hora;
	
	

	/**
	 * @return El min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min: El min para setear
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return La hora
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * @param hora: La hora para setear
	 */
	public void setHora(int hora) {
		this.hora = hora;
	}

	/**
	 * 
	 * El metodo se fija si la fecha es menor a la fecha enviada como parametro
	 * 
	 * @param f: FechaCompleta a comparar
	 * @return True si se cumple que fecha es menor a la fecha como parametro
	 */

	public boolean esMenorQue(FechaCompleta f) {

		if(!super.esMenorQue(f) && !super.equals(f))
			return false;

		if (this.hora < f.hora)
			return true;
		else if (this.hora > f.hora)
			return false;

		if (this.min < f.min)
			return true;
		return false;
	}
	
	/**
	 * 
	 * El metodo se fija si las fecha son iguales
	 * 
	 * @param f: FechaCompleta a comparar
	 * @return True si se cumple que fecha es igual a la fecha como parametro
	 */	
	public boolean equals(FechaCompleta f) {
		return super.equals(f) && (this.hora == f.hora) && (this.min == f.min);
	}
	
	/**
	 * 
	 * El metodo devuelve "Dia/Mes/Año Hora: Hora:Min"
	 * 
	 * @return FechaCompleta en String
	 */
	
	public String toString() {
		
		return super.toString() + "Hora: " + this.hora + ":" + this.min;
	}
}
