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
import excepciones.ExcepcionRara;
import excepciones.InicioSesionException;
import excepciones.RegistrationException;

public class FuncionesUsuario {
	
	public static void registrarse(Usuario usuario) throws RegistrationException, ExcepcionRara{

		PersonaDAO<Persona> personaDAO = FactoryDAO.getPersonaDAO();
		UsuarioDAO<Usuario> usuarioDAO = FactoryDAO.getUsuarioDAO();
		if(usuario.getEmail().isEmpty() || usuario.getContraseña().isEmpty() || usuario.getPersona().getApellidos().isEmpty() || usuario.getPersona().getNombres().isEmpty()) {
			throw new RegistrationException("Por favor, complete todos los campos.");
		}

		try {

			if (usuarioDAO.buscarUsuario(usuario) != null) // YA EXISTE CON EL MAIL
					throw new RegistrationException();
			usuario.getPersona().setIdPersona(personaDAO.insertarPersona(usuario.getPersona()));
			usuarioDAO.insertarUsuario(usuario);

		} catch (SQLException e) {
			throw new ExcepcionRara();		
		}
	}

	public static Usuario iniciarSesion(Usuario usuario) throws InicioSesionException, ExcepcionRara{		
		if(usuario==null) {
			throw new InicioSesionException("Hubo un error inesperado.");
		}
		
		UsuarioDAO<Usuario> usuarioDAO = FactoryDAO.getUsuarioDAO();
		try {
			Usuario usuarioEncontrado = usuarioDAO.buscarUsuario(usuario);

			if (usuarioEncontrado != null && usuario.getContraseña().equals(usuarioEncontrado.getContraseña())) {
				return usuarioEncontrado;
			}

			throw new InicioSesionException(); // Se ingresó usuario y/o contraseña incorrectas

		} catch (SQLException e) {
			throw new ExcepcionRara();
		}	
	}

	public static boolean aceptarTerminosYCondiciones(Usuario usuario) throws ExcepcionRara{
		
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
			throw new ExcepcionRara();
		}
	}
}
