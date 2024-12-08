package ventana.ingresar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clases.Usuario;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import ventana.CriptoWalletControlador;
import ventana.VistaMain;

public class IngresarControlador implements CriptoWalletControlador{

	private IngresarVista vista;

	public IngresarControlador(IngresarVista vista, VistaMain vistaMain) {
		this.vista = vista;
		
		vista.getBotonLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String email = vista.getCampoEmail().getText();
				String contraseña = new String(vista.getCampoContraseña().getPassword());
				
				Usuario usuario = FuncionesDeLaAplicacion.iniciarSesion(new Usuario(-1, null, email, contraseña, false));
				
				if(usuario != null) {
					vista.getCampoEmail().setText("");
					vista.getCampoContraseña().setText("");
					vistaMain.cambiarPanel("Inicio");
				}
			}
		});
		
		vista.getBotonRegistrarse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaMain.cambiarPanel("Registrarse");
			}
		});
	}
	
	@Override
	public void actualizar(IdActualizacion id) {
		
	}
}
