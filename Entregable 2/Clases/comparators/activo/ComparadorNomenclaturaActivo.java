package comparators.activo;
import java.util.Comparator;
import clases.Activo;

public class ComparadorNomenclaturaActivo implements Comparator<Activo> {

	public ComparadorNomenclaturaActivo() {};

	@Override
	public int compare(Activo a1, Activo a2) {
		return a1.getMoneda().getNomenclatura().compareTo(a2.getMoneda().getNomenclatura());
	}	

}