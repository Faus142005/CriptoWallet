package ventana.registrarse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.VistaMain;

public class RegistrarseControlador {

	private RegistrarseVista vista;

	public RegistrarseControlador(RegistrarseVista vista, VistaMain vistaMain) {

		this.vista = vista;

		this.vista.getBotonRegistrarse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
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
