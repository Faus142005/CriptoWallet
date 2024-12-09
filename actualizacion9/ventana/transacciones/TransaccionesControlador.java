package ventana.transacciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;

public class TransaccionesControlador implements CriptoWalletControlador{

	private TransaccionesVista vista;
	private CriptoWalletVistaMain vistaMain;
	
	public TransaccionesControlador(TransaccionesVista vista, CriptoWalletVistaMain vistaMain) {
		
		this.vista = vista;
		this.vistaMain = vistaMain;
		
		this.vista.getBotonAtras().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				vistaMain.panelAnterior();
			}
		});
	}

	@Override
	public void actualizar() {
		vista.actualizarTransferencias();
	}

	@Override
	public void ingresoVentana() {
		vistaMain.cambiarTamaño(vista.getDimensiones());
		vista.actualizarTransferencias();
	}	
	
}
