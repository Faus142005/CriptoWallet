package aplicacion;

import javax.sql.DataSource;
import org.sqlite.SQLiteDataSource;

public final class MiDataSource {
			
    private static SQLiteDataSource dataSource;

    static {
        try {
            dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:CriptoWallet.db"); // Ruta a tu base de datos SQLite
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    private MiDataSource(){}

    
    // Este método ahora no iría, porque cada conexión se abre y se cierra cuando se usa    
    /*public static void closeConnection() throws SQLException {
        if (dataSource != null) {
            try (Connection conn = dataSource.getConnection()) {
                // Cierra la conexión obtenida
                conn.close();
            }
        }
    }*/

}
