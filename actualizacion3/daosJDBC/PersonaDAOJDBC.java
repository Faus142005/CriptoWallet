package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.Persona;
import daos.PersonaDAO;

public class PersonaDAOJDBC implements PersonaDAO<Persona>{

	private DataSource dataSource;

    // Constructor para inicializar el DataSource
    public PersonaDAOJDBC() {
        this.dataSource = MiDataSource.getDataSource();
    }
    
    // Método privado para obtener la conexión con cuidado de las excepciones
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
	@Override
	public int insertarPersona(Persona persona) throws SQLException {		
		String sql = "INSERT INTO PERSONA (NOMBRES, APELLIDOS) VALUES (?, ?)";
	    try (Connection connection = getConnection();
	    		PreparedStatement pstmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
	        
	    	pstmt.setString(1, persona.getNombres());
	        pstmt.setString(2, persona.getApellidos());
	        pstmt.executeUpdate();
	        
	     // Obtener el id generado
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	persona.setIdPersona(generatedKeys.getInt(1)); // Asigna a persona el id generado
	                return persona.getIdPersona(); 	// Si la operación de insertar persona sale bien, entonces retorna el id
	            } else 
	                throw new SQLException("No se pudo obtener el ID generado para la persona.");	            
	        }	        
	    }
	    
	}

	//Acá el id tmb se lo podríamos asignar a la persona, y no pasarlo como un parámetro aparte
	@Override
	public void actualizarPersona(int id, Persona persona) throws SQLException {
		 String sql = "UPDATE PERSONA SET NOMBRES = ?, APELLIDOS = ? WHERE ID = ?";
		    try (Connection connection = getConnection();
		         PreparedStatement pstmt = connection.prepareStatement(sql)) {

		        pstmt.setString(1, persona.getNombres());
		        pstmt.setString(2, persona.getApellidos());
		        pstmt.setInt(3, id);
		        pstmt.executeUpdate();
				connection.close();
						        
		    }
	}

	@Override
	public Persona buscarPersona(int idPersona) throws SQLException {
		String sql = "SELECT * FROM PERSONA WHERE ID = ?"; 

	    try (Connection connection = getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        pstmt.setInt(1, idPersona);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            // Si se encontró un registro creo persona con los atributos
	            if (rs.next()) {	            	
	                return new Persona(
	                    rs.getInt("ID"),           // ID de la persona
	                    rs.getString("NOMBRES"),   // Nombre de la persona
	                    rs.getString("APELLIDOS")  // Apellido de la persona
	                );
	            }
	        }
	    }
	    // Si no se encuentra la persona, devuelve null
	    return null;
	}

	@Override
	public void eliminarPersona(int ID) throws SQLException {
		String sql = "DELETE FROM PERSONA WHERE ID = ?"; 

		try (Connection connection = getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(sql)) {
		     pstmt.setInt(1, ID);
		     pstmt.executeUpdate();
			 connection.close();		        
		}
		catch (SQLException e) {
	        System.out.println("Error al eliminar la persona: " + e.getMessage());
	        throw e;
	    }
	}

    
}