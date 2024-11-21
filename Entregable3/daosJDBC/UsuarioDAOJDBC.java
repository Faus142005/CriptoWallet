package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import aplicacion.MyDataSource;
import clases.Usuario;
import daos.UsuarioDAO;

public class UsuarioDAOJDBC implements UsuarioDAO {

	public int insertarUsuario(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS) VALUES (?, ?, ?, ?)";
		try (Connection connection = MyDataSource.getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, usuario.getIdPersona());
			pstmt.setString(2, usuario.getEmail());
			pstmt.setString(3, usuario.getContraseña());
			pstmt.setBoolean(4, usuario.isTyc());
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

	public void actualizarUsuario(int idUsuario, Usuario usuario) throws SQLException {
		String sql = "UPDATE USUARIO SET EMAIL = ?, PASSWORD = ?, ACEPTA_TERMINOS = ? WHERE ID = ?";
		try (Connection connection = MyDataSource.getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, usuario.getEmail());
			pstmt.setString(2, usuario.getContraseña());
			pstmt.setBoolean(3, usuario.isTyc());
			pstmt.setInt(4, idUsuario);
			pstmt.executeUpdate();
		}
	}

	public Usuario buscarUsuario(int idUsuario) throws SQLException {
		String sql = "SELECT * FROM USUARIO WHERE ID = ?";
		Usuario usuario = null;
		try (Connection connection = MyDataSource.getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, idUsuario);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					// int id = rs.getInt("ID");
					int idPersona = rs.getInt("ID_PERSONA");
					String email = rs.getString("EMAIL");
					String password = rs.getString("PASSWORD");
					boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
					usuario = new Usuario(idPersona, email, password, aceptaTerminos);
				}
			}
		}
		return usuario;
	}

	public void eliminarUsuario(int idUsuario) throws SQLException {
		String sql = "DELETE FROM USUARIO WHERE ID = ?";
		try (Connection connection = MyDataSource.getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, idUsuario);
			pstmt.executeUpdate();
		}
	}

	public Usuario buscarUsuario(String email) throws SQLException {
		String sql = "SELECT ID, ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS FROM USUARIO WHERE EMAIL = ?";
		Usuario usuario = null;

		try (Connection connection = MyDataSource.getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, email);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int idPersona = rs.getInt("ID_PERSONA");
					String password = rs.getString("PASSWORD");
					boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
					usuario = new Usuario(idPersona, email, password, aceptaTerminos);
				}
			}
		}

		return usuario;
	}
}
