package clases;

/**
 * Activo liquido hereda lo de activo
 * 
 * Se le agrega el codigo de tenencia
 * para saber donde estan las monedas en la blockchain
 * @author Fausto Y Albertina
 */

public class ActivoLiquido extends Activo{

	private String codigo;

	/**
	 * @return El codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo El codigo para setear
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
