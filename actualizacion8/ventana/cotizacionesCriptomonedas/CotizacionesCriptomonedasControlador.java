package ventana.cotizacionesCriptomonedas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Criptomoneda;
import clases.Moneda;
import daos.FactoryDAO;
import daos.MonedaDAO;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;

public class CotizacionesCriptomonedasControlador implements CriptoWalletControlador {

	private CotizacionesCriptomonedasVista vista;
	private CriptoWalletVistaMain vistaMain;

	public CotizacionesCriptomonedasControlador(CotizacionesCriptomonedasVista vista, CriptoWalletVistaMain vistaMain) {
		this.vista = vista;
		this.vistaMain = vistaMain;

		this.vista.getBotonAtras().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaMain.panelAnterior();
			}
		});

		this.vista.getTabla().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				int row = vista.getTabla().rowAtPoint(e.getPoint());
				int column = vista.getTabla().columnAtPoint(e.getPoint());
				if (column == 6) {
					String nomenclatura = (String) vista.getTabla().getValueAt(row, 2);
					MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
					try {
						GestorDeDatosDeLaAplicacion
								.setCriptomonedaSeleccionada(monedaDAO.buscarCriptomoneda(nomenclatura));
						vistaMain.cambiarPanel("Compra de Criptomoneda");
					} catch (SQLException ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		});

	}

	@Override
	public void actualizar() {
		vista.cambiarDatosCriptomonedas();
	}

	@Override
	public void ingresoVentana() {
		vistaMain.cambiarTamaño(vista.getDimensiones());
		vista.nuevasCriptomonedas();
		
		System.out.println("1");

		this.vista.getBotonesCompra().forEach((criptomoneda, button) -> {

			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					GestorDeDatosDeLaAplicacion.setCriptomonedaSeleccionada(criptomoneda);
					vistaMain.cambiarPanel("Compra de Criptomoneda");
				}
			});
		});
	}
}
