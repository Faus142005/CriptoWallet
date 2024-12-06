package ventana.compraCriptomoneda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import aplicacion.FuncionesDeLaAplicacion;
import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Transaccion;
import clases.UnidadDeCompra;
import clases.Usuario;
import ventana.VistaMain;

public class CompraCriptomonedaControlador {

	private CompraCriptomonedaVista vista;

	public CompraCriptomonedaControlador(CompraCriptomonedaVista vista, VistaMain vistaMain) {
		this.vista = vista;

		this.vista.getBotonAtras().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				vistaMain.cambiarPanel("Inicio");
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
}
