package interfacesDAO;

import javax.sql.DataSource;

public interface StockDAO <Stock>{

	public abstract void insertarStock(DataSource connection, String nomenclatura, double cantidad);
	
}
