package clasesDAO;

import java.sql.Connection;

public interface StockDAO <Stock>{

	public abstract void insertarStock(Connection connection, String nomenclatura, double cantidad);
	
	public abstract void generarStock(Connection connection);
	
	public abstract void listarStock(Connection connection);
}
