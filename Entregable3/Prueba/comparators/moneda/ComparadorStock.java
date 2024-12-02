package comparators.moneda;

import java.util.Comparator;
import clases.Moneda;

public class ComparadorStock implements Comparator<Moneda> {

	public ComparadorStock() {};
	
	@Override
	public int compare(Moneda m1, Moneda m2) {
		return Double.compare(m1.getStock(), m2.getStock());		
	}	

}
