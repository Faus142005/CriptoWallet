package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import aplicacion.MyDataSource;
import clases.Persona;
import daos.PersonaDAO;

public class PersonaDAOJDBC implements PersonaDAO{
	
	public int insertarPersona(Persona persona) throws SQLException {
	    String sql = "INSERT INTO PERSONA (NOMBRES, APELLIDOS) VALUES (?, ?)";
	    try (Connection connection = MyDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        pstmt.setString(1, persona.getNombres());
	        pstmt.setString(2, persona.getApellidos());
	        pstmt.executeUpdate();

	        // Recuperar la clave generada
	        try (ResultSet rs = pstmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getInt(1); // Devuelve el ID generado
	            }
	        }
	    }
	    return -1; // Retorna -1 si no se pudo obtener el ID
	}
	
	public void actualizarPersona(int id, Persona persona) throws SQLException {
        String sql = "UPDATE PERSONA SET NOMBRES = ?, APELLIDOS = ? WHERE ID = ?";
        try (Connection connection = MyDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
        	pstmt.setString(1, persona.getNombres());
        	pstmt.setString(2, persona.getApellidos());
        	pstmt.setInt(3, id);
        	pstmt.executeUpdate();
        }
    }
	
	public Persona buscarPersona(int ID) throws SQLException {
        String sql = "SELECT * FROM PERSONA WHERE ID = ?";
        try (Connection connection = MyDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
        	pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Persona(
                    rs.getString("NOMBRES"),
                    rs.getString("APELLIDOS")
                );
            }
        }
        return null; // Retorna null si no se encuentra
    }
	
	public void eliminarPersona(int ID) throws SQLException {
        String sql = "DELETE FROM PERSONA WHERE ID = ?";
        try (Connection connection = MyDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
        	pstmt.setInt(1, ID);
        	pstmt.executeUpdate();
        }
    }
}