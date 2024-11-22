package ventana.registrarse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.VistaMain;

public class RegistrarseControlador {

	private RegistrarseVista vista;
	private RegistrarseModelo modelo;

	public RegistrarseControlador(RegistrarseVista vista, RegistrarseModelo modelo, VistaMain vistaMain) {

		this.vista = vista;
		this.modelo = modelo;

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
