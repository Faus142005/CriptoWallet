package clases;

public class ActivoCripto extends Activo {			

	public ActivoCripto() {
	}

	public ActivoCripto(int idActivo, Usuario usuario, Criptomoneda criptomoneda, double cantidad) {
		super(idActivo, usuario, criptomoneda, cantidad);
	}
	
}
