package ventana.PRUEBALOCA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;

public class PruebaControlador implements CriptoWalletControlador {

	private PruebaVista vista;
	private CriptoWalletVistaMain vistaMain;

	public PruebaControlador(PruebaVista vista, CriptoWalletVistaMain vistaMain) {

		this.vista = vista;

		this.vista.getBoton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaMain.cambiarPanel("Panel2");
			}
		});
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ingresoVentana() {
		// TODO Auto-generated method stub
		
	}
}
