package clases;

/**
 * FIAT contiene la misma información que Moneda
 * 
 * @author Fausto Y Albertina
 */

public class FIAT extends Moneda {
	public FIAT() {
	}
	
	// Constructor
	public FIAT(int idMoneda, String nombre, String nomenclatura, double valorDolar, String nombreIcono) {

		// el tipo se inicializa en F automáticamente
		super(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono);			
	}

	/*public FIAT(int idMoneda, char tipo, String nombre, String nomenclatura, double valorDolar, String nombreIcono,
			double stock) {
		super(idMoneda, tipo, nombre, nomenclatura, valorDolar, nombreIcono);
	}*/
	
	public FIAT(FIAT fiat) {
		super(fiat.getIdMoneda(), fiat.getNombre(), fiat.getNomenclatura(), fiat.getValorDolar(), fiat.getNombreIcono());
	}

}
