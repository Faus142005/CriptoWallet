package ventana.ingresar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clases.Usuario;
import ventana.VistaMain;

public class IngresarControlador {

	private IngresarModelo modelo;
	private IngresarVista vista;

	public IngresarControlador(IngresarVista vista, IngresarModelo modelo, VistaMain vistaMain) {
		this.vista = vista;
		this.modelo = modelo;
		
		vista.getBotonLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String email = vista.getCampoEmail().getText();
				String contraseña = String.valueOf(vista.getCampoContraseña().getPassword());
				Usuario usr = new Usuario(-1, email, contraseña, false);
				
				boolean inicioSesion = modelo.iniciarSesion(usr);
			}
		});
		
		vista.getBotonRegistrarse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaMain.cambiarPanel("Registrarse");
			}
		});
	}
}
