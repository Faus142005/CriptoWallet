package clases;

/**
 * FIAT contiene la misma información que Moneda
 * 
 * @author Fausto Y Albertina
 */

public class FIAT extends Moneda{
	public FIAT() {}
			
	public FIAT(char tipo, String nombre, String nomenclatura, double valorDolar,
			String nombreIcono, double stock) {
		super(tipo, nombre, nomenclatura, valorDolar, nombreIcono, stock);		
	}

}
