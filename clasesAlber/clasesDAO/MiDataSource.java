package clasesDAO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MiDataSource {
	
	private static DataSource dataSource = null;
	static {
		try {
		dataSource=(DataSource) new InitialContext().lookup("java:comp/env/jdbc/CriptoWallet");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	private MiDataSource(){}

}
