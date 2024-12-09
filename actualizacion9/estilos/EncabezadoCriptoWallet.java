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
	
	public static JPanel crearEncabezado(String titulo, String rutaIcono) {
        // Crear panel para el encabezado
        JPanel encabezado = new JPanel();
        encabezado.setLayout(null); 
        encabezado.setBackground(Color.BLACK); // Fondo negro
        encabezado.setPreferredSize(new Dimension(0, 100)); // Tamaño fijo

        // Crear etiqueta para el título
        JLabel etiquetaTitulo = new JLabel(titulo);
        etiquetaTitulo.setForeground(Color.WHITE); // Texto blanco
        etiquetaTitulo.setFont(new Font("SansSerif", Font.BOLD, 34)); 
        etiquetaTitulo.setBounds(200, 30, 200, 40); 

        // Cargar y redimensionar el ícono
        ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        JLabel etiquetaIcono = new JLabel(iconoRedimensionado);
        etiquetaIcono.setBounds(500, 20, 60, 60); // Posicionamos el ícono a la derecha del título

        // Añadir los componentes al encabezado
        encabezado.add(etiquetaTitulo);
        encabezado.add(etiquetaIcono);

        encabezado.setBounds(0, 0, 700, 100);
        return encabezado;
	}
	
}