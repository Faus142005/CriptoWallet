package clasesDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface MonedaDAO {

	public abstract void crearMoneda(Connection connection) throws SQLException;
	
	public abstract void listarMonedas(Connection connection) throws SQLException;

}
