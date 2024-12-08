package ventana.registrarse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases.Persona;
import clases.Usuario;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import ventana.CriptoWalletControlador;
import ventana.VistaMain;

public class RegistrarseControlador implements CriptoWalletControlador {

	private RegistrarseVista vista;

	public RegistrarseControlador(RegistrarseVista vista, VistaMain vistaMain) {

		this.vista = vista;

		this.vista.getCampoContraseña().getDocument().addDocumentListener(new comprobadorCamposContraseña());

		this.vista.getCampoContraseñaNuevamente().getDocument().addDocumentListener(new comprobadorCamposContraseña());

		this.vista.getBotonRegistrarse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel panel = new JPanel(new BorderLayout(5, 5));

				JLabel label = new JLabel(
						"Aceptas los terminos y condiciones? Puedes hacerlo mas tarde pero no se te permitira hacer ningun tipo de transaccion");
				panel.add(label, BorderLayout.NORTH);

				JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JCheckBox checkBox = new JCheckBox("Acepto totalmente sin importar lo que eso implique");
				checkboxPanel.add(checkBox);

				panel.add(checkboxPanel, BorderLayout.CENTER);

				Object[] options = { "Registrarse", "Cancelar" };
				int result = JOptionPane.showOptionDialog(null, panel, "Confirmacion", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (result != 0)
					return;

				String nombres = vista.getCampoNombre().getText();
				String apellidos = vista.getCampoApellidos().getText();
				Persona persona = new Persona(-1, nombres, apellidos);

				String email = vista.getCampoEmail().getText();
				String contraseña = new String(vista.getCampoContraseña().getPassword());
				Usuario usuario = new Usuario(-1, persona, email, contraseña, checkBox.isSelected());

				if(FuncionesDeLaAplicacion.registrarse(usuario)) {
					JOptionPane.showMessageDialog(null, "Te has logrado registrar exitosamente!", "Registracion exitosa", JOptionPane.INFORMATION_MESSAGE);
					vista.getCampoNombre().setText("");
					vista.getCampoApellidos().setText("");
					vista.getCampoEmail().setText("");
					vista.getCampoContraseña().setText("");
					vista.getCampoContraseñaNuevamente().setText("");
					vistaMain.panelAnterior();
				}
				else {
			        JOptionPane.showMessageDialog(null, "Algo paso... Investiga y resolvelo!", "Registracion no exitosa", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		this.vista.getBotonLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.panelAnterior();
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

	@Override
	public void actualizar(IdActualizacion id) {

	}
}
