package comparators.activo;

import java.util.Comparator;
import clases.Activo;

public class ComparadorCantidadActivo implements Comparator<Activo> {

	public ComparadorCantidadActivo() {};

	@Override
	public int compare(Activo a1, Activo a2) {
		return Double.compare(a1.getCantidad(),a2.getCantidad());
	}	

}