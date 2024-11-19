package daos;

import java.sql.SQLException;

import clases.Persona;
import clases.Usuario;

public interface UsuarioDAO {

	public abstract int insertarUsuario(Usuario usuario) throws SQLException; 
	public abstract void actualizarUsuario(int idUsuario,Usuario usuario) throws SQLException;
	public abstract Usuario buscarUsuario(int idUsuario) throws SQLException; 
	public abstract void eliminarUsuario(int idUsuario)throws SQLException;
}
