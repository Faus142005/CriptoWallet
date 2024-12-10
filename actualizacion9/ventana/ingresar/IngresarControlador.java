package ventana.ingresar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Usuario;
import excepciones.ExcepcionRara;
import excepciones.InicioSesionException;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesUsuario;

public class IngresarControlador implements CriptoWalletControlador {

	private IngresarVista vista;
	private CriptoWalletVistaMain vistaMain;
	private boolean viendoContraseña = false;

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
					viendoContraseña = false;
					vista.getCampoContraseña().setEchoChar('•');
					vistaMain.cambiarPanel("Inicio");

				} catch (InicioSesionException | ExcepcionRara ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Inicio de sesión no exitoso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vista.getBotonRegistrarse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vista.getCampoEmail().setText("");
				vista.getCampoContraseña().setText("");
				viendoContraseña = false;
				vista.getCampoContraseña().setEchoChar('•');
				vistaMain.cambiarPanel("Registrarse");
			}
		});

		vista.getBotonVerContraseña().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (viendoContraseña)
					vista.getCampoContraseña().setEchoChar('•');
				else
					vista.getCampoContraseña().setEchoChar('\0');

				viendoContraseña = !viendoContraseña;
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
