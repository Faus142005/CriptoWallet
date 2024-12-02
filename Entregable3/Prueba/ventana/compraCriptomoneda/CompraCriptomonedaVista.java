package ventana.compraCriptomoneda;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aplicacion.CalculosGenerales;
import aplicacion.FuncionesDeLaAplicacion;
import clases.Criptomoneda;
import clases.FIAT;

public class CompraCriptomonedaVista {

	private Dimension dimensiones = new Dimension(600, 400);
	private JPanel panel = new JPanel();

	private JButton botonAtras = new JButton("Atras");

	private JLabel etiquetaCriptomoneda = new JLabel("Seleccione la criptomoneda a comprar");
	private JComboBox<Criptomoneda> selectorCriptomonedas = new JComboBox<Criptomoneda>();
	private JTextField campoCantidadCriptomonedas = new JTextField();
	private JLabel etiquetaPrecioCriptoADolar = new JLabel("BTC = 90USD");

	private JLabel etiquetaFIAT = new JLabel("Seleccione la moneda FIAT a usar");
	private JComboBox<FIAT> selectorFIAT = new JComboBox<FIAT>();
	private JTextField campoCantidadFIAT = new JTextField();
	private JLabel etiquetaPrecioCriptoAFIAT = new JLabel("BTC = 90000ARG");

	private JButton botonComprar = new JButton("Comprar");

	private HashMap<String, ImageIcon> imagenesEscaladas = new HashMap<>();

	public CompraCriptomonedaVista() {

		this.panel.setLayout(null);

		this.botonAtras.setBounds(10, 10, 100, 30);

		this.etiquetaCriptomoneda.setBounds(165, 30, 270, 30);
		this.selectorCriptomonedas.setBounds(165, 60, 270, 30);

		selectorCriptomonedas.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);

				if (value instanceof Criptomoneda) {
					Criptomoneda c = (Criptomoneda) value;
					label.setIcon(imagenesEscaladas.get(c.getNomenclatura()));
					label.setText(c.getNomenclatura() + " | " + c.getValorDolar());
				} else {
					label.setText("Seleccione una criptomoneda");
				}

