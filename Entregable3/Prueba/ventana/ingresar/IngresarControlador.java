package ventana.ingresar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
