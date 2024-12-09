package ventana.cotizacionesCriptomonedas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import aplicacion.CalculosGenerales;
import clases.Criptomoneda;
import clases.Stock;
import daos.FactoryDAO;
import daos.StockDAO;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import funcionesAplicacion.FuncionesRecursosPrograma;

public class CotizacionesCriptomonedasVista {

	private Dimension dimensiones = new Dimension(1100, 500);
	private JPanel panel = new JPanel();

	private JButton botonAtras = new JButton("Atras");

	private String[] titulos = { "", "Nombre", "Nomenclatura", "Precio", "Stock", "Volatilidad", "Boton" };
	private ModeloTablaCotizaciones modelo = new ModeloTablaCotizaciones(null, titulos);
	private JTable tabla = new JTable(modelo);
	private JScrollPane scrollPaneCriptomonedas = new JScrollPane(tabla);

	private HashMap<String, ImageIcon> imagenesEscaladas = new HashMap<>();
	private HashMap<String, JLabel> etiquetasPrecios = new HashMap<String, JLabel>();
	private HashMap<String, JLabel> etiquetasVolatilidad = new HashMap<String, JLabel>();
	private HashMap<String, JLabel> etiquetasStock = new HashMap<String, JLabel>();
	private HashMap<Criptomoneda, JButton> botonesCompra = new HashMap<Criptomoneda, JButton>();

	public CotizacionesCriptomonedasVista() {
		this.panel.setLayout(null);
		this.panel.add(scrollPaneCriptomonedas);

		this.scrollPaneCriptomonedas.setBounds(50, 50, 1000, 400);

		// Configuración de las columnas
		tabla.getColumnModel().getColumn(0).setPreferredWidth(60); // Icono
		tabla.getColumnModel().getColumn(1).setPreferredWidth(90); // Nombre/Nomenclatura
		tabla.getColumnModel().getColumn(2).setPreferredWidth(110); // Nombre/Nomenclatura
		tabla.getColumnModel().getColumn(3).setPreferredWidth(270); // Precio
		tabla.getColumnModel().getColumn(4).setPreferredWidth(270); // Stock
		tabla.getColumnModel().getColumn(5).setPreferredWidth(100); // Volatilidad
		tabla.getColumnModel().getColumn(6).setPreferredWidth(100); // Botón
		tabla.setRowHeight(60);
		tabla.setShowGrid(false);
		// Desactivar el cambio de color al seleccionar una celda
		tabla.setSelectionBackground(new Color(255, 255, 255)); // Color de fondo al seleccionar una celda (blanco)
		tabla.setSelectionForeground(Color.BLACK); // Color del texto al seleccionar (negro)

		

		// Configuración para centrar las celdas
		DefaultTableCellRenderer centrarRenderer = new DefaultTableCellRenderer();
		centrarRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 1; i < 6; i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(centrarRenderer);
		}

		// Crear el TableRowSorter para hacer la tabla ordenable
		TableRowSorter<ModeloTablaCotizaciones> sorter = new TableRowSorter<>(modelo);
		tabla.setRowSorter(sorter); 

