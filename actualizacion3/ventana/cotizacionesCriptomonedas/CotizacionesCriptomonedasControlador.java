package ventana.cotizacionesCriptomonedas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aplicacion.GestorDeDatosDeLaAplicacion;
import ventana.CriptoWalletControlador;
import ventana.VistaMain;

public class CotizacionesCriptomonedasControlador implements CriptoWalletControlador{

	CotizacionesCriptomonedasVista vista;

	public CotizacionesCriptomonedasControlador(CotizacionesCriptomonedasVista vista, VistaMain vistaMain) {
		this.vista = vista;

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
	public void actualizar(IdActualizacion id) {
		
		this.vista.nuevasCriptomonedas();
	}
}
