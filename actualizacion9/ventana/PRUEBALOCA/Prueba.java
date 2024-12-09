package ventana.PRUEBALOCA;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import funcionalidadesVentana.CambiadorDePaneles;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;

public class Prueba extends JFrame implements CriptoWalletVistaMain{
	
	private CambiadorDePaneles cambiador = new CambiadorDePaneles();
	private JPanel panel = new JPanel();
	
	private PruebaVista pruebaVista = new PruebaVista();
	private PruebaControlador pruebaControlador = new PruebaControlador(pruebaVista, this);
	
	private PruebaVista2 pruebaVista2 = new PruebaVista2();
	private PruebaControlador2 pruebaControlador2 = new PruebaControlador2(pruebaVista2, this);
	

	public Prueba(){
		
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.add(panel);
				
		cambiador.setPanelPadre(panel);
		cambiador.agregarPanelHijo(pruebaVista.getPanel(), pruebaVista.getPanel().getName(), pruebaControlador);
		cambiador.agregarPanelHijo(pruebaVista2.getPanel(), pruebaVista2.getPanel().getName(), pruebaControlador2);
		cambiador.mostrarPanel("Panel1");
	}


	@Override
	public CriptoWalletControlador cambiarPanel(String id) {
		
		return cambiador.mostrarPanel(id);
	}


	@Override
	public CriptoWalletControlador panelAnterior() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void cambiarTamaño(int ancho, int alto) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void cambiarTamaño(Dimension d) {
		// TODO Auto-generated method stub
		
	}
}
