package funcionalidadesVentana;

import java.awt.Dimension;


public interface CriptoWalletVistaMain {

	public CriptoWalletControlador panelAnterior();
	public CriptoWalletControlador cambiarPanel(String id);	
	public void cambiarTamaño(int ancho, int alto);
	public void cambiarTamaño(Dimension d);
}
