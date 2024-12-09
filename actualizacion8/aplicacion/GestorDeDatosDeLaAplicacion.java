package aplicacion;

import clases.Criptomoneda;
import clases.Usuario;

public class GestorDeDatosDeLaAplicacion {

	private static Usuario usuarioConectado = null;
	private static Criptomoneda criptomonedaSeleccionada = new Criptomoneda(-1, null, null, 1, null, 1);
	/**
	 * @return the usuarioConectado
	 */
	public static Usuario getUsuarioConectado() {
		return usuarioConectado;
	}
	/**
	 * @param usuarioConectado the usuarioConectado to set
	 */
	public static void setUsuarioConectado(Usuario usuarioConectado) {
		GestorDeDatosDeLaAplicacion.usuarioConectado = usuarioConectado;
	}
	/**
	 * @return the criptomonedaSeleccionada
	 */
	public static Criptomoneda getCriptomonedaSeleccionada() {
		return criptomonedaSeleccionada;
	}
	/**
	 * @param criptomonedaSeleccionada the criptomonedaSeleccionada to set
	 */
	public static void setCriptomonedaSeleccionada(Criptomoneda criptomonedaSeleccionada) {
		GestorDeDatosDeLaAplicacion.criptomonedaSeleccionada = criptomonedaSeleccionada;
	}
	
	
}