		// Asignar un renderer personalizado a la columna de botones
		tabla.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());

		this.botonAtras.setBounds(15, 15, 100, 30);
		this.panel.add(botonAtras);

		this.panel.setName("Cotizaciones Criptomonedas");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);

		this.nuevasCriptomonedas();
	}

	// Clase personalizada para renderizar botones en la tabla
	private class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (value instanceof JButton) {
				JButton button = (JButton) value;
				return button; // Devuelve el botón que se ha creado en la tabla
			}
			return this; // Si no es un botón, se devuelve el componente por defecto
		}
	}

	public void cambiarDatosCriptomonedas() {		
		
		for(int i = 0; i < modelo.getRowCount(); i++) {
			
			String nomenclatura = (String)modelo.getValueAt(i, 2);
			//Listar criptomonedas
		}
	}

	public void nuevasCriptomonedas() {

		imagenesEscaladas.clear();

		etiquetasPrecios.clear();
		etiquetasVolatilidad.clear();
		etiquetasStock.clear();
		botonesCompra.clear();
		
		tabla.setRowSorter(null);
		modelo.setRowCount(0);
		TableRowSorter<ModeloTablaCotizaciones> sorter = new TableRowSorter<>(modelo);
		tabla.setRowSorter(sorter); 
		
		
		tabla.removeAll();

		List<Criptomoneda> criptomonedas = FuncionesDeLaAplicacion.listarCriptomonedas();
		StockDAO<Stock> stockDAO = FactoryDAO.getStockDAO();

		HashMap<String, ImageIcon> auxImagenes = FuncionesRecursosPrograma.obtenerIconosDeCriptomonedas();

		DecimalFormat formato = new DecimalFormat("#.##########");

		try {
			for (Criptomoneda criptomoneda : criptomonedas) {

				ImageIcon icon = CalculosGenerales.escalarImagenAlto(auxImagenes.get(criptomoneda.getNomenclatura()),
						50);

				Stock s = stockDAO.buscarStock(criptomoneda.getNomenclatura());

				imagenesEscaladas.put(criptomoneda.getNomenclatura(), icon);

				Object[] dato = { icon, criptomoneda.getNombre(), criptomoneda.getNomenclatura(),
						formato.format(criptomoneda.getValorDolar()), formato.format(s.getCantidad()),
						formato.format(criptomoneda.getVolatilidad()) + "%", new JButton("Comprar")};

				modelo.addRow(dato);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @return the dimensiones
	 */
	public Dimension getDimensiones() {
		return dimensiones;
	}

	/**
	 * @param dimensiones the dimensiones to set
	 */
	public void setDimensiones(Dimension dimensiones) {
		this.dimensiones = dimensiones;
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * @return the botonAtras
	 */
	public JButton getBotonAtras() {
		return botonAtras;
	}

	/**
	 * @param botonAtras the botonAtras to set
	 */
	public void setBotonAtras(JButton botonAtras) {
		this.botonAtras = botonAtras;
	}

	/**
	 * @return the modelo
	 */
	public ModeloTablaCotizaciones getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(ModeloTablaCotizaciones modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the tabla
	 */
	public JTable getTabla() {
		return tabla;
	}

	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	/**
	 * @return the scrollPaneCriptomonedas
	 */
	public JScrollPane getScrollPaneCriptomonedas() {
		return scrollPaneCriptomonedas;
	}

	/**
	 * @param scrollPaneCriptomonedas the scrollPaneCriptomonedas to set
	 */
	public void setScrollPaneCriptomonedas(JScrollPane scrollPaneCriptomonedas) {
		this.scrollPaneCriptomonedas = scrollPaneCriptomonedas;
	}

	/**
	 * @return the imagenesEscaladas
	 */
	public HashMap<String, ImageIcon> getImagenesEscaladas() {
		return imagenesEscaladas;
	}

	/**
	 * @param imagenesEscaladas the imagenesEscaladas to set
	 */
	public void setImagenesEscaladas(HashMap<String, ImageIcon> imagenesEscaladas) {
		this.imagenesEscaladas = imagenesEscaladas;
	}

	/**
	 * @return the etiquetasPrecios
	 */
	public HashMap<String, JLabel> getEtiquetasPrecios() {
		return etiquetasPrecios;
	}

	/**
	 * @param etiquetasPrecios the etiquetasPrecios to set
	 */
	public void setEtiquetasPrecios(HashMap<String, JLabel> etiquetasPrecios) {
		this.etiquetasPrecios = etiquetasPrecios;
	}

	/**
	 * @return the etiquetasVolatilidad
	 */
	public HashMap<String, JLabel> getEtiquetasVolatilidad() {
		return etiquetasVolatilidad;
	}

	/**
	 * @param etiquetasVolatilidad the etiquetasVolatilidad to set
	 */
	public void setEtiquetasVolatilidad(HashMap<String, JLabel> etiquetasVolatilidad) {
		this.etiquetasVolatilidad = etiquetasVolatilidad;
	}

	/**
	 * @return the etiquetasStock
	 */
	public HashMap<String, JLabel> getEtiquetasStock() {
		return etiquetasStock;
	}

	/**
	 * @param etiquetasStock the etiquetasStock to set
	 */
	public void setEtiquetasStock(HashMap<String, JLabel> etiquetasStock) {
		this.etiquetasStock = etiquetasStock;
	}

	/**
	 * @return the botonesCompra
	 */
	public HashMap<Criptomoneda, JButton> getBotonesCompra() {
		return botonesCompra;
	}

	/**
	 * @param botonesCompra the botonesCompra to set
	 */
	public void setBotonesCompra(HashMap<Criptomoneda, JButton> botonesCompra) {
		this.botonesCompra = botonesCompra;
	}

}