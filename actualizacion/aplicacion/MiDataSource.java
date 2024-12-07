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

}
