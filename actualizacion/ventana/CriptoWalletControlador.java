package ventana;

public interface CriptoWalletControlador {
	public enum IdActualizacion {
		NuevasMonedas, NuevasFIATS, NuevasCriptomonedas, ActualizacionMonedas, ActualizacionCriptomonedas,
		ActualizacionFIATS, ActualizacionActivos
	};

	public abstract void actualizar(IdActualizacion id);
}
