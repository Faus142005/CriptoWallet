package clases;

/**
 * FIAT contiene la misma información que Moneda
 * 
 * @author Fausto Y Albertina
 */

public class FIAT extends Moneda {
	public FIAT() {
	}

	public FIAT(int idMoneda, char tipo, String nombre, String nomenclatura, double valorDolar, String nombreIcono,
			double stock) {
		super(idMoneda, tipo, nombre, nomenclatura, valorDolar, nombreIcono, stock);
	}
	
	public FIAT(FIAT fiat) {
		super(fiat.getIdMoneda(), fiat.getTipo(), fiat.getNombre(), fiat.getNomenclatura(), fiat.getValorDolar(), fiat.getNombreIcono(), fiat.getStock());
	}

}
