package comparators.stock;

import java.util.Comparator;
import clases.Stock;

public class ComparadorNomenclatura implements Comparator<Stock> {

	public ComparadorNomenclatura() {};
	
	@Override
	public int compare(Stock s1, Stock s2) {
		return s1.getMoneda().getNomenclatura().compareTo(s2.getMoneda().getNomenclatura());	
	}	

}
