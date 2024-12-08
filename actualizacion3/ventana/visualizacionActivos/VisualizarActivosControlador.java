package ventana.visualizacionActivos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aplicacion.GestorDeDatosDeLaAplicacion;
import funcionesAplicacion.FuncionesDeLaAplicacion;
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
				FuncionesDeLaAplicacion.exportarCSV(GestorDeDatosDeLaAplicacion.getUsuarioConectado());

			}
		});
	}

	@Override
	public void actualizar(IdActualizacion id) {
		this.vista.actualizarActivos();
	}
}
