package clases;

public class ActivoFIAT extends Activo {

	public ActivoFIAT() {
	}

	public ActivoFIAT(int idActivo, Usuario usuario, FIAT fiat, double cantidad) {
		super(idActivo, usuario, fiat, cantidad);
	}
	
	public FIAT getFIAT() {
		return (FIAT) super.getMoneda();
	}
}
