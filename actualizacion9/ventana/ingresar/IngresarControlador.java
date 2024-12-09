package ventana.ingresar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Usuario;
import excepciones.InicioSesionException;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import funcionesAplicacion.FuncionesUsuario;

public class IngresarControlador implements CriptoWalletControlador {

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

				try {
					Usuario usuario = FuncionesUsuario.iniciarSesion(new Usuario(-1, null, email, contraseña, false));

					vista.getCampoEmail().setText("");
					vista.getCampoContraseña().setText("");
					GestorDeDatosDeLaAplicacion.setUsuarioConectado(usuario);
					vistaMain.cambiarPanel("Inicio");

				} catch (InicioSesionException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),
							"Inicio de sesión no exitoso", JOptionPane.INFORMATION_MESSAGE);
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
