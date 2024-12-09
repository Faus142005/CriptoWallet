package daos;

import java.sql.SQLException;

public interface UsuarioDAO<Usuario> {

	public abstract int insertarUsuario(Usuario usuario) throws SQLException; 
	
	public abstract void actualizarUsuarioPorId(Usuario usuario) throws SQLException; 
	
	public abstract void actualizarUsuario(Usuario usuario) throws SQLException;
	
	public abstract Usuario buscarUsuario(int idUsuario) throws SQLException; // Por id
	
	public abstract Usuario buscarUsuario(Usuario usuario) throws SQLException; // Por email
	
	public abstract void eliminarUsuario(int idUsuario)throws SQLException;

}
