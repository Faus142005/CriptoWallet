package ventana.registrarse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases.Persona;
import clases.Usuario;
import excepciones.ExcepcionRara;
import excepciones.RegistrationException;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesUsuario;

public class RegistrarseControlador implements CriptoWalletControlador {

	private RegistrarseVista vista;
	private CriptoWalletVistaMain vistaMain;
	private boolean viendoContraseña1 = false;
	private boolean viendoContraseña2 = false;

	public RegistrarseControlador(RegistrarseVista vista, CriptoWalletVistaMain vistaMain) {

		this.vista = vista;
		this.vistaMain = vistaMain;

		this.vista.getCampoEmail().getDocument().addDocumentListener(new comprobadorCampoEmail());

		this.vista.getCampoContraseña().getDocument().addDocumentListener(new comprobadorCamposContraseña());

		this.vista.getCampoContraseñaNuevamente().getDocument().addDocumentListener(new comprobadorCamposContraseña());

		this.vista.getBotonRegistrarse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel panel = new JPanel(new BorderLayout(5, 5));

				JLabel label = new JLabel(
						"Aceptas los términos y condiciones? Puedes hacerlo más tarde pero no se te permitirá hacer ningún tipo de transacción");
				panel.add(label, BorderLayout.NORTH);

				JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JCheckBox checkBox = new JCheckBox("He leído y acepto los términos y condiciones.");
				checkboxPanel.add(checkBox);

				panel.add(checkboxPanel, BorderLayout.CENTER);

				Object[] options = { "Registrarse", "Cancelar" };
				int result = JOptionPane.showOptionDialog(null, panel, "Confirmación", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (result != 0)
					return;

				String nombres = vista.getCampoNombre().getText();
				String apellidos = vista.getCampoApellidos().getText();
				Persona persona = new Persona(-1, nombres, apellidos);

				String email = vista.getCampoEmail().getText();
				String contraseña = new String(vista.getCampoContraseña().getPassword());
				Usuario usuario = new Usuario(-1, persona, email, contraseña, checkBox.isSelected());

				try {
					FuncionesUsuario.registrarse(usuario);

					JOptionPane.showMessageDialog(null, "Te has logrado registrar exitosamente!",
							"Registración exitosa", JOptionPane.INFORMATION_MESSAGE);
					vista.getCampoNombre().setText("");
					vista.getCampoApellidos().setText("");
					vista.getCampoEmail().setText("");
					vista.getCampoContraseña().setText("");
					vista.getCampoContraseñaNuevamente().setText("");
					viendoContraseña1 = false;
					vista.getCampoContraseña().setEchoChar('•');
					viendoContraseña2 = false;
					vista.getCampoContraseñaNuevamente().setEchoChar('•');
					vistaMain.panelAnterior();
				} catch (RegistrationException | ExcepcionRara ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Registración no exitosa",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		this.vista.getBotonLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vista.getCampoNombre().setText("");
				vista.getCampoApellidos().setText("");
				vista.getCampoEmail().setText("");
				vista.getCampoContraseña().setText("");
				vista.getCampoContraseñaNuevamente().setText("");
				viendoContraseña1 = false;
				vista.getCampoContraseña().setEchoChar('•');
				viendoContraseña2 = false;
				vista.getCampoContraseñaNuevamente().setEchoChar('•');
				vistaMain.panelAnterior();
			}
		});

		this.vista.getBotonVerContraseña1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (viendoContraseña1)
					vista.getCampoContraseña().setEchoChar('•');
				else
					vista.getCampoContraseña().setEchoChar('\0');

				viendoContraseña1 = !viendoContraseña1;
			}
		});

		this.vista.getBotonVerContraseña2().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (viendoContraseña2)
					vista.getCampoContraseñaNuevamente().setEchoChar('•');
				else
					vista.getCampoContraseñaNuevamente().setEchoChar('\0');

				viendoContraseña2 = !viendoContraseña2;
			}
		});
	}

	public class comprobadorCamposContraseña implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			this.comprobarContraseñas();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			this.comprobarContraseñas();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			this.comprobarContraseñas();
		}

		private void comprobarContraseñas() {
			char[] password1 = vista.getCampoContraseña().getPassword();
			char[] password2 = vista.getCampoContraseñaNuevamente().getPassword();
			boolean bandera = java.util.Arrays.equals(password1, password2);
			vista.getBotonRegistrarse().setEnabled(bandera);
			vista.getEtiquetaContraseñasDiferentes().setVisible(!bandera);
		}
	}

	public class comprobadorCampoEmail implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			this.comprobarEmail();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			this.comprobarEmail();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			this.comprobarEmail();
		}

		private void comprobarEmail() {
			String mail = vista.getCampoEmail().getText();

			// Validar que el correo no sea nulo ni vacío
			boolean emailValido = (mail != null && !mail.trim().isEmpty());

			// Validar formato del correo electrónico
			if (emailValido) {
				emailValido = mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
			}

			vista.getBotonRegistrarse().setEnabled(emailValido);
			vista.getEtiquetaDebeIngresarEmail().setVisible(!emailValido);

		}
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
