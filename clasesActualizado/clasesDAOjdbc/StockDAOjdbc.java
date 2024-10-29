package clasesDAOjdbc;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import clases.Stock;
import interfacesDAO.StockDAO;


public class StockDAOjdbc implements StockDAO<Stock>{	    
        
	@Override
	public void insertarStock(DataSource connection, String nomenclatura, double cantidad) {
				
		String sql = "INSERT INTO STOCK (cantidadActual, nomenclatura) VALUES ('" + cantidad + "', " + nomenclatura + ")";
		
		try(Statement stmt=connection.getConnection().createStatement()){			
			stmt.executeUpdate(sql);
			System.out.println("Stock de "+nomenclatura+" insertado exitosamente en la base de datos.");				
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }	
	}		   

}
