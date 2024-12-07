package daos;

import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Persona;
import clases.Stock;
import clases.Transaccion;
import clases.Usuario;
import daosJDBC.ActivoCriptoDAOJDBC;
import daosJDBC.ActivoFIATDAOJDBC;

public class FactoryDAO {
	private static PersonaDAO<Persona> personaDAO;// = new PersonaDAOJDBC();
	private static UsuarioDAO<Usuario> usuarioDAO;// = new UsuarioDAOJDBC();
	private static FIATDAO<FIAT> fiatDAO; // = new FIATDAOJDBC();
	private static CriptomonedaDAO<Criptomoneda> criptomonedaDAO; // = new CriptomonedaDAOJDBC();
	private static TransaccionDAO<Transaccion> transaccionDAO;// = new TransaccionDAOJDBC();
	private static ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = new ActivoCriptoDAOJDBC();
	private static ActivoFIATDAO<ActivoFIAT> activoFIATDAO = new ActivoFIATDAOJDBC();
	private static StockDAO<Stock> stockDAO;// = new ActivoFIATDAOJDBC();
	
	/**
	 * @return the personaDAO
	 */
	public static PersonaDAO<Persona> getPersonaDAO() {
		return personaDAO;
	}
	/**
	 * @return the usuarioDAO
	 */
	public static UsuarioDAO<Usuario> getUsuarioDAO() {
		return usuarioDAO;
	}
	/**
	 * @return the fiatDAO
	 */
	public static FIATDAO<FIAT> getFiatDAO() {
		return fiatDAO;
	}
	/**
	 * @return the criptomonedaDAO
	 */
	public static CriptomonedaDAO<Criptomoneda> getCriptomonedaDAO() {
		return criptomonedaDAO;
	}
	/**
	 * @return the transaccionDAO
	 */
	public static TransaccionDAO<Transaccion> getTransaccionDAO() {
		return transaccionDAO;
	}
	/**
	 * @return the activoCriptoDAO
	 */
	public static ActivoCriptoDAO<ActivoCripto> getActivoCriptoDAO() {
		return activoCriptoDAO;
	}
	/**
	 * @return the activoFIATDAO
	 */
	public static ActivoFIATDAO<ActivoFIAT> getActivoFIATDAO() {
		return activoFIATDAO;
	}
	/**
	 * @return the stockDAO
	 */
	public static StockDAO<Stock> getStockDAO() {
		return stockDAO;
	}
	
}
