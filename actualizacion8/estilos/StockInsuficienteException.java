package estilos;

/**
 * Excepción personalizada, por si no hay suficiente
 * stock (de alguna moneda en particular) en el sistema 
 * para realizar alguna transacción.
 */
public class StockInsuficienteException extends Exception{

	private String nombreMoneda; 
    
    /**
     * Constructor predeterminado de la excepción.
     * 
     * @param nombreMoneda Nombre de la moneda asociada a la excepción.
     */
    public StockInsuficienteException(String nombreMoneda) {
        super("Stock insuficiente en el sistema de " + nombreMoneda+".");
        this.nombreMoneda = nombreMoneda;
    }

    /**
     * Constructor con un mensaje personalizado.
     *
     * @param nombreMoneda Nombre de la moneda asociada a la excepción.
     * @param mensaje Mensaje personalizado para la excepción.
     */
    public StockInsuficienteException(String nombreMoneda, String mensaje) {
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