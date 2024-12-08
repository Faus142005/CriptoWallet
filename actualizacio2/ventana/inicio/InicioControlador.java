package ventana.inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aplicacion.GestorDeDatosDeLaAplicacion;
import ventana.CriptoWalletControlador;
import ventana.VistaMain;

public class InicioControlador implements CriptoWalletControlador{

	private InicioVista vista;

	public InicioControlador(InicioVista vista, VistaMain vistaMain) {
		this.vista = vista;

		this.vista.getBotonCerrarSesion().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GestorDeDatosDeLaAplicacion.setUsuarioConectado(null);
				vistaMain.panelAnterior();
			}
		});

		this.vista.getBotonVisualizarActivos().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.cambiarPanel("Visualizar Activos");
			}
		});

		this.vista.getBotonVerCotizacionCriptomonedas().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.cambiarPanel("Cotizaciones Criptomonedas");
			}
		});
		
		this.vista.getBotonComprarCriptomonedas().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.cambiarPanel("Compra de Criptomoneda");
			}
		});
		
		this.vista.getBotonVisualizarTransaccion().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.cambiarPanel("Transacciones");
			}
		});
	}
	
	@Override
	public void actualizar(IdActualizacion id) {
		
	}
}
