package ventana.inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aplicacion.GestorDeDatosDeLaAplicacion;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesDeCreacionDeMonedasYStock;

public class InicioControlador implements CriptoWalletControlador {

	private InicioVista vista;
	private CriptoWalletVistaMain vistaMain;

	public InicioControlador(InicioVista vista, CriptoWalletVistaMain vistaMain) {
		this.vista = vista;
		this.vistaMain = vistaMain;

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

		this.vista.getBotonGenerarStock().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FuncionesDeCreacionDeMonedasYStock.generarStock();
			}
		});

		this.vista.getBotonGenerarActivos().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FuncionesDeCreacionDeMonedasYStock.generarActivos(GestorDeDatosDeLaAplicacion.getUsuarioConectado());
			}
		});
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ingresoVentana() {
		vistaMain.cambiarTamaño(vista.getDimensiones());
	}	
}
