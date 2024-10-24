package comparators;

import java.util.Comparator;
import clases.Moneda;

public class ComparadorNomenclatura implements Comparator<Moneda> {

	public ComparadorNomenclatura() {};
	
	@Override
	public int compare(Moneda m1, Moneda m2) {
		
		return m1.getNomenclatura().compareTo(m2.getNomenclatura());				
	}	

}
