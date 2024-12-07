package aplicacion;

import java.util.List;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Usuario;

public class GestorDeDatosDeLaAplicacion {

	private static Usuario usuarioConectado = new Usuario(1, null, null, null, false);
	private static List <Criptomoneda> criptomonedas;
	private static List <FIAT> fiats;
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
	 * @return the criptomonedas
	 */
	public static List<Criptomoneda> getCriptomonedas() {
		return criptomonedas;
	}
	/**
	 * @param criptomonedas the criptomonedas to set
	 */
	public static void setCriptomonedas(List<Criptomoneda> criptomonedas) {
		GestorDeDatosDeLaAplicacion.criptomonedas = criptomonedas;
	}
	/**
	 * @return the fiats
	 */
	public static List<FIAT> getFiats() {
		return fiats;
	}
	/**
	 * @param fiats the fiats to set
	 */
	public static void setFiats(List<FIAT> fiats) {
		GestorDeDatosDeLaAplicacion.fiats = fiats;
	}
	
	
	
}
