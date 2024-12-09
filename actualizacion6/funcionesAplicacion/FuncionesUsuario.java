package funcionesAplicacion;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Persona;
import clases.Usuario;
import daos.FactoryDAO;
import daos.PersonaDAO;
import daos.UsuarioDAO;
import excepciones.InicioSesionException;
import excepciones.RegistrationException;

public class FuncionesUsuario {
	
	public static void registrarse(Usuario usuario) throws RegistrationException{

		PersonaDAO<Persona> personaDAO = FactoryDAO.getPersonaDAO();
		UsuarioDAO<Usuario> usuarioDAO = FactoryDAO.getUsuarioDAO();

		try {

			if (usuarioDAO.buscarUsuario(usuario) != null) // YA EXISTE CON EL MAIL
					throw new RegistrationException("Ya existe un usuario con ese mail");
			usuario.getPersona().setIdPersona(personaDAO.insertarPersona(usuario.getPersona()));
			usuarioDAO.insertarUsuario(usuario);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RegistrationException("Hubo un error inesperado");
		}
	}

	public static Usuario iniciarSesion(Usuario usuario) throws InicioSesionException{

		UsuarioDAO<Usuario> usuarioDAO = FactoryDAO.getUsuarioDAO();
		try {
			Usuario usuarioEncontrado = usuarioDAO.buscarUsuario(usuario);

			if (usuarioEncontrado != null && usuario.getContraseña().equals(usuarioEncontrado.getContraseña())) {
				return usuarioEncontrado;
			}

			throw new InicioSesionException("Usuario/Contraseña incorrectas");

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new InicioSesionException("Hubo un error inesperado");
		}
	}

	public static boolean aceptarTerminosYCondiciones(Usuario usuario) {
		
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JLabel label = new JLabel(
				"Antes tienes que aceptar los terminos y condiciones");
		panel.add(label, BorderLayout.NORTH);

		Object[] options = { "Aceptar terminos y condiciones y comprar", "Cancelar" };
		int result = JOptionPane.showOptionDialog(null, panel, "Confirmacion", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (result != 0)
			return false;
		
		try {
			
			UsuarioDAO<Usuario> usuarioDAO = FactoryDAO.getUsuarioDAO();
			
			usuario.setTyc(true);
			
			usuarioDAO.actualizarUsuarioPorId(usuario);
			
			return true;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error!! Intente nuevamente", "Información del Usuario", JOptionPane.INFORMATION_MESSAGE);
			System.err.println(e.getMessage());
			return false;
		}
	}
}
