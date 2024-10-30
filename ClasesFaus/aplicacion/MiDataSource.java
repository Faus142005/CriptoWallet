package aplicacion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.sqlite.SQLiteDataSource;

public class MiDataSource {
			
    private static SQLiteDataSource dataSource;

    static {
        try {
            dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:src/CriptoWallet.db"); // Ruta a tu base de datos SQLite
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    private MiDataSource(){}

    public static void closeConnection() throws SQLException {
        if (dataSource != null) {
            try (Connection conn = dataSource.getConnection()) {
                // Cierra la conexión obtenida
                conn.close();
            }
        }
    }
	
	
	
	/*private static DataSource dataSource = null;
	static {
		try {
			//dataSource=(DataSource) new InitialContext().lookup("java:comp/env/interfacesDAO/CriptoWallet");
			//dataSource=(DataSource) new InitialContext().lookup("java:comp/env/src/CriptoWallet");
			dataSource=(DataSource) new InitialContext().lookup("jdbc:sqlite:src/CriptoWallet.db");
			
		} catch (NamingException e) {
			e.printStackTrace();		
		}
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	private MiDataSource(){}
	
	public static void closeConnection () throws SQLException {
		dataSource.getConnection().close();
	}*/

}