				return label;
			}
		});

		this.campoCantidadCriptomonedas.setBounds(165, 100, 270, 30);
		this.etiquetaPrecioCriptoADolar.setBounds(200, 140, 200, 30);

		this.etiquetaFIAT.setBounds(180, 200, 240, 30);
		this.selectorFIAT.setBounds(165, 230, 270, 30);
		this.campoCantidadFIAT.setBounds(165, 270, 270, 30);
		this.etiquetaPrecioCriptoAFIAT.setBounds(200, 310, 200, 30);

		this.botonComprar.setBounds(490, 325, 100, 30);

		this.panel.add(botonAtras);

		this.panel.add(etiquetaCriptomoneda);
		this.panel.add(selectorCriptomonedas);
		this.panel.add(campoCantidadCriptomonedas);
		this.panel.add(etiquetaPrecioCriptoADolar);

		this.panel.add(etiquetaFIAT);
		this.panel.add(selectorFIAT);
		this.panel.add(campoCantidadFIAT);
		this.panel.add(etiquetaPrecioCriptoAFIAT);

		this.panel.add(botonComprar);

		this.panel.setName("Compra de Criptomoneda");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);

		this.actualizarCriptomonedas();

	}

	public void actualizarCriptomonedas() {

		selectorCriptomonedas.removeAllItems();

		imagenesEscaladas.clear();

		List<Criptomoneda> criptomonedas = FuncionesDeLaAplicacion.listarCriptomonedas();

		HashMap<String, ImageIcon> auxImagenes = FuncionesDeLaAplicacion.obtenerIconsDeCriptomonedas();

		for (Criptomoneda c : criptomonedas) {
			selectorCriptomonedas.addItem(c);
			imagenesEscaladas.put(c.getNomenclatura(),
					CalculosGenerales.escalarImagenAlto(auxImagenes.get(c.getNomenclatura()), 20));
		}
	}

	public void cambiarPreciosCriptomonedas() {
		selectorCriptomonedas.removeAllItems();

		List<Criptomoneda> criptomonedas = FuncionesDeLaAplicacion.listarCriptomonedas();

		for (Criptomoneda c : criptomonedas)
			selectorCriptomonedas.addItem(c);

	}

	public void reordenarFIAT() {

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

	/**
	 * @return the etiquetaCriptomoneda
	 */
	public JLabel getEtiquetaCriptomoneda() {
		return etiquetaCriptomoneda;
	}

	/**
	 * @param etiquetaCriptomoneda the etiquetaCriptomoneda to set
	 */
	public void setEtiquetaCriptomoneda(JLabel etiquetaCriptomoneda) {
		this.etiquetaCriptomoneda = etiquetaCriptomoneda;
	}

	/**
	 * @return the selectorCriptomonedas
	 */
	public JComboBox<Criptomoneda> getSelectorCriptomonedas() {
		return selectorCriptomonedas;
	}

	/**
	 * @param selectorCriptomonedas the selectorCriptomonedas to set
	 */
	public void setSelectorCriptomonedas(JComboBox<Criptomoneda> selectorCriptomonedas) {
		this.selectorCriptomonedas = selectorCriptomonedas;
	}

	/**
	 * @return the campoCantidadCriptomonedas
	 */
	public JTextField getCampoCantidadCriptomonedas() {
		return campoCantidadCriptomonedas;
	}

	/**
	 * @param campoCantidadCriptomonedas the campoCantidadCriptomonedas to set
	 */
	public void setCampoCantidadCriptomonedas(JTextField campoCantidadCriptomonedas) {
		this.campoCantidadCriptomonedas = campoCantidadCriptomonedas;
	}

	/**
	 * @return the etiquetaPrecioCriptoADolar
	 */
	public JLabel getEtiquetaPrecioCriptoADolar() {
		return etiquetaPrecioCriptoADolar;
	}

	/**
	 * @param etiquetaPrecioCriptoADolar the etiquetaPrecioCriptoADolar to set
	 */
	public void setEtiquetaPrecioCriptoADolar(JLabel etiquetaPrecioCriptoADolar) {
		this.etiquetaPrecioCriptoADolar = etiquetaPrecioCriptoADolar;
	}

	/**
	 * @return the etiquetaFIAT
	 */
	public JLabel getEtiquetaFIAT() {
		return etiquetaFIAT;
	}

	/**
	 * @param etiquetaFIAT the etiquetaFIAT to set
	 */
	public void setEtiquetaFIAT(JLabel etiquetaFIAT) {
		this.etiquetaFIAT = etiquetaFIAT;
	}

	/**
	 * @return the selectorFIAT
	 */
	public JComboBox<FIAT> getSelectorFIAT() {
		return selectorFIAT;
	}

	/**
	 * @param selectorFIAT the selectorFIAT to set
	 */
	public void setSelectorFIAT(JComboBox<FIAT> selectorFIAT) {
		this.selectorFIAT = selectorFIAT;
	}

	/**
	 * @return the campoCantidadFIAT
	 */
	public JTextField getCampoCantidadFIAT() {
		return campoCantidadFIAT;
	}

	/**
	 * @param campoCantidadFIAT the campoCantidadFIAT to set
	 */
	public void setCampoCantidadFIAT(JTextField campoCantidadFIAT) {
		this.campoCantidadFIAT = campoCantidadFIAT;
	}

	/**
	 * @return the etiquetaPrecioCriptoAFIAT
	 */
	public JLabel getEtiquetaPrecioCriptoAFIAT() {
		return etiquetaPrecioCriptoAFIAT;
	}

	/**
	 * @param etiquetaPrecioCriptoAFIAT the etiquetaPrecioCriptoAFIAT to set
	 */
	public void setEtiquetaPrecioCriptoAFIAT(JLabel etiquetaPrecioCriptoAFIAT) {
		this.etiquetaPrecioCriptoAFIAT = etiquetaPrecioCriptoAFIAT;
	}

	/**
	 * @return the botonComprar
	 */
	public JButton getBotonComprar() {
		return botonComprar;
	}

	/**
	 * @param botonComprar the botonComprar to set
	 */
	public void setBotonComprar(JButton botonComprar) {
		this.botonComprar = botonComprar;
	}
}
