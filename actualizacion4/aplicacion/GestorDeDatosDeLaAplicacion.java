package aplicacion;

import clases.Usuario;

public class GestorDeDatosDeLaAplicacion {

	private static Usuario usuarioConectado = new Usuario(1, null, null, null, false);
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
}
