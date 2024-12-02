package ventana.cotizacionesCriptomonedas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import aplicacion.FuncionesDeLaAplicacion;
import aplicacion.CalculosGenerales;
import clases.Criptomoneda;

public class CotizacionesCriptomonedasVista {

	private Dimension dimensiones = new Dimension(600, 500);
	private JPanel panel = new JPanel();

	private JPanel panelScrollPaneCriptomonedas = new JPanel();
	private JScrollPane scrollPaneCriptomonedas = new JScrollPane(panelScrollPaneCriptomonedas);

	private HashMap<String, ImageIcon> imagenesEscaladas = new HashMap<>();
	private HashMap<String, JLabel> etiquetasPrecios = new HashMap<String, JLabel>();

	public CotizacionesCriptomonedasVista() {
		this.panel.setLayout(null);
		this.panel.add(scrollPaneCriptomonedas);

		this.panelScrollPaneCriptomonedas.setLayout(new BoxLayout(panelScrollPaneCriptomonedas, BoxLayout.Y_AXIS));
		this.scrollPaneCriptomonedas.setBounds(100, 50, 400, 400);

		this.panel.setName("Cotizaciones Criptomonedas");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);

		this.actualizarCriptomonedas();
	}

	public void cambiarPreciosCriptomonedas() {

		List<Criptomoneda> criptomonedas = FuncionesDeLaAplicacion.listarCriptomonedas();

		for (Criptomoneda criptomoneda : criptomonedas) {
			etiquetasPrecios.get(criptomoneda.getNomenclatura()).setText(String.valueOf(criptomoneda.getValorDolar()));
		}
	}

	public void actualizarCriptomonedas() {

		imagenesEscaladas.clear();
		etiquetasPrecios.clear();

		List<Criptomoneda> criptomonedas = FuncionesDeLaAplicacion.listarCriptomonedas();

		HashMap<String, ImageIcon> auxImagenes = FuncionesDeLaAplicacion.obtenerIconsDeCriptomonedas();

		for (Criptomoneda criptomoneda : criptomonedas) {

			imagenesEscaladas.put(criptomoneda.getNomenclatura(),
					CalculosGenerales.escalarImagenAlto(auxImagenes.get(criptomoneda.getNomenclatura()), 40));

			JPanel monedaPanel = new JPanel(new BorderLayout());
			monedaPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			JPanel izquierdaPanel = new JPanel();
			izquierdaPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			JLabel imagenLabel = new JLabel(imagenesEscaladas.get(criptomoneda.getNomenclatura()));

			izquierdaPanel.add(imagenLabel, gbc);

			JPanel derechaPanel = new JPanel();
			derechaPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc2 = new GridBagConstraints();
			gbc2.anchor = GridBagConstraints.CENTER;
			gbc2.gridx = 0;

			JLabel textoLabel = new JLabel(criptomoneda.getNombre() + " (" + criptomoneda.getNomenclatura() + ")");
			gbc2.gridy = 0;
			derechaPanel.add(textoLabel, gbc2);

			JLabel precioLabel = new JLabel(String.valueOf(criptomoneda.getValorDolar()));
			gbc2.gridy = 1;
			derechaPanel.add(precioLabel, gbc2);
			etiquetasPrecios.put(criptomoneda.getNomenclatura(), precioLabel);

			monedaPanel.add(izquierdaPanel, BorderLayout.WEST);
			monedaPanel.add(derechaPanel, BorderLayout.CENTER);

			JButton boton = new JButton("Comprar");
			boton.setPreferredSize(new Dimension(120, 30));
			boton.setMaximumSize(new Dimension(120, 30));
			boton.setMinimumSize(new Dimension(120, 30));
			boton.addActionListener(e -> {
			});
			monedaPanel.add(boton, BorderLayout.EAST);
			
			panelScrollPaneCriptomonedas.add(monedaPanel);
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

}