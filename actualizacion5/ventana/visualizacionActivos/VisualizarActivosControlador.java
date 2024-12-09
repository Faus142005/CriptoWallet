package ventana.visualizacionActivos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aplicacion.GestorDeDatosDeLaAplicacion;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesDeLaAplicacion;

public class VisualizarActivosControlador implements CriptoWalletControlador {

	private VisualizarActivosVista vista;
	private CriptoWalletVistaMain vistaMain;

	public VisualizarActivosControlador(VisualizarActivosVista vista, CriptoWalletVistaMain vistaMain) {
		this.vista = vista;
		this.vistaMain = vistaMain;

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
	public void actualizar() {
		vista.actualizarDatosActivos();
	}

	@Override
	public void ingresoVentana() {
		vistaMain.cambiarTamaño(vista.getDimensiones());
		vista.nuevosActivos();
	}
}
