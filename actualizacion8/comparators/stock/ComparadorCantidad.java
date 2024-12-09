package comparators.stock;

import java.util.Comparator;
import clases.Stock;

public class ComparadorCantidad implements Comparator<Stock> {

	public ComparadorCantidad() {};
	
	@Override
	public int compare(Stock s1, Stock s2) {
		return Double.compare(s1.getCantidad(),s2.getCantidad());		
	}	

}
