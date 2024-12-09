package ventana.inicio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.CalculosGenerales;
import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.Persona;
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
		
		this.vista.getFotoUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Código que se ejecuta cuando se hace clic en la etiqueta
            	vista.getFotoUsuario().setIcon(new ImageIcon("iconos/laurafava.gif"));
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
		
		Persona persona = GestorDeDatosDeLaAplicacion.getUsuarioConectado().getPersona();
		String letras = (String.valueOf(persona.getNombres().charAt(0)) + String.valueOf(persona.getApellidos().charAt(0))).toUpperCase();
		vista.getFotoUsuario().setIcon(crearFotoPerfil(70, letras));
	}
	
	private static Icon crearFotoPerfil(int radio, String letras) {
        int diametro = 2 * radio;
        BufferedImage imagen = new BufferedImage(diametro, diametro, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagen.createGraphics();
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, diametro, diametro);

        g2d.setColor(new Color(0,100,255));
        g2d.fill(new Ellipse2D.Double(0, 0, diametro, diametro));

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, radio));

        // Centrar el texto
        int x = (diametro - g2d.getFontMetrics().stringWidth(letras)) / 2;
        int y = (diametro + g2d.getFontMetrics().getAscent()) / 2;

        g2d.drawString(letras, x, y);

        g2d.dispose();
        ImageIcon icon = new ImageIcon(imagen);
        return icon;
    }
}
