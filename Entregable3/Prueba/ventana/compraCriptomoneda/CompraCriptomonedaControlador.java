package ventana.compraCriptomoneda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Transaccion;
import clases.UnidadDeCompra;
import clases.Usuario;

public class CompraCriptomonedaControlador {

	private CompraCriptomonedaVista vista;
	private CompraCriptomonedaModelo modelo;

	private Usuario usuario;

	public CompraCriptomonedaControlador(CompraCriptomonedaVista compraCriptomonedaVista,
			CompraCriptomonedaModelo compraCriptomonedaModelo) {
		this.modelo = modelo;
		this.vista = vista;

		this.vista.getBotonAtras().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		this.vista.getBotonComprar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String criptomonedaSeleccionada = vista.getSelectorCriptomonedas().getSelectedItem().toString();
				double cantidadCriptomonedas = Double.valueOf(vista.getCampoCantidadCriptomonedas().getText());
				
				String fiatSeleccionada = vista.getSelectorFIAT().getSelectedItem().toString();
				double cantidadFIAT = Double.valueOf(vista.getCampoCantidadFIAT().getText());
				
				//UnidadDeCompra unidad = new UnidadDeCompra(usuario, criptomonedaSeleccionada, cantidadCriptomonedas, fiatSeleccionada, cantidadFIAT);
				

				//Transaccion transaccion = modelo.comprarCriptomoneda(unidad); // Si queres mostrar la transaccion
			}
		});
	}
}
