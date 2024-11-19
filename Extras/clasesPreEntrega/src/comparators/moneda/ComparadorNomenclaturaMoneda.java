package comparators.moneda;

import java.util.Comparator;
import clases.Moneda;

public class ComparadorNomenclaturaMoneda implements Comparator<Moneda> {

	public ComparadorNomenclaturaMoneda() {};
	
	@Override
	public int compare(Moneda m1, Moneda m2) {
		
		return m1.getNomenclatura().compareTo(m2.getNomenclatura());				
	}	

}
