package clasesDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface MonedaFiduciariaDAO {
	public abstract void insertarMonedaFiduciaria(Connection connection) throws SQLException;

}
