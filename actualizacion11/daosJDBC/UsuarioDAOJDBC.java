package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.Persona;
import clases.Usuario;
import daos.UsuarioDAO;

public class UsuarioDAOJDBC implements UsuarioDAO<Usuario>{

	private DataSource dataSource;

    // Constructor para inicializar el DataSource
    public UsuarioDAOJDBC() {
        this.dataSource = MiDataSource.getDataSource();
    }
    
    // Método privado para obtener la conexión con cuidado de las excepciones
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
	@Override
	public int insertarUsuario(Usuario usuario) throws SQLException {		
		String sql = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS) VALUES (?, ?, ?, ?)";

	    try (Connection connection = getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	        // Asigno valores al statement
	        pstmt.setInt(1, usuario.getPersona().getIdPersona());
	        pstmt.setString(2, usuario.getEmail());
	        pstmt.setString(3, usuario.getContraseña());
	        pstmt.setBoolean(4, usuario.isTyc());

	        pstmt.executeUpdate();
	        
	        // Obtener el id generado
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	usuario.setIdUsuario(generatedKeys.getInt(1)); // Asigna a persona el id generado
	                return usuario.getIdUsuario(); 	// Si la operación de insertar persona sale bien, entonces retorna el id
	            } else 
	                throw new SQLException("No se pudo obtener el ID generado para el usuario.");	            
	        }	        
	    }
	}
	
	@Override
	public void actualizarUsuarioPorId(Usuario usuario) throws SQLException {
	    String sql = "UPDATE USUARIO SET ID_PERSONA = ?, EMAIL = ?, PASSWORD = ?, ACEPTA_TERMINOS = ? WHERE ID = ?";
	    try (Connection connection = getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        pstmt.setInt(1, usuario.getPersona().getIdPersona());
	        pstmt.setString(2, usuario.getEmail());
	        pstmt.setString(3, usuario.getContraseña());
	        pstmt.setBoolean(4, usuario.isTyc());
	        pstmt.setInt(5, usuario.getIdUsuario());

	        pstmt.executeUpdate();
	    }
	}


	@Override
	public void actualizarUsuario(Usuario usuario) throws SQLException {
	    String sql = "UPDATE USUARIO SET ID_PERSONA = ?, EMAIL = ?, PASSWORD = ?, ACEPTA_TERMINOS = ? WHERE ID = ?";
	    try (Connection connection = getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        pstmt.setInt(1, usuario.getPersona().getIdPersona());
	        pstmt.setString(2, usuario.getEmail());
	        pstmt.setString(3, usuario.getContraseña());
	        pstmt.setBoolean(4, usuario.isTyc());

	        pstmt.executeUpdate();
	    }
	}

	@Override
	public Usuario buscarUsuario(int idUsuario) throws SQLException {
		String sql = """				
				SELECT 
					u.ID AS ID_USUARIO, 					 
					u.EMAIL, 
					u.PASSWORD, 
					u.ACEPTA_TERMINOS, 
					p.ID AS ID_PERSONA,
					p.NOMBRES, 
					p.APELLIDOS 	                
                FROM 
				    USUARIO u                
                JOIN 
				    PERSONA p ON u.ID_PERSONA = p.ID 
                WHERE u.ID = ?
				
			"""; 

	    try (Connection connection = getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        pstmt.setInt(1, idUsuario);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            // Si se encontró un registro, creo y devuelvo el objeto Usuario
	            if (rs.next()) {
	                // Creo Persona
	                Persona persona = new Persona(
	                    rs.getInt("ID_PERSONA"),
	                    rs.getString("NOMBRES"),
	                    rs.getString("APELLIDOS")
	                );

	                // Creo y devuelvo Usuario
	                return new Usuario(
	                    rs.getInt("ID_USUARIO"),    // ID del usuario
	                    persona,            // Objeto Persona asociado
	                    rs.getString("EMAIL"),
	                    rs.getString("PASSWORD"),
	                    rs.getBoolean("ACEPTA_TERMINOS")
	                );
	            }
	        }
	        // Si no se encuentra usuario, devuelve null
	        return null;
	    }
	}

	@Override
	public void eliminarUsuario(int idUsuario) throws SQLException {
		String sql = "DELETE FROM USUARIO WHERE ID = ?";
	    try (Connection connection = MiDataSource.getDataSource().getConnection(); 
	    		PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, idUsuario);
	        pstmt.executeUpdate();
	    }
	    catch (SQLException e) {
	        System.err.println("Error al eliminar el usuario: " + e.getMessage());
	        throw e;
	    }
	}

	@Override
	public Usuario buscarUsuario(Usuario usuario) throws SQLException {
		String sql = """				
				SELECT 
					u.ID AS ID_USUARIO, 					 
					u.EMAIL, 
					u.PASSWORD, 
					u.ACEPTA_TERMINOS, 
					p.ID AS ID_PERSONA,
					p.NOMBRES, 
					p.APELLIDOS 	                
                FROM 
				    USUARIO u                
                JOIN 
				    PERSONA p ON u.ID_PERSONA = p.ID 
                WHERE u.EMAIL = ?
				
			"""; 

	    try (Connection connection = getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        pstmt.setString(1, usuario.getEmail());

	        try (ResultSet rs = pstmt.executeQuery()) {
	            // Si se encontró un registro, creo y devuelvo el objeto Usuario
	            if (rs.next()) {
	                // Creo Persona
	                Persona persona = new Persona(
	                    rs.getInt("ID_PERSONA"),
	                    rs.getString("NOMBRES"),
	                    rs.getString("APELLIDOS")
	                );

	                // Creo y devuelvo Usuario
	                return new Usuario(
	                    rs.getInt("ID_USUARIO"),    // ID del usuario
	                    persona,            // Objeto Persona asociado
	                    rs.getString("EMAIL"),
	                    rs.getString("PASSWORD"),
	                    rs.getBoolean("ACEPTA_TERMINOS")
	                );
	            }
	        }
	        // Si no se encuentra usuario, devuelve null
	        return null;
	    }
	}
}

