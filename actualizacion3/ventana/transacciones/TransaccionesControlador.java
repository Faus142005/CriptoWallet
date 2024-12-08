package ventana.transacciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.CriptoWalletControlador;
import ventana.VistaMain;

public class TransaccionesControlador implements CriptoWalletControlador{

	private TransaccionesVista vista;
	
	public TransaccionesControlador(TransaccionesVista vista, VistaMain vistaMain) {
		
		this.vista = vista;
		
		this.vista.getBotonAtras().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				vistaMain.panelAnterior();
			}
		});
	}

	@Override
	public void actualizar(IdActualizacion id) {
		this.vista.actualizarTransferencias();
	}
}
