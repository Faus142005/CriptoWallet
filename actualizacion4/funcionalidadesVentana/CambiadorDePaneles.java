package funcionalidadesVentana;

import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JPanel;

public class CambiadorDePaneles {

	private JPanel panelPadre = new JPanel();
	private HashMap<String, JPanel> panelesHijos = new HashMap<String, JPanel>();
	private HashMap<String, CriptoWalletControlador> controladores = new HashMap<String, CriptoWalletControlador>();
	private JPanel panelHijoAnterior;

	public CambiadorDePaneles() {
	}

	public JPanel getPanelPadre() {
		return panelPadre;
	}

	public void setPanelPadre(JPanel panelPadre) {
		this.panelPadre = panelPadre;
	}

	public void agregarPanelHijo(JPanel panel, String id, CriptoWalletControlador c) {
		panelesHijos.put(id, panel);
		controladores.put(id, c);
	}

	public void eliminarPanelHijo(String id) {
		panelesHijos.remove(id);
		controladores.remove(id);
	}

	public CriptoWalletControlador mostrarPanel(String idPanel) {
		if (panelHijoAnterior != null) {
			panelPadre.remove(panelHijoAnterior);
		}
		panelHijoAnterior = panelesHijos.get(idPanel);
		if(panelHijoAnterior == null)
			return null;
		panelPadre.add(panelHijoAnterior);
		panelHijoAnterior.setVisible(true);
		panelPadre.repaint();
		panelPadre.revalidate();
		return controladores.get(idPanel);
	}
}
