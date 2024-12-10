package excepciones;

/**
 * Excepción personalizada, si al momento de loguearse 
 * se introduce un usuario y/o contraseña incorrectas.
 */
public class InicioSesionException extends Exception{

	/**
     * Constructor predeterminado de la excepción.
     */
	public InicioSesionException(String msg) {
		super(msg);
	}
	
    /**
     * Constructor con un mensaje personalizado.
     *
     * @param mensaje Mensaje personalizado para la excepción.
     */
	public InicioSesionException() {
		super("Usuario/contraseña incorrecta. Por favor, vuelva a intentar.");
	}
}
