package excepciones;

/**
 * Excepción personalizada, por si el usuario quiere
 * registrarse con un correo electrónico ya registrado.
 */
public class RegistrationException extends Exception{

	/**
     * Constructor predeterminado de la excepción.
     */
    public RegistrationException() {
        super("Ya existe un usuario con ese correo electrónico.");
    }

    /**
     * Constructor con un mensaje personalizado.
     *
     * @param mensaje Mensaje personalizado para la excepción.
     */
    public RegistrationException(String mensaje) {
        super(mensaje);
    }
}
