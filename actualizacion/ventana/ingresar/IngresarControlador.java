package ventana.ingresar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.VistaMain;

public class IngresarControlador {

	private IngresarVista vista;

	public IngresarControlador(IngresarVista vista, VistaMain vistaMain) {
		this.vista = vista;
		
		vista.getBotonLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
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
