package ventana;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import funcionalidadesVentana.CambiadorDePaneles;
import funcionalidadesVentana.CriptoWalletControlador;
import funcionalidadesVentana.CriptoWalletVistaMain;
import funcionesAplicacion.FuncionesDeConexionCriptomonedas;
import ventana.compraCriptomoneda.CompraCriptomonedaControlador;
import ventana.compraCriptomoneda.CompraCriptomonedaVista;
import ventana.cotizacionesCriptomonedas.CotizacionesCriptomonedasControlador;
import ventana.cotizacionesCriptomonedas.CotizacionesCriptomonedasVista;
import ventana.ingresar.IngresarControlador;
import ventana.ingresar.IngresarVista;
import ventana.inicio.InicioControlador;
import ventana.inicio.InicioVista;
import ventana.registrarse.RegistrarseControlador;
import ventana.registrarse.RegistrarseVista;
import ventana.transacciones.TransaccionesControlador;
import ventana.transacciones.TransaccionesVista;
import ventana.visualizacionActivos.VisualizarActivosControlador;
import ventana.visualizacionActivos.VisualizarActivosVista;

public class VistaMain extends JFrame implements CriptoWalletVistaMain,Runnable{
	
	private boolean encendido;

	private CambiadorDePaneles cambiadorPaneles = new CambiadorDePaneles();
	private JPanel panel = new JPanel();
	private Stack<String> pilaDePaneles = new Stack<String>();

	// Clases vista
	private RegistrarseVista registrarseVista = new RegistrarseVista();
	private IngresarVista ingresarVista = new IngresarVista();
	private InicioVista inicioVista = new InicioVista();
	private CotizacionesCriptomonedasVista cotizacionesCriptomonedasVista = new CotizacionesCriptomonedasVista();
	private CompraCriptomonedaVista compraCriptomonedaVista = new CompraCriptomonedaVista();
	private VisualizarActivosVista visualizarActivosVista = new VisualizarActivosVista();
	private TransaccionesVista transaccionesVista = new TransaccionesVista();

	// Clases Controlador

	private RegistrarseControlador registrarseControlador = new RegistrarseControlador(registrarseVista, this);
	private IngresarControlador ingresarControlador = new IngresarControlador(ingresarVista, this);
	private InicioControlador inicioControlador = new InicioControlador(inicioVista, this);
	private CotizacionesCriptomonedasControlador cotizacionesCriptomonedasControlador = new CotizacionesCriptomonedasControlador(
			cotizacionesCriptomonedasVista, this);
	private CompraCriptomonedaControlador compraCriptomonedaControlador = new CompraCriptomonedaControlador(
			compraCriptomonedaVista, this);
	private VisualizarActivosControlador visualizarActivosControlador = new VisualizarActivosControlador(
			visualizarActivosVista, this);
	private TransaccionesControlador transaccionesControlador = new TransaccionesControlador(transaccionesVista, this);
	
	private ArrayList<CriptoWalletControlador> controladores = new ArrayList<CriptoWalletControlador>(7);

	public VistaMain() {

		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.encendido = true;
		cambiadorPaneles.setPanelPadre(panel);
		cambiadorPaneles.agregarPanelHijo(registrarseVista.getPanel(), registrarseVista.getPanel().getName(), registrarseControlador);
		cambiadorPaneles.agregarPanelHijo(ingresarVista.getPanel(), ingresarVista.getPanel().getName(), ingresarControlador);
		cambiadorPaneles.agregarPanelHijo(inicioVista.getPanel(), inicioVista.getPanel().getName(), inicioControlador);
		cambiadorPaneles.agregarPanelHijo(cotizacionesCriptomonedasVista.getPanel(), cotizacionesCriptomonedasVista.getPanel().getName(), cotizacionesCriptomonedasControlador);
		cambiadorPaneles.agregarPanelHijo(compraCriptomonedaVista.getPanel(), compraCriptomonedaVista.getPanel().getName(), compraCriptomonedaControlador);
		cambiadorPaneles.agregarPanelHijo(visualizarActivosVista.getPanel(), visualizarActivosVista.getPanel().getName(), visualizarActivosControlador);
		cambiadorPaneles.agregarPanelHijo(transaccionesVista.getPanel(), transaccionesVista.getPanel().getName(), transaccionesControlador);

		// this.cambiarPanel(registrarseVista.getPanel().getName());
		this.cambiarPanel(ingresarVista.getPanel().getName());
		//this.cambiarPanel(inicioVista.getPanel().getName());
		// this.cambiarPanel(compraCriptomonedaVista.getPanel().getName());
		// this.cambiarPanel(registrarseVista.getPanel().getName());
		// this.cambiarPanel(cotizacionesCriptomonedasVista.getPanel().getName());
		// this.cambiarPanel(visualizarActivosVista.getPanel().getName());

		this.add(panel);
		this.setLocationRelativeTo(null);
		
		Thread hiloActualizacionPrecios = new Thread(this);
		hiloActualizacionPrecios.start();
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	encendido = false;
            	dispose();
            }
        });
		
		this.setVisible(true);
	}

	public CriptoWalletControlador cambiarPanel(String id) {
		pilaDePaneles.push(id);
		CriptoWalletControlador cc = cambiadorPaneles.mostrarPanel(id);
		cc.ingresoVentana();
		return cc;
	}

	public CriptoWalletControlador panelAnterior() {
		pilaDePaneles.pop();
		String id = pilaDePaneles.pop();
		this.cambiarPanel(id);
		return null;
	}
	
	@Override
	public void cambiarTamaño(int ancho, int alto) {
		this.setLocationRelativeTo(null);
		this.setSize(ancho, alto);
	}

	@Override
	public void cambiarTamaño(Dimension d) {
		this.setLocationRelativeTo(null);
		this.setSize(d);
	}

	@Override
	public void run() {
		
		final int segs = 60;
		
		while(encendido) {
			
			FuncionesDeConexionCriptomonedas.actualizarPrecios();
			try {
				Thread.sleep(1000 * segs);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
