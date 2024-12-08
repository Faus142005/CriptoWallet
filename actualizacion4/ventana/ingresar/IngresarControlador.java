package ventana.ingresar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Usuario;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesDeLaAplicacion;

public class IngresarControlador implements CriptoWalletControlador{

	private IngresarVista vista;
	private CriptoWalletVistaMain vistaMain;

	public IngresarControlador(IngresarVista vista, CriptoWalletVistaMain vistaMain) {
		this.vista = vista;
		this.vistaMain = vistaMain;
		
		vista.getBotonLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String email = vista.getCampoEmail().getText();
				String contraseña = new String(vista.getCampoContraseña().getPassword());
				
				Usuario usuario = FuncionesDeLaAplicacion.iniciarSesion(new Usuario(-1, null, email, contraseña, false));
				
				if(usuario != null) {
					vista.getCampoEmail().setText("");
					vista.getCampoContraseña().setText("");
					GestorDeDatosDeLaAplicacion.setUsuarioConectado(usuario);
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
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ingresoVentana() {
		vistaMain.cambiarTamaño(vista.getDimensiones());
	}
}
