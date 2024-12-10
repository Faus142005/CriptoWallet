package estilos;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EncabezadoCriptoWallet {

    /**
     * Crea un encabezado con CriptoWallet y el ícono.
     * 
     * @param titulo El título de la aplicación.
     * @param rutaIcono La ruta del ícono
     * @return Un JPanel que representa el encabezado.
     */
	
	public static JPanel crearEncabezado(String titulo, String rutaIcono, int ancho) {
        // Crear panel para el encabezado
        JPanel encabezado = new JPanel();
        encabezado.setLayout(null); 
        encabezado.setBackground(Color.BLACK); // Fondo negro
 
        // Cargar y redimensionar el ícono
        ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); 
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

       
        JLabel etiquetaTituloIcono = new JLabel(titulo, iconoRedimensionado, JLabel.CENTER);
        etiquetaTituloIcono.setForeground(Color.WHITE); // Texto blanco
        etiquetaTituloIcono.setFont(new Font("SansSerif", Font.BOLD, 40));
        etiquetaTituloIcono.setBounds((ancho - 400) / 2, 20, 400, 60); // Centramos el contenido


        encabezado.add(etiquetaTituloIcono);
        encabezado.setBounds(0, 0, ancho, 100);
        return encabezado;
	}
	
}