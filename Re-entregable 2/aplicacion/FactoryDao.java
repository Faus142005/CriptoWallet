package aplicacion;

import clases.Activo;
import clases.Moneda;
import clases.Stock;
import clasesDAOjdbc.Activo_CriptoDAOJDBC;
import clasesDAOjdbc.Activo_FIATDAOJDBC;
import clasesDAOjdbc.MonedaDAOJDBC;
import clasesDAOjdbc.StockDAOJDBC;
import clasesDAOjdbc.TransaccionDAOJDBC;
import interfacesDAO.Activo_CriptoDAO;
import interfacesDAO.Activo_FIATDAO;
import interfacesDAO.MonedaDAO;
import interfacesDAO.StockDAO;
import interfacesDAO.TransaccionDAO;

public final class FactoryDao {

	private static Activo_CriptoDAO<Activo> activoCriptoDAO;
	private static Activo_FIATDAO<Activo> activoFIATDAO;
	private static MonedaDAO<Moneda> monedaDAO;
	private static StockDAO<Stock> stockDAO;
	private static TransaccionDAO transaccionDAO;
	
	static {
		
		activoCriptoDAO = new Activo_CriptoDAOJDBC();
		activoFIATDAO = new Activo_FIATDAOJDBC();
		monedaDAO = new MonedaDAOJDBC();
		stockDAO = new StockDAOJDBC();
		transaccionDAO = new TransaccionDAOJDBC();
	}
		
	public static Activo_CriptoDAO<Activo> getActivoCriptoDAO() {
		
		return activoCriptoDAO;
	}
	
	public static Activo_FIATDAO<Activo> getActivoFIATDAO() {
		return activoFIATDAO;
	}
	
	public static MonedaDAO<Moneda> getMonedaDAO() {
		return monedaDAO;
	}
	
	public static StockDAO<Stock> getStockDAO() {
		return stockDAO;
	}
	
	public static TransaccionDAO getTransaccionDAO() {
		return transaccionDAO;
	}
}
