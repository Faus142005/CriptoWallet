package comparators.stock;

import java.util.Comparator;
import clases.Stock;

public class ComparadorCantidadStock implements Comparator<Stock> {

	public ComparadorCantidadStock() {};
	
	@Override
	public int compare(Stock s1, Stock s2) {
		return Double.compare(s1.getCantidadActual(),s2.getCantidadActual());		
	}	

}
