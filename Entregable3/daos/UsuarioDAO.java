package daos;

import java.sql.SQLException;

public interface UsuarioDAO<Usuario> {

	public abstract int insertarUsuario(Usuario usuario) throws SQLException; 
	public abstract void actualizarUsuario(int idUsuario,Usuario usuario) throws SQLException;
	public abstract Usuario buscarUsuario(int idUsuario) throws SQLException; 
	public abstract void eliminarUsuario(int idUsuario)throws SQLException;
}
