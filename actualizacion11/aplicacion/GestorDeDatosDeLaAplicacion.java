package aplicacion;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Usuario;

public class GestorDeDatosDeLaAplicacion {

	private static Usuario usuarioConectado = null;
	private static Criptomoneda criptomonedaSeleccionada = new Criptomoneda(-1, null, null, 1, null, 1);
	private static FIAT fiatSeleccionada = new FIAT(-1, null, null, 1, null);
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
	/**
	 * @return the fiatSeleccionada
	 */
	public static FIAT getFiatSeleccionada() {
		return fiatSeleccionada;
	}
	/**
	 * @param fiatSeleccionada the fiatSeleccionada to set
	 */
	public static void setFiatSeleccionada(FIAT fiatSeleccionada) {
		GestorDeDatosDeLaAplicacion.fiatSeleccionada = fiatSeleccionada;
	}
	
	
}
