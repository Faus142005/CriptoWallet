package excepciones;

/**
 * Excepción personalizada, por si el usuario no posee
 * activos (de una moneda en particular) suficientes para 
 * realizar alguna transacción.
 */
public class FondosInsuficientesException extends Exception{

	private String nombreMoneda;
	
	/**
     * Constructor predeterminado de la excepción.
     * 
     * @param nombreMoneda Nombre de la moneda asociada a la excepción.
     */
    public FondosInsuficientesException(String nombreMoneda) {
        super("Fondos insuficientes de " + nombreMoneda+".");
        this.nombreMoneda = nombreMoneda;
    }

    /**
     * Constructor con un mensaje personalizado.
     *
     * @param nombreMoneda Nombre de la moneda asociada a la excepción.
     * @param mensaje Mensaje personalizado para la excepción.
     */
    public FondosInsuficientesException(String nombreMoneda, String mensaje) {
        super(mensaje);
        this.nombreMoneda = nombreMoneda;
    }

	/**
	 * @return el nombre de la moneda asociada al error
	 */
	public String getNombreMoneda() {
		return nombreMoneda;
	}

}