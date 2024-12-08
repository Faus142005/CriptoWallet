package ventana;

public interface CriptoWalletControlador {
	public enum IdActualizacion {
		NuevasMonedas, NuevasFIATS, NuevasCriptomonedas, ActualizacionMonedas, ActualizacionCriptomonedas,
		ActualizacionFIATS, ActualizacionActivos, ActualizacionTransferencias
	};

	public abstract void actualizar(IdActualizacion id);
}
