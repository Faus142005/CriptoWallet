package clasesDAO;
import clases.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class StockDAOjdbc implements StockDAO<Stock>{
	
    private static final double MAX_CANTIDAD = 1000.0; // cantidad máxima de criptos a generar
    
    

	@Override
	public void insertarStock(Connection connection, String nomenclatura, double cantidad) {
				
		String sql = "INSERT INTO STOCK (cantidadActual, nomenclatura) VALUES ('" + cantidad + "', " + nomenclatura + ")";
		
		try(Statement stmt=connection.createStatement()){			
			stmt.executeUpdate(sql);
			System.out.println("Stock de "+nomenclatura+" insertado exitosamente en la base de datos.");				
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }	
	}
		

    public void generarStock(Connection connection) {                      
    	
    	String sql = "SELECT nomenclatura FROM Criptomonedas";
        String sqlUpdate = "UPDATE Stocks SET cantidad = ? WHERE nomenclatura = ?";
        
        try (PreparedStatement selectStmt = connection.prepareStatement(sql);
        PreparedStatement updateStmt = connection.prepareStatement(sqlUpdate);
        
        	ResultSet resul = selectStmt.executeQuery()) {

            	Random random = new Random();
            	while (resul.next()) {
            		String nomenclatura = resul.getString("nomenclatura");
            		double stockAleatorio = 0.01 + (MAX_CANTIDAD - 0.01) * random.nextDouble(); // genera un valor aleatorio entre 0.01 y 1000.0                   

            		updateStmt.setDouble(1, stockAleatorio);
            		updateStmt.setString(2, nomenclatura);
            		updateStmt.executeUpdate();
            	}

                System.out.println("Stock generado aleatoriamente para todas las criptomonedas.");
                
             } catch (SQLException e) {
            	 System.out.println(e.getMessage());
             }
	}
        

	
	

	@Override
	public void listarStock(Connection connection) {
		// TODO Auto-generated method stub
		
	}

}
