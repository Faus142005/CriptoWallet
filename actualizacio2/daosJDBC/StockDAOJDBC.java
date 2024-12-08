package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.Moneda;
import clases.Stock;
import daos.StockDAO;

public class StockDAOJDBC implements StockDAO<Stock>{

	private DataSource dataSource;

    // Constructor para inicializar el DataSource
    public StockDAOJDBC() {
        this.dataSource = MiDataSource.getDataSource();
    }
    
    // Método privado para obtener la conexión con cuidado de las excepciones
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    //----------------------------------------------------------
    
    //Inserta Stock en la tabla
	@Override
	public int insertarStock(Stock s) throws SQLException {
		String sql = "INSERT INTO STOCK (ID_MONEDA, CANTIDAD) VALUES (?, ?)";		
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
						
			pstmt.setInt(1, s.getMoneda().getIdMoneda());
			pstmt.setDouble(2, s.getCantidad());
			
			pstmt.executeUpdate();
			//Obtener el id generado
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	        	if (generatedKeys.next()) {
	        		s.setIdStock(generatedKeys.getInt(1));
	        		return s.getIdStock();	                
	            } else 
	            	throw new SQLException("No se pudo obtener el ID generado para la moneda FIAT.");
	        }
		}
	}

	//Actualiza stock por ID
	@Override
	public void actualizarStock(Stock s) throws SQLException {
		String sql = "UPDATE STOCK SET ID_MONEDA = ?, CANTIDAD = ? WHERE ID = ?";
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, s.getMoneda().getIdMoneda());
			pstmt.setDouble(2, s.getCantidad());
			pstmt.setInt(3, s.getIdStock());
			
			pstmt.executeUpdate();
			connection.close();
		}
	}

	//Busca Stock por nomenclatura
	@Override
	public Stock buscarStock(String nomenclatura) throws SQLException {
		String sql = """				
				SELECT 
				s.ID,
				s.CANTIDAD, 
				m.ID AS ID_MONEDA, 
				m.TIPO,
				m.NOMBRE, 
				m.VALOR_DOLAR,				
				m.NOMBRE_ICONO,												
			FROM 
				STOCK s
			JOIN 
				MONEDA m ON s.ID_MONEDA = m.ID			
			WHERE 
				m.NOMENCLATURA = ? """;
		
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setString(1, nomenclatura);			
			ResultSet rs = pstmt.executeQuery();
			
			Stock stock = null;
			
			if (rs.next()) {
				// Extraer información de la moneda	 	
				int idMoneda = rs.getInt("ID_MONEDA");
				char tipo = rs.getString("TIPO").charAt(0);
	            String nombre = rs.getString("NOMBRE");	            
	            double valorDolar = rs.getDouble("VALOR_DOLAR");
	            String nombreIcono = rs.getString("NOMBRE_ICONO");	            
	            
	            // Crear objeto moneda
	            Moneda moneda = new Moneda(idMoneda, tipo, nombre, nomenclatura, valorDolar, nombreIcono);	            
	            	            
	            // Extraer cantidad y crear el objeto Stock
                int idStock = rs.getInt("ID");
	            double cantidad = rs.getDouble("CANTIDAD");
	            stock = new Stock(idStock, moneda, cantidad);
	            
			}
			connection.close();
			return stock;			
		}
				
	}
	
	//Busca Stock por su ID
	@Override
	public Stock buscarStockporID(int idStock) throws SQLException {
		String sql = """				
				SELECT 
				s.CANTIDAD, 
				m.ID AS ID_MONEDA, 
				m.TIPO,
				m.NOMBRE, 
				m.VALOR_DOLAR, 					
				m.NOMBRE_ICONO,												
			FROM 
				STOCK s
			JOIN 
				MONEDA m ON s.ID_MONEDA = m.ID			
			WHERE 
				s.ID = ? """;
		
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, idStock);			
			ResultSet rs = pstmt.executeQuery();
			
			Stock stock = null;
			
			if (rs.next()) {
				// Extraer información de la moneda	 	
				int idMoneda = rs.getInt("ID_MONEDA");
				char tipo = rs.getString("TIPO").charAt(0);
	            String nombre = rs.getString("NOMBRE");	 
	            String nomenclatura = rs.getString("NOMENCLATURA");	 
	            double valorDolar = rs.getDouble("VALOR_DOLAR");
	            String nombreIcono = rs.getString("NOMBRE_ICONO");	            
	            
	            // Crear objeto moneda
	            Moneda moneda = new Moneda(idMoneda, tipo, nombre, nomenclatura, valorDolar, nombreIcono);	            
	            	            
	            // Extraer cantidad y crear el objeto Stock                
	            double cantidad = rs.getDouble("CANTIDAD");
	            stock = new Stock(idStock, moneda, cantidad);
	            
			}
			connection.close();
			return stock;			
		}
		
	}

	//Eliminar un stock pasándole como parámetro la nomenclatura de una moneda
	@Override
	public void eliminarStock(String nomenclatura) throws SQLException {
		// Obtengo el ID de la moneda con la nomenclatura del parámetro
	    String sql1 = "SELECT ID FROM MONEDA WHERE NOMENCLATURA = ?";
	    String sql2 = "DELETE FROM STOCK WHERE ID_MONEDA = ?";
	    
	    try (Connection connection = getConnection();
	         PreparedStatement pstmt1 = connection.prepareStatement(sql1);
	         PreparedStatement pstmt2 = connection.prepareStatement(sql2)) {
	        
	        // Obtener el ID de la moneda
	    	pstmt1.setString(1, nomenclatura);
	    	
	        try (ResultSet rs = pstmt1.executeQuery()) {
	            if (rs.next()) {
	                int idMoneda = rs.getInt("ID");	    
	                
	                // elimino el stock asociado al ID de la moneda
	                pstmt2.setInt(1, idMoneda);
	                pstmt2.executeUpdate();
	                
	            } else {
	                throw new SQLException("No se encontró ninguna moneda con la nomenclatura: " + nomenclatura);
	            }
	        }
	    }				
	}
	
	//Elimina un stock por su ID
	@Override
	public void eliminarStockporID(int idStock) throws SQLException {
		String sql = "DELETE FROM STOCK WHERE ID = ?";
	    try (Connection connection = getConnection(); 
	    		PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, idStock);
	        pstmt.executeUpdate();
	    }
	}

	//Lista los stocks disponibles
	@Override
	public List<Stock> listarStock() throws SQLException {
		List<Stock> stocks = new ArrayList<>();
	     		
		String sql = """				
				SELECT 
				s.ID,
				s.CANTIDAD, 
				m.ID AS ID_MONEDA, 
				m.TIPO,
				m.NOMBRE, 
				m.VALOR_DOLAR, 					
				m.NOMBRE_ICONO,												
			FROM 
				STOCK s
			JOIN 
				MONEDA m ON s.ID_MONEDA = m.ID """;
		
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			ResultSet rs = pstmt.executeQuery();
			
			Stock stock = null;
			
			while (rs.next()) {
				// Extraer información de la moneda	 
				int idMoneda = rs.getInt("ID_MONEDA");
				char tipo = rs.getString("TIPO").charAt(0);
	            String nombre = rs.getString("NOMBRE");
	            String nomenclatura = rs.getString("NOMENCLATURA");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");	
	            String nombreIcono = rs.getString("NOMBRE_ICONO");	            
	            
	            // Crear objeto moneda
	            Moneda moneda = new Moneda(idMoneda, tipo, nombre, nomenclatura, valorDolar, nombreIcono);	            
	            	            
	            // Extraer cantidad y crear el objeto Stock
                int idStock = rs.getInt("ID");
	            double cantidad = rs.getDouble("CANTIDAD");
	            stock = new Stock(idStock, moneda, cantidad);	            
									
	            stocks.add(stock);
	            
			} 
		} catch (SQLException e) {
	        System.out.println("Error al listar las monedas: " + e.getMessage());
	        throw e;
	    }
		
	    // Retornar la lista de stocks
	    return stocks;
	}	

}
