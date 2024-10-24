package clasesDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CriptomonedaDAO {
	
	public abstract void crearCriptomoneda(Connection connection) throws SQLException;		

}
