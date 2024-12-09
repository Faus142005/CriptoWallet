package ventana.transacciones;

import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Transaccion;
import comparators.transacciones.ComparadorFechaReciente;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionesAplicacion.FuncionesDeLaAplicacion;

public class TransaccionesVista {

	private Dimension dimensiones = new Dimension(500, 500);
	private JPanel panel = new JPanel();

	private JPanel panelScrollPaneTransferencias = new JPanel();
	private JScrollPane scrollPaneTransferencias = new JScrollPane(panelScrollPaneTransferencias);

	private JButton botonAtras = new JButton("Atras");

	public TransaccionesVista() {

		this.panel.setLayout(null);

		this.scrollPaneTransferencias.setBounds(50, 50, 400, 400);
		this.panelScrollPaneTransferencias.setLayout(new BoxLayout(panelScrollPaneTransferencias, BoxLayout.Y_AXIS));
		this.panel.add(scrollPaneTransferencias);

		this.botonAtras.setBounds(15, 15, 100, 30);
		this.panel.add(botonAtras);

		this.actualizarTransferencias();

		this.panel.setName("Transacciones");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);
	}

	public void actualizarTransferencias() {

		panelScrollPaneTransferencias.removeAll();

		List<Transaccion> transacciones = FuncionesDeLaAplicacion
				.listarTransacciones(GestorDeDatosDeLaAplicacion.getUsuarioConectado());
		
		transacciones.sort(new ComparadorFechaReciente());

		for (Transaccion transaccion : transacciones) {

			String resumen = transaccion.getFecha().toString() + ":\n" + transaccion.getResumen();
			resumen = resumen.replace("\n", "<br>");

			JLabel etiquetaTransaccion = new JLabel();

			etiquetaTransaccion.setMaximumSize(new Dimension(390, Integer.MAX_VALUE));

			etiquetaTransaccion.setText("<html>" + resumen + "</html>");

			panelScrollPaneTransferencias.add(etiquetaTransaccion);
			panelScrollPaneTransferencias.add(Box.createVerticalStrut(20));
		}
	}

	/**
	 * @return the dimensiones
	 */
	public Dimension getDimensiones() {
		return dimensiones;
	}

	/**
	 * @param dimensiones the dimensiones to set
	 */
	public void setDimensiones(Dimension dimensiones) {
		this.dimensiones = dimensiones;
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * @return the panelScrollPaneTransferencias
	 */
	public JPanel getPanelScrollPaneTransferencias() {
		return panelScrollPaneTransferencias;
	}

	/**
	 * @param panelScrollPaneTransferencias the panelScrollPaneTransferencias to set
	 */
	public void setPanelScrollPaneTransferencias(JPanel panelScrollPaneTransferencias) {
		this.panelScrollPaneTransferencias = panelScrollPaneTransferencias;
	}

	/**
	 * @return the scrollPaneTransferencias
	 */
	public JScrollPane getScrollPaneTransferencias() {
		return scrollPaneTransferencias;
	}

	/**
	 * @param scrollPaneTransferencias the scrollPaneTransferencias to set
	 */
	public void setScrollPaneTransferencias(JScrollPane scrollPaneTransferencias) {
		this.scrollPaneTransferencias = scrollPaneTransferencias;
	}

	/**
	 * @return the botonAtras
	 */
	public JButton getBotonAtras() {
		return botonAtras;
	}

	/**
	 * @param botonAtras the botonAtras to set
	 */
	public void setBotonAtras(JButton botonAtras) {
		this.botonAtras = botonAtras;
	}

}
