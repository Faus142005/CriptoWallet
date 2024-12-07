package ventana.visualizacionActivos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.CriptoWalletControlador;
import ventana.VistaMain;

public class VisualizarActivosControlador implements CriptoWalletControlador {

	private VisualizarActivosVista vista;

	public VisualizarActivosControlador(VisualizarActivosVista vista, VistaMain vistaMain) {
		this.vista = vista;

		this.vista.getBotonAtras().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.panelAnterior();
			}
		});

		this.vista.getBotonExportarCVS().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	@Override
	public void actualizar(IdActualizacion id) {

		switch (id) {
		case ActualizacionCriptomonedas:
		case ActualizacionFIATS:
		case ActualizacionMonedas:
			this.vista.actualizarDatosActivos();
			break;
		case ActualizacionActivos:
			this.vista.actualizarActivos();
			break;
		}
	}
}
