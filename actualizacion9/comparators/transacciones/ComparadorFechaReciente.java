package comparators.transacciones;

import java.util.Comparator;

import clases.Moneda;
import clases.Transaccion;

public class ComparadorFechaReciente implements Comparator<Transaccion> {

	@Override
	public int compare(Transaccion t1, Transaccion t2) {
		
		return t2.getFecha().compareTo(t1.getFecha());
	}
}
