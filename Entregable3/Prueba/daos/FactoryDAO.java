package daos;

public class FactoryDAO {
	private static PersonaDAO personaDAO;// = new PersonaDAOJDBC();
	private static UsuarioDAO usuarioDAO;// = new UsuarioDAOJDBC();
	private static MonedaDAO monedaDAO;// = new MonedaDAOJDBC();	
	private static TransaccionDAO transaccionDAO;// = new TransaccionDAOJDBC();
	private static ActivoCriptoDAO activoCriptoDAO;// = new ActivoCriptoDAOJDBC();
	private static ActivoFIATDAO activoFIATDAO;// = new ActivoFIATDAOJDBC();
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
