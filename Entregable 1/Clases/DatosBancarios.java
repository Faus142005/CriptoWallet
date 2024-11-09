package clases;

/**
 * DatosBancarios contiene informacion bancaria
 * del usuario, el CBU y el banco el cual esta asociado
 * @author Fausto Y Albertina
 */

public class DatosBancarios {

	private long cbu;
	private String banco;
	
	/**
	 * @return El cbu
	 */
	public long getCbu() {
		return cbu;
	}
	/**
	 * @param cbu El cbu para setear
	 */
	public void setCbu(long cbu) {
		this.cbu = cbu;
	}
	/**
	 * @return El banco
	 */
	public String getBanco() {
		return banco;
	}
	/**
	 * @param banco El banco para setear
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}		
}