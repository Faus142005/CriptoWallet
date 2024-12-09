package ventana.compraCriptomoneda;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Transaccion;
import clases.UnidadDeCompra;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import funcionesAplicacion.FuncionesUsuario;

public class CompraCriptomonedaControlador implements CriptoWalletControlador {

	private CompraCriptomonedaVista vista;
	private CriptoWalletVistaMain vistaMain;
	private boolean campoCriptomonedaFocus = false;
	private boolean campoFIATFocus = false;

	public CompraCriptomonedaControlador(CompraCriptomonedaVista vista, CriptoWalletVistaMain vistaMain) {
		this.vista = vista;
		this.vistaMain = vistaMain;

		this.vista.getPanel().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (!vista.getCampoCantidadCriptomonedas().getBounds().contains(e.getPoint())
						&& campoCriptomonedaFocus) {
					actualizarCampoFIAT();
				}

				if (!vista.getCampoCantidadFIAT().getBounds().contains(e.getPoint()) && campoFIATFocus) {
					actualizarCampoCripto();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		this.vista.getBotonAtras().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.panelAnterior();
			}
		});

		this.vista.getSelectorCriptomonedas().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Criptomoneda c = (Criptomoneda) vista.getSelectorCriptomonedas().getSelectedItem();
				if (c == null)
					return;
				vista.getEtiquetaPrecioCriptoADolar().setText(c.getNomenclatura() + " | " + c.getValorDolar() + "USD");
				actualizarCampoCripto();
			}
		});

		this.vista.getCampoCantidadCriptomonedas().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				actualizarCampoFIAT();
			}

			@Override
			public void focusGained(FocusEvent e) {
				campoCriptomonedaFocus = true;
			}
		});

		this.vista.getSelectorFIAT().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FIAT f = (FIAT) vista.getSelectorFIAT().getSelectedItem();
				if (f == null)
					return;
				vista.getEtiquetaPrecioCriptoAFIAT().setText(f.getNomenclatura() + " | " + f.getValorDolar() + "USD");
				actualizarCampoFIAT();
			}
		});

		this.vista.getCampoCantidadFIAT().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				actualizarCampoCripto();
				campoFIATFocus = false;
			}

			@Override
			public void focusGained(FocusEvent e) {
				campoFIATFocus = true;
			}
		});

		this.vista.getBotonComprar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!GestorDeDatosDeLaAplicacion.getUsuarioConectado().isTyc())
					if(!FuncionesUsuario.aceptarTerminosYCondiciones(GestorDeDatosDeLaAplicacion.getUsuarioConectado()))
						return;
				
				Criptomoneda criptomonedaSeleccionada = (Criptomoneda) vista.getSelectorCriptomonedas()
						.getSelectedItem();
				double cantidadCriptomonedas = Double.valueOf(vista.getCampoCantidadCriptomonedas().getText());

				FIAT fiatSeleccionada = (FIAT) vista.getSelectorFIAT().getSelectedItem();
				double cantidadFIAT = Double.valueOf(vista.getCampoCantidadFIAT().getText());

				UnidadDeCompra unidad = new UnidadDeCompra(GestorDeDatosDeLaAplicacion.getUsuarioConectado(),
						criptomonedaSeleccionada, cantidadCriptomonedas, fiatSeleccionada, cantidadFIAT);
				
				JPanel panel = new JPanel(new BorderLayout(5, 5));

				JLabel label = new JLabel(
						"Estas seguro de comprar " + unidad.getCantidadCriptomoneda() + unidad.getCriptomoneda().getNomenclatura() + " a " + unidad.getCriptomoneda().getValorDolar() + "?");
				panel.add(label, BorderLayout.NORTH);

				Object[] options = { "Si", "No" };
				int result = JOptionPane.showOptionDialog(null, panel, "Confirmacion", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (result != 0)
					return;
				
				vista.getCampoCantidadCriptomonedas().setText("0");
				vista.getCampoCantidadFIAT().setText("0");
				Transaccion transaccion = FuncionesDeLaAplicacion.comprarCriptomoneda(unidad);
			}
		});
	}

	private void actualizarCampoCripto() {

		String campo = vista.getCampoCantidadFIAT().getText().toString();
		campoCriptomonedaFocus = false;
		if (campo == null || campo.isEmpty())
			campo = "0";
		double cantidadFIAT = Double.valueOf(campo);

		vista.getCampoCantidadCriptomonedas()
				.setText(String.valueOf(cantidadFIAT * FuncionesDeLaAplicacion.calcularEquivalenciaEnUSD(
						(Moneda) vista.getSelectorFIAT().getSelectedItem(),
						(Moneda) vista.getSelectorCriptomonedas().getSelectedItem())));
	}

	private void actualizarCampoFIAT() {
		String campo = vista.getCampoCantidadCriptomonedas().getText().toString();
		campoFIATFocus = false;
		if (campo == null || campo.isEmpty())
			campo = "0";
		double cantidadCripto = Double.valueOf(campo);
		vista.getCampoCantidadFIAT()
				.setText(Double.toString(cantidadCripto * FuncionesDeLaAplicacion.calcularEquivalenciaEnUSD(
						(Moneda) vista.getSelectorCriptomonedas().getSelectedItem(),
						(Moneda) vista.getSelectorFIAT().getSelectedItem())));
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
