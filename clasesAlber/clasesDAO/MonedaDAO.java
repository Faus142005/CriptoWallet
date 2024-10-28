package clasesDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface MonedaDAO <Moneda>{

	//public abstract void insertarMoneda(Connection connection) throws SQLException;
	
	public abstract void insertarMoneda(Connection connection, String nombre, String nomenclatura, double precio, double volatilidad)
			throws SQLException;
	
	public abstract void insertarMoneda(Connection connection, String nombre, String nomenclatura, double precio)
			throws SQLException;
	
	public abstract void listarMonedas(Connection connection) throws SQLException;

	
}
