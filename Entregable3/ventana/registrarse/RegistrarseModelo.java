package ventana.registrarse;

import java.sql.SQLException;

import aplicacion.GestorDAOS;
import clases.Persona;
import clases.Usuario;
import daos.PersonaDAO;
import daos.UsuarioDAO;

public class RegistrarseModelo {

	public boolean registrarse(Persona persona, Usuario usuario) {

		PersonaDAO personaDAO = GestorDAOS.getPersonaDAO();
		UsuarioDAO usuarioDAO = GestorDAOS.getUsuarioDAO();

		try {
			int id = personaDAO.insertarPersona(persona);
			usuario.setIdPersona(id);
			usuarioDAO.insertarUsuario(usuario);
			return true;
			
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
			return false;
		}
	}
}
