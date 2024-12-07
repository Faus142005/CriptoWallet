package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.Persona;
import clases.Transaccion;
import clases.Usuario;
import daos.TransaccionDAO;

public class TransaccionDAOJDBC implements TransaccionDAO<Transaccion>{

	private DataSource dataSource;

    // Constructor para inicializar el DataSource
    public TransaccionDAOJDBC() {
        this.dataSource = MiDataSource.getDataSource();
    }
    
    // Método privado para obtener la conexión con cuidado de las excepciones
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
	@Override
	public int insertarTransaccion(Transaccion t) throws SQLException {
		String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA, ID_USUARIO) VALUES (?, ?, ?)";

	    try (Connection connection = getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	       
	    	// Asigno valores al statement
	        pstmt.setString(1, t.getResumen());	    
	        pstmt.setDate(2, t.getFecha());	        	        	        	        
	        pstmt.setInt(3, t.getUsuario().getIdUsuario());
	        
	        pstmt.executeUpdate();
	        
	        // Obtener el id generado
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	t.setIdTransaccion(generatedKeys.getInt(1));
	                return t.getIdTransaccion();	                
	            } else 
	            	throw new SQLException("No se pudo obtener el ID generado para el usuario.");	            
	        }		      	        	             
	    }
	}

	@Override
	public int insertarTransaccionSoloResumen(Transaccion t) throws SQLException {
		String sql = "INSERT INTO TRANSACCION (RESUMEN, ID_USUARIO) VALUES (?, ?)";

	    try (Connection connection = getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	       
	    	// Asigno valores al statement
	        pstmt.setString(1, t.getResumen());	    	        	        	        	        	        
	        pstmt.setInt(2, t.getUsuario().getIdUsuario());
	        
	        pstmt.executeUpdate();
	        
	        // Obtener el id generado
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	t.setIdTransaccion(generatedKeys.getInt(1));
	                return t.getIdTransaccion();	                
	            } else 
	            	throw new SQLException("No se pudo obtener el ID generado para el usuario.");	            
	        }		      	        	             
	    }
	}

	@Override
	public List<Transaccion> listarTransacciones() throws SQLException {
		String sql = """				
		        SELECT 
		            t.ID AS ID_TRANSACCION, 					 
		            t.RESUMEN, 
		            t.FECHA_HORA, 					
		            u.ID AS ID_USUARIO,
		            u.EMAIL,
		            u.PASSWORD,
		            u.ACEPTA_TERMINOS,					
		            p.ID AS ID_PERSONA,
		            p.NOMBRES, 
		            p.APELLIDOS
		        FROM 
		            TRANSACCION t                
		        JOIN 
		            USUARIO u ON t.ID_USUARIO = u.ID       
		        JOIN 
		            PERSONA p ON u.ID_PERSONA = p.ID;
		    """;

		    List<Transaccion> transacciones = new ArrayList<>();

		    try (Connection connection = getConnection();
		         PreparedStatement pstmt = connection.prepareStatement(sql);
		         ResultSet rs = pstmt.executeQuery()) {

		        // Recorrer los resultados del ResultSet
		        while (rs.next()) {
		            // Crear un objeto Persona a partir de los datos de la base de datos
		            Persona persona = new Persona(
		                rs.getInt("ID_PERSONA"),
		                rs.getString("NOMBRES"),
		                rs.getString("APELLIDOS")
		            );

		            // Crear un objeto Usuario a partir de los datos de la base de datos
		            Usuario usuario = new Usuario(
		                rs.getInt("ID_USUARIO"),
		                persona,
		                rs.getString("EMAIL"),
		                rs.getString("PASSWORD"),
		                rs.getBoolean("ACEPTA_TERMINOS")
		            );

		            // Crear un objeto Transaccion a partir de los datos de la base de datos
		            Transaccion transaccion = new Transaccion(
		                rs.getInt("ID_TRANSACCION"),
		                rs.getString("RESUMEN"),
		                rs.getDate("FECHA_HORA"),
		                usuario
		            );

		            // Agregar la transacción a la lista
		            transacciones.add(transaccion);
		        }
		    }

		    return transacciones;
	}

	@Override  
	public void actualizarTransaccion(int idTransaccion, Transaccion t) throws SQLException {
		 String sql = "UPDATE TRANSACCION SET RESUMEN = ?, FECHA_HORA = ?, ID_USUARIO = ? WHERE ID = ?";
		 try (Connection connection = getConnection();
		         PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        			
			 	pstmt.setString(1, t.getResumen());	    
		        pstmt.setDate(2, t.getFecha());	        	        	        	        
		        pstmt.setInt(3, t.getUsuario().getIdUsuario());		        		    
		        pstmt.setInt(4, idTransaccion);

		        pstmt.executeUpdate();
		        
		        connection.close();		        
		 }
	}

	@Override
	public void eliminarTransaccion(int idTransaccion) throws SQLException {
		String sql = "DELETE FROM TRANSACCION WHERE ID = ?";
	    try (Connection connection = MiDataSource.getDataSource().getConnection(); 
	    		PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, idTransaccion);
	        pstmt.executeUpdate();
	    }
	}
	
	@Override
	public Transaccion buscarTransaccion(int idTransaccion) throws SQLException {
		String sql = """				
				SELECT 
					t.ID AS ID_TRANSACCION, 					 
					t.RESUMEN, 
					t.FECHA_HORA, 					
					u.ID AS ID_USUARIO,
					u.EMAIL,
					u.PASSWORD,
					u.ACEPTA_TERMINOS,					
					p.ID AS ID_PERSONA,
					p.NOMBRES, 
					p.APELLIDOS 	                
                FROM 
				    TRANSACCION t                
                JOIN 
				    USUARIO u ON t.ID_USUARIO = u.ID
				JOIN
				    PERSONA p ON u.ID_PERSONA = p.ID 
                WHERE t.ID = ?				
			"""; 
		
		try (Connection connection = getConnection();
		         PreparedStatement pstmt = connection.prepareStatement(sql)) {

		        pstmt.setInt(1, idTransaccion);

		        try (ResultSet rs = pstmt.executeQuery()) {
		            // Si se encontró un registro, creo y devuelvo el objeto transaccion
		            if (rs.next()) {
		                // Creo Persona
		                Persona persona = new Persona(
		                    rs.getInt("ID_PERSONA"),     // ID de la persona
		                    rs.getString("NOMBRES"),
		                    rs.getString("APELLIDOS")
		                );
		                
		                // Creo y devuelvo Usuario
		                Usuario usuario = new Usuario(
		                    rs.getInt("ID_USUARIO"),    // ID del usuario
		                    persona,            		// Objeto Persona asociado
		                    rs.getString("EMAIL"),
		                    rs.getString("PASSWORD"),
		                    rs.getBoolean("ACEPTA_TERMINOS")
		                );
		                
		                return new Transaccion(
		                	rs.getInt("ID"),    // ID de la transaccion			                
			                rs.getString("RESUMEN"),
			                rs.getDate("FECHA_HORA"),
			                usuario			                
		                );
		            }
		        }
		
		        return null;
		}
	}

}

