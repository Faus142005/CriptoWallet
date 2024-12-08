package ventana.compraCriptomoneda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Transaccion;
import clases.UnidadDeCompra;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import ventana.VistaMain;

public class CompraCriptomonedaControlador implements CriptoWalletControlador {

	private CompraCriptomonedaVista vista;
	private CriptoWalletVistaMain vistaMain;

	public CompraCriptomonedaControlador(CompraCriptomonedaVista vista, CriptoWalletVistaMain vistaMain) {
		this.vista = vista;
		this.vistaMain = vistaMain;

		this.vista.getBotonAtras().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.panelAnterior();
			}
		});

		this.vista.getBotonComprar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel panel = new JPanel(new BorderLayout(5, 5));

				JLabel label = new JLabel(
						"Antes tienes que aceptar los terminos y condiciones");
				panel.add(label, BorderLayout.NORTH);

				Object[] options = { "Aceptar terminos y condiciones y comprar", "Cancelar" };
				int result = JOptionPane.showOptionDialog(null, panel, "Confirmacion", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (result != 0)
					return;
				
				
				Criptomoneda criptomonedaSeleccionada = (Criptomoneda) vista.getSelectorCriptomonedas()
						.getSelectedItem();
				double cantidadCriptomonedas = Double.valueOf(vista.getCampoCantidadCriptomonedas().getText());

				FIAT fiatSeleccionada = (FIAT) vista.getSelectorFIAT().getSelectedItem();
				double cantidadFIAT = Double.valueOf(vista.getCampoCantidadFIAT().getText());
				
				UnidadDeCompra unidad = new UnidadDeCompra(GestorDeDatosDeLaAplicacion.getUsuarioConectado(),
						criptomonedaSeleccionada, cantidadCriptomonedas, fiatSeleccionada, cantidadFIAT);

				try {
					Transaccion transaccion = FuncionesDeLaAplicacion.comprarCriptomoneda(unidad);
				} catch (SQLException error) {
					System.err.println("Error: " + error.getMessage());
				}
			}
		});
	}

	@Override
	public void actualizar() {
		vista.cambiarDatosCriptomonedas();
		vista.cambiarDatosFIAT();
	}

	@Override
	public void ingresoVentana() {
		vistaMain.cambiarTamaño(vista.getDimensiones());
		vista.nuevasCriptomonedas();
		vista.nuevasFIAT();
	}
}
