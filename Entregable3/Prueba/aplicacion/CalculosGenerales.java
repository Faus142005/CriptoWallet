package aplicacion;

import java.awt.Image;

import javax.swing.ImageIcon;

public class CalculosGenerales {

	public static ImageIcon escalarImagenAlto(ImageIcon icono, int alto) {
		Image img = icono.getImage();
		int ancho = (int) (icono.getIconWidth() * ((double) alto / icono.getIconHeight()));
		Image imagenEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(imagenEscalada);
	}
	
	public static ImageIcon escalarImagenAncho(ImageIcon icono, int ancho) {
	    Image img = icono.getImage();
	    int alto = (int) (icono.getIconHeight() * ((double) ancho / icono.getIconWidth()));
	    Image imagenEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	    return new ImageIcon(imagenEscalada);
	}
}
