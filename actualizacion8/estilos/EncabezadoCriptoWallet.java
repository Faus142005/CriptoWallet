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
        encabezado.setLayout(new BorderLayout());
        encabezado.setBackground(Color.BLACK); // Fondo negro
        encabezado.setPreferredSize(new Dimension(0, 100)); // Altura fija para el encabezado
        encabezado.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margen interno

        // Cargar y redimensionar el ícono
        ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); 
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        // 	JLabel para el ícono
        JLabel etiquetaIcono = new JLabel(iconoRedimensionado); 

        // Crear etiqueta para el título
        JLabel etiquetaTitulo = new JLabel(titulo);
        etiquetaTitulo.setForeground(Color.WHITE); // Texto blanco
        etiquetaTitulo.setFont(new Font("Serif", Font.BOLD, 28)); // Fuente personalizada
        etiquetaTitulo.setAlignmentX(0);

        // Añadir elementos al encabezado
        encabezado.add(etiquetaIcono, BorderLayout.EAST); // Ícono a la izquierda
        encabezado.add(etiquetaTitulo, BorderLayout.CENTER); // Título centrado

        return encabezado;
    }
}