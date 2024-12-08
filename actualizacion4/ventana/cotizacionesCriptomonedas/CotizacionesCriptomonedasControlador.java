package ventana.cotizacionesCriptomonedas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;

public class CotizacionesCriptomonedasControlador implements CriptoWalletControlador{

	private CotizacionesCriptomonedasVista vista;
	private CriptoWalletVistaMain vistaMain;

	public CotizacionesCriptomonedasControlador(CotizacionesCriptomonedasVista vista, CriptoWalletVistaMain vistaMain) {
		this.vista = vista;
		this.vistaMain = vistaMain;

		this.vista.getBotonAtras().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaMain.panelAnterior();
			}
		});
		
		this.vista.getBotonesCompra().forEach((key, button) -> {
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					vistaMain.cambiarPanel("Compra de Criptomoneda");
				}
			});
		});
	}
	
	@Override
	public void actualizar() {
		vista.cambiarDatosCriptomonedas();
	}

	@Override
	public void ingresoVentana() {
		vistaMain.cambiarTamaño(vista.getDimensiones());
		vista.nuevasCriptomonedas();
	}
}
