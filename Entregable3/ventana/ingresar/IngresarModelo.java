package ventana.ingresar;

import java.sql.SQLException;

import aplicacion.GestorDAOS;
import clases.Usuario;
import daos.UsuarioDAO;

public class IngresarModelo {

	public boolean iniciarSesion(Usuario usuario) {

		UsuarioDAO usuarioDAO = GestorDAOS.getUsuarioDAO();

		try {
			Usuario usuarioBase = usuarioDAO.buscarUsuario(usuario.getEmail());
			
			return ((usuarioBase != null) && (usuarioBase.getContraseña().equals(usuario.getContraseña())));
			
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			return false;
		}
	}
}
