package ventana.registrarse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.CriptoWalletControlador;
import ventana.VistaMain;

public class RegistrarseControlador implements CriptoWalletControlador{

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
	
	@Override
	public void actualizar(IdActualizacion id) {
		
	}
}
