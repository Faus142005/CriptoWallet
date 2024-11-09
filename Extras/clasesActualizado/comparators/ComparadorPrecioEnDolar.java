package comparators;

import java.util.Comparator;
import clases.Moneda;

public class ComparadorPrecioEnDolar implements Comparator<Moneda> {

	public ComparadorPrecioEnDolar() {};
	
	@Override
	public int compare(Moneda m1, Moneda m2) {
		
		return Double.compare(m1.getPrecio(), m2.getPrecio());				
	}	

}