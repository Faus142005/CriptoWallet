package aplicacion;

import daos.ActivoCriptoDAO;
import daos.ActivoFIATDAO;
import daos.MonedaDAO;
import daos.PersonaDAO;
import daos.TransaccionDAO;
import daos.UsuarioDAO;
import daosJDBC.PersonaDAOJDBC;
import daosJDBC.UsuarioDAOJDBC;

public class GestorDAOS {

	private static PersonaDAO personaDAO = new PersonaDAOJDBC();
	private static UsuarioDAO usuarioDAO = new UsuarioDAOJDBC();
	private static MonedaDAO monedaDAO;
	private static TransaccionDAO transaccionDAO;
	private static ActivoCriptoDAO activoCriptoDAO;
	private static ActivoFIATDAO activoFIATDAO;
	/**
	 * @return the personaDAO
	 */
	public static PersonaDAO getPersonaDAO() {
		return personaDAO;
	}
	/**
	 * @return the usuarioDAO
	 */
	public static UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	/**
	 * @return the monedaDAO
	 */
	public static MonedaDAO getMonedaDAO() {
		return monedaDAO;
	}
	/**
	 * @return the transaccionDAO
	 */
	public static TransaccionDAO getTransaccionDAO() {
		return transaccionDAO;
	}
	/**
	 * @return the activoCriptoDAO
	 */
	public static ActivoCriptoDAO getActivoCriptoDAO() {
		return activoCriptoDAO;
	}
	/**
	 * @return the activoFIATDAO
	 */
	public static ActivoFIATDAO getActivoFIATDAO() {
		return activoFIATDAO;
	}
	
	
}
