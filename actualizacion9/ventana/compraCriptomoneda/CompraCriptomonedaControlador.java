package ventana.compraCriptomoneda;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Transaccion;
import clases.UnidadDeCompra;
import excepciones.FondosInsuficientesException;
import excepciones.StockInsuficienteException;
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

				Criptomoneda c = (Criptomoneda) vista.getSelectorCriptomonedas().getSelectedItem();
				FIAT f = (FIAT) vista.getSelectorFIAT().getSelectedItem();
				GestorDeDatosDeLaAplicacion.setCriptomonedaSeleccionada(c);
				GestorDeDatosDeLaAplicacion.setFiatSeleccionada(f);
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

				FIAT f = (FIAT) vista.getSelectorFIAT().getSelectedItem();

				double cantidadFIAT = Double.valueOf(vista.getCampoCantidadFIAT().getText());

				HashMap<String, ActivoFIAT> activosFiat = FuncionesDeLaAplicacion
						.mapearActivosFIAT(GestorDeDatosDeLaAplicacion.getUsuarioConectado());

				ActivoFIAT af = activosFiat.get(f.getNomenclatura());

				if (af != null) {
					if (af.getCantidad() < cantidadFIAT) {
						vista.getCampoCantidadFIAT().setText(Double.toString(af.getCantidad()));
					}
				} else {
					vista.getCampoCantidadFIAT().setText("0");
				}

				actualizarCampoCripto();
			}

			@Override
			public void focusGained(FocusEvent e) {
				campoFIATFocus = true;
			}
		});

		this.vista.getBotonComprar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!GestorDeDatosDeLaAplicacion.getUsuarioConectado().isTyc())
					if (!FuncionesUsuario
							.aceptarTerminosYCondiciones(GestorDeDatosDeLaAplicacion.getUsuarioConectado()))
						return;

				Criptomoneda criptomonedaSeleccionada = (Criptomoneda) vista.getSelectorCriptomonedas()
						.getSelectedItem();
				double cantidadCriptomonedas = Double.valueOf(vista.getCampoCantidadCriptomonedas().getText());

				FIAT fiatSeleccionada = (FIAT) vista.getSelectorFIAT().getSelectedItem();
				double cantidadFIAT = Double.valueOf(vista.getCampoCantidadFIAT().getText());

				UnidadDeCompra unidad = new UnidadDeCompra(GestorDeDatosDeLaAplicacion.getUsuarioConectado(),
						criptomonedaSeleccionada, cantidadCriptomonedas, fiatSeleccionada, cantidadFIAT);

				JPanel panel = new JPanel(new BorderLayout(5, 5));

				JLabel label = new JLabel("Estas seguro de comprar " + unidad.getCantidadCriptomoneda()
						+ unidad.getCriptomoneda().getNomenclatura() + " a " + unidad.getCriptomoneda().getValorDolar()
						+ "?");
				panel.add(label, BorderLayout.NORTH);

				Object[] options = { "Si", "No" };
				int result = JOptionPane.showOptionDialog(null, panel, "Confirmacion", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (result != 0)
					return;

				vista.getCampoCantidadCriptomonedas().setText("0");
				vista.getCampoCantidadFIAT().setText("0");
				try {
					Transaccion transaccion = FuncionesDeLaAplicacion.comprarCriptomoneda(unidad);
					JOptionPane.showMessageDialog(null, "Compra increiblemente exitosa.\nPodra ver mas informacion en la ventana de transacciones", "Compra exitosa",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (StockInsuficienteException | FondosInsuficientesException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error en la compra",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	private void actualizarCampoCripto() {
		String campo = vista.getCampoCantidadFIAT().getText().toString();
		campoFIATFocus = false;

		if (campo == null || campo.isEmpty() || !(Double.valueOf(campo) > 0)) {
			this.vista.getBotonComprar().setEnabled(false);
			this.vista.getCampoCantidadCriptomonedas().setText("0");
			return;
		}

		else
			this.vista.getBotonComprar().setEnabled(true);

		double cantidadFIAT = Double.valueOf(campo);

		vista.getCampoCantidadCriptomonedas()
				.setText(String.valueOf(cantidadFIAT * FuncionesDeLaAplicacion.calcularEquivalenciaEnUSD(
						(Moneda) vista.getSelectorFIAT().getSelectedItem(),
						(Moneda) vista.getSelectorCriptomonedas().getSelectedItem())));
	}

	private void actualizarCampoFIAT() {
		String campo = vista.getCampoCantidadCriptomonedas().getText().toString();
		campoCriptomonedaFocus = false;

		if (campo == null || campo.isEmpty() || !(Double.valueOf(campo) > 0)) {
			this.vista.getBotonComprar().setEnabled(false);
			this.vista.getCampoCantidadFIAT().setText("0");
			return;

		} else
			this.vista.getBotonComprar().setEnabled(true);

		Criptomoneda c = (Criptomoneda) vista.getSelectorCriptomonedas().getSelectedItem();
		FIAT f = (FIAT) vista.getSelectorFIAT().getSelectedItem();

		double cantidadCripto = Double.valueOf(campo);

		double cantidadFIAT = cantidadCripto * FuncionesDeLaAplicacion.calcularEquivalenciaEnUSD(c, f);

		HashMap<String, ActivoFIAT> activosFiat = FuncionesDeLaAplicacion
				.mapearActivosFIAT(GestorDeDatosDeLaAplicacion.getUsuarioConectado());

		ActivoFIAT af = activosFiat.get(f.getNomenclatura());

		if (af != null) {
			if (af.getCantidad() < cantidadFIAT) {
				cantidadFIAT = af.getCantidad();
				vista.getCampoCantidadFIAT().setText(Double.toString(cantidadFIAT));

				cantidadCripto = cantidadFIAT * FuncionesDeLaAplicacion.calcularEquivalenciaEnUSD(f, c);

				vista.getCampoCantidadCriptomonedas().setText(Double.toString(cantidadCripto));
			} else {
				vista.getCampoCantidadFIAT().setText(Double.toString(cantidadFIAT));
			}
		}

		else {
			vista.getCampoCantidadFIAT().setText("0");
			vista.getCampoCantidadCriptomonedas().setText("0");
		}
	}

	@Override
	public void actualizar() {
		Criptomoneda c = (Criptomoneda) vista.getSelectorCriptomonedas().getSelectedItem();
		FIAT f = (FIAT) vista.getSelectorFIAT().getSelectedItem();
		GestorDeDatosDeLaAplicacion.setCriptomonedaSeleccionada(c);
		GestorDeDatosDeLaAplicacion.setFiatSeleccionada(f);
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
