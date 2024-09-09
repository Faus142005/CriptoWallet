package gyifvuyvyuvuygfuy;

public abstract class TipoDeVerificacion {

	public abstract void enviar();
}


public class objetoUnico{

	private static objetoUnico singleton;
	
	public objetoUnico getObjetoUnicoInstance() {
		if (singleton == null) {
			singleton = new objetoUnico();
		}
		return singleton;
	}
}
