package ventana.registrarse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import clases.Persona;
import clases.Usuario;
import ventana.VistaMain;

public class RegistrarseControlador{

	private RegistrarseVista vista;
	private RegistrarseModelo modelo;

	public RegistrarseControlador(RegistrarseVista vista, RegistrarseModelo modelo, VistaMain vistaMain) {

		this.vista = vista;
		this.modelo = modelo;

		this.vista.getBotonRegistrarse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nombres = vista.getCampoNombres().getText();
				String apellidos = vista.getCampoApellidos().getText();
				String email = vista.getCampoEmail().getText();
				String contraseña = String.valueOf(vista.getCampoContraseña().getPassword());
				
				Persona persona = new Persona(nombres, apellidos);
				Usuario usuario = new Usuario(-1, email, contraseña, false);
				
				//QUE HACER CON EL MAIL?
				//NO LO PONGO COMO UNICO EN LA TABLA??
				//LO TENGO QUE CONTROLAR POR MI CUENTA?
				
				boolean registro = modelo.registrarse(persona, usuario);
				
				if(registro) {
					JOptionPane.showMessageDialog(null, "Registro completado exitosamente!", 
                            "Registracion", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Registro no completado exitosamente!", 
                            "Registracion", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		this.vista.getBotonLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				vistaMain.cambiarPanel("Ingresar");
			}
		});
	}
}
