package funcionesAplicacion;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

			if (usuarioDAO.buscarUsuario(usuario) != null) // YA EXISTE UN USUARIO CON EL MAIL
					throw new RegistrationException();
			usuario.getPersona().setIdPersona(personaDAO.insertarPersona(usuario.getPersona()));
			usuarioDAO.insertarUsuario(usuario);

		} catch (RegistrationException r) {
			System.err.println(r.getMessage());			
		} catch (SQLException e) {
			System.err.println(e.getMessage());	
		}
	}

	public static Usuario iniciarSesion(Usuario usuario) throws InicioSesionException{

		UsuarioDAO<Usuario> usuarioDAO = FactoryDAO.getUsuarioDAO();
		try {
			Usuario usuarioEncontrado = usuarioDAO.buscarUsuario(usuario);

			if (usuarioEncontrado != null && usuario.getContraseña().equals(usuarioEncontrado.getContraseña())) {
				return usuarioEncontrado;
			}

			throw new InicioSesionException(); // Se ingresó usuario y/o contraseña incorrectas

		} catch (InicioSesionException i) {
			System.err.println("Error: "+i.getMessage());
		} catch (SQLException e) {
			System.err.println(e.getMessage());			
		}finally {
			return null;
		}
		
	}

	public static boolean aceptarTerminosYCondiciones(Usuario usuario) {
		
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JLabel label = new JLabel(
				"Antes tienes que aceptar los términos y condiciones");
		panel.add(label, BorderLayout.NORTH);

		Object[] options = { "Aceptar términos y condiciones y comprar", "Cancelar" };
		int result = JOptionPane.showOptionDialog(null, panel, "Confirmación", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (result != 0)
			return false;
		
		try {
			
			UsuarioDAO<Usuario> usuarioDAO = FactoryDAO.getUsuarioDAO();
			
			usuario.setTyc(true);
			
			usuarioDAO.actualizarUsuarioPorId(usuario);
			
			return true;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error! Intente nuevamente", "Información del Usuario", JOptionPane.INFORMATION_MESSAGE);
			System.err.println(e.getMessage());
			return false;
		}
	}
}
