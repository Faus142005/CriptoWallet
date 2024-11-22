package daos;

import daosJDBC.PersonaDAOJDBC;
import daosJDBC.UsuarioDAOJDBC;
import daosJDBC.MonedaDAOJDBC;
import daosJDBC.TransaccionDAOJDBC;
import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Moneda;
import clases.Persona;
import clases.Transaccion;
import clases.Usuario;
import daosJDBC.ActivoCriptoDAOJDBC;
import daosJDBC.ActivoFIATDAOJDBC;


public class FactoryDAO {
	private static PersonaDAO<Persona> personaDAO = new PersonaDAOJDBC();
	private static UsuarioDAO<Usuario> usuarioDAO = new UsuarioDAOJDBC();
	private static MonedaDAO<Moneda> monedaDAO = new MonedaDAOJDBC();	
	private static TransaccionDAO<Transaccion> transaccionDAO = new TransaccionDAOJDBC();
	private static ActivoCriptoDAO<ActivoCripto> activoCriptoDAO = new ActivoCriptoDAOJDBC();
	private static ActivoFIATDAO<ActivoFIAT> activoFIATDAO = new ActivoFIATDAOJDBC();
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
	 * @return the monedaDAO
	 */
	public static MonedaDAO<Moneda> getMonedaDAO() {
		return monedaDAO;
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
	
}
