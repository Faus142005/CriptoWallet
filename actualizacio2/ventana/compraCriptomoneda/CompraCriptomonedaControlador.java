package ventana.compraCriptomoneda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Transaccion;
import clases.UnidadDeCompra;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import ventana.CriptoWalletControlador;
import ventana.VistaMain;

public class CompraCriptomonedaControlador implements CriptoWalletControlador {

	private CompraCriptomonedaVista vista;

	public CompraCriptomonedaControlador(CompraCriptomonedaVista vista, VistaMain vistaMain) {
		this.vista = vista;

		this.vista.getBotonAtras().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vistaMain.panelAnterior();
			}
		});

		this.vista.getBotonComprar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Criptomoneda criptomonedaSeleccionada = (Criptomoneda) vista.getSelectorCriptomonedas()
						.getSelectedItem();
				double cantidadCriptomonedas = Double.valueOf(vista.getCampoCantidadCriptomonedas().getText());

				FIAT fiatSeleccionada = (FIAT) vista.getSelectorFIAT().getSelectedItem();
				double cantidadFIAT = Double.valueOf(vista.getCampoCantidadFIAT().getText());

				UnidadDeCompra unidad = new UnidadDeCompra(GestorDeDatosDeLaAplicacion.getUsuarioConectado(),
						criptomonedaSeleccionada, cantidadCriptomonedas, fiatSeleccionada, cantidadFIAT);

				try {
					Transaccion transaccion = FuncionesDeLaAplicacion.comprarCriptomoneda(unidad);
				} catch (SQLException error) {
					System.err.println("Error: " + error.getMessage());
				}
			}
		});
	}

	@Override
	public void actualizar(IdActualizacion id) {
		switch (id) {
		case ActualizacionCriptomonedas:
			this.vista.cambiarDatosCriptomonedas();
			break;
		case ActualizacionFIATS:
			this.vista.cambiarDatosFIAT();
			break;
		case ActualizacionMonedas:
			this.vista.cambiarDatosCriptomonedas();
			this.vista.cambiarDatosFIAT();
			break;
		case NuevasCriptomonedas:
			this.vista.nuevasCriptomonedas();
			break;
		case NuevasFIATS:
			this.vista.nuevasFIAT();
			break;
		case NuevasMonedas:
			this.vista.nuevasCriptomonedas();
			this.vista.nuevasFIAT();
			break;
		}
	}
}
