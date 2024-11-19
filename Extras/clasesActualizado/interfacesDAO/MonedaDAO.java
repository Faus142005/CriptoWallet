package interfacesDAO;

import java.sql.SQLException;

import javax.sql.DataSource;

public interface MonedaDAO <Moneda>{
	
	public abstract void insertarMoneda(DataSource connection, String nombre, String nomenclatura, double precio, double volatilidad)
			throws SQLException;
	
	public abstract void insertarMoneda(DataSource connection, String nombre, String nomenclatura, double precio)
			throws SQLException;
	
}
