package ventana.visualizacionActivos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import aplicacion.CalculosGenerales;
import aplicacion.GestorDeDatosDeLaAplicacion;
import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;

import estilos.EncabezadoCriptoWallet;
import estilos.ModeloTabla;
import funcionesAplicacion.FuncionesDeLaAplicacion;
import funcionesAplicacion.FuncionesRecursosPrograma;


public class VisualizarActivosVista {

	private Dimension dimensiones = new Dimension(900, 600);
	private JPanel panel = new JPanel();

	// FUNCIONAMIENTO SCROLL PANE
	private HashMap<String, ImageIcon> imagenesEscaladas = new HashMap<>();

	//Tabla
	private String[] titulos = { "", "Criptomoneda", "Volatilidad", "Cantidad", "Monto en USD"};
	private ModeloTabla modelo = new ModeloTabla(null, titulos);
	private JTable tabla = new JTable(modelo);
	private JScrollPane scrollPaneActivos = new JScrollPane(tabla);
	
	// OTRAS COSAS
	private static JPanel encabezado = EncabezadoCriptoWallet.crearEncabezado("CriptoWallet", "iconos/logo.png");
	private JLabel etiquetaBalance = new JLabel();
	private JButton botonAtras = new JButton("Atras");
	private JButton botonExportarCVS = new JButton("Exportar CVS");


	public VisualizarActivosVista() {

		this.panel.setLayout(null);
		this.panel.add(scrollPaneActivos);

		this.scrollPaneActivos.setBounds(25, 110, 850, 400);
		this.scrollPaneActivos.getVerticalScrollBar().setUnitIncrement(10);

		// Configuración de las columnas
		tabla.getColumnModel().getColumn(0).setPreferredWidth(60); 	// Icono
		tabla.getColumnModel().getColumn(1).setPreferredWidth(80); 	// Criptomoneda (NOMENCLATURA)		
		tabla.getColumnModel().getColumn(2).setPreferredWidth(30); 	// Volatilidad
		tabla.getColumnModel().getColumn(3).setPreferredWidth(90); 	// Cantidad
		tabla.getColumnModel().getColumn(4).setPreferredWidth(90); 	// Monto en USD		
		tabla.setRowHeight(60);
		tabla.setShowGrid(false);
		
		// Desactivar el cambio de color al seleccionar una celda
		tabla.setSelectionBackground(new Color(255, 255, 255)); // Color de fondo al seleccionar una celda (blanco)
		tabla.setSelectionForeground(Color.BLACK); // Color del texto al seleccionar (negro)

		// Configuración para centrar las celdas
		DefaultTableCellRenderer centrarRenderer = new DefaultTableCellRenderer();
		centrarRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 1; i < tabla.getColumnCount(); i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(centrarRenderer);
		}

		// Crear el TableRowSorter para hacer la tabla ordenable
		TableRowSorter<ModeloTabla> sorter = new TableRowSorter<>(modelo);
		tabla.setRowSorter(sorter); 
		
		this.botonAtras.setBounds(15, 15, 100, 30);
		this.panel.add(botonAtras);

		this.panel.setName("Visualizar Activos");
		this.panel.setSize(dimensiones);
		this.panel.setPreferredSize(dimensiones);

		encabezado.setBounds(0, 0, 900, 100);
		this.panel.add(encabezado);
		
		this.etiquetaBalance.setBounds(50, 460, 300, 30);
		this.panel.add(etiquetaBalance);		
		
		this.nuevosActivos();			
		 
	}

	private String obtenerNomenclatura(String textoTabla) {
		//Busco índice del inicio del paréntesis
		int startIndex = textoTabla.indexOf("(") + 1; 
		//Busco índice del cierre del paréntesis
	    int endIndex = textoTabla.indexOf(")"); 
	    
	    if (startIndex > 0 && endIndex > startIndex) {
	        String nomenclatura = textoTabla.substring(startIndex, endIndex);
	        return nomenclatura;
	    } 
	    else 	        
	        return "";	    
	}
	
	public void actualizarDatosActivos() {				
		HashMap<String, ActivoCripto> mapeadoActivoCripto = FuncionesDeLaAplicacion.mapearActivosCripto(GestorDeDatosDeLaAplicacion.getUsuarioConectado());
		HashMap<String, ActivoFIAT> mapeadoActivoFIAT = FuncionesDeLaAplicacion.mapearActivosFIAT(GestorDeDatosDeLaAplicacion.getUsuarioConectado());
		
		for(int i = 0; i < modelo.getRowCount(); i++) {
			String nomenclatura = obtenerNomenclatura((String)modelo.getValueAt(i, 1));						
			
			if( (modelo.getValueAt(i, 2)).toString().equals("-")) {
				ActivoFIAT af = mapeadoActivoFIAT.get(nomenclatura);
				modelo.setValueAt(af.getCantidad(), i, 3); 	// Cantidad				
				modelo.setValueAt(af.getCantidad()*af.getFIAT().getValorDolar(),  i, 4); 		// Monto En dolar	
			}
			else {
				ActivoCripto ac = mapeadoActivoCripto.get(nomenclatura);
				modelo.setValueAt(ac.getCriptomoneda().getVolatilidad(), i, 2); 				// Volatilidad
				modelo.setValueAt(ac.getCantidad(), i, 3); 										// Cantidad
				modelo.setValueAt(ac.getCantidad()*ac.getCriptomoneda().getValorDolar(),  i, 4);// Monto En dolar	
			}	
		}
	}		

	public void nuevosActivos() {
		
		imagenesEscaladas.clear();		
		
		tabla.setRowSorter(null);
		modelo.setRowCount(0);
		TableRowSorter<ModeloTabla> sorter = new TableRowSorter<>(modelo);
		tabla.setRowSorter(sorter); 		
		
		tabla.removeAll();
		
		List<ActivoCripto> activosCripto = FuncionesDeLaAplicacion
				.listarActivosCripto(GestorDeDatosDeLaAplicacion.getUsuarioConectado());

		List<ActivoFIAT> activosFIAT = FuncionesDeLaAplicacion
				.listarActivosFIAT(GestorDeDatosDeLaAplicacion.getUsuarioConectado());

		HashMap<String, ImageIcon> auxImagenes = FuncionesRecursosPrograma.obtenerIconosDeCriptomonedas();
		
		DecimalFormat formato = new DecimalFormat("#.##########");
		
						

		for (ActivoCripto activoCripto : activosCripto) {
	
			Criptomoneda criptoAux = activoCripto.getCriptomoneda();				
			ImageIcon icon = CalculosGenerales.escalarImagenAlto(auxImagenes.get(criptoAux.getNomenclatura()),
						50);
			

			imagenesEscaladas.put(activoCripto.getMoneda().getNomenclatura(), icon);

			Object[] dato = { icon, criptoAux.getNombre()+"("+criptoAux.getNomenclatura()+")", criptoAux.getVolatilidad(),
						formato.format(activoCripto.getCantidad()), formato.format(activoCripto.getCantidad()*criptoAux.getValorDolar()) };						

			modelo.addRow(dato);													
		}
			
		auxImagenes = FuncionesRecursosPrograma.obtenerIconosDeFIATS();

		for (ActivoFIAT activoFIAT : activosFIAT) {
			FIAT fiatAux = activoFIAT.getFIAT();
			ImageIcon icon = CalculosGenerales.escalarImagenAlto(auxImagenes.get(fiatAux.getNomenclatura()),
					50);

			imagenesEscaladas.put(activoFIAT.getMoneda().getNomenclatura(), icon);

			Object[] dato = { icon, fiatAux.getNombre()+"("+fiatAux.getNomenclatura()+")", "-",
					formato.format(activoFIAT.getCantidad()), formato.format(activoFIAT.getCantidad()*fiatAux.getValorDolar()) };

			modelo.addRow(dato);
				
		}							
								
			etiquetaBalance.setText("Balance: " + formato.format(FuncionesDeLaAplicacion
					.calcularBalanceEnDolaresDeUsuario(GestorDeDatosDeLaAplicacion.getUsuarioConectado())) + "USD");													
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
	 * @return the etiquetaBalance
	 */
	public JLabel getEtiquetaBalance() {
		return etiquetaBalance;
	}

	/**
	 * @param etiquetaBalance the etiquetaBalance to set
	 */
	public void setEtiquetaBalance(JLabel etiquetaBalance) {
		this.etiquetaBalance = etiquetaBalance;
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
	 * @return the botonExportarCVS
	 */
	public JButton getBotonExportarCVS() {
		return botonExportarCVS;
	}

	/**
	 * @param botonExportarCVS the botonExportarCVS to set
	 */
	public void setBotonExportarCVS(JButton botonExportarCVS) {
		this.botonExportarCVS = botonExportarCVS;
	}

	/**
	 * @return the titulos
	 */
	public String[] getTitulos() {
		return titulos;
	}

	/**
	 * @param titulos the titulos to set
	 */
	public void setTitulos(String[] titulos) {
		this.titulos = titulos;
	}

	/**
	 * @return the modelo
	 */
	public ModeloTabla getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(ModeloTabla modelo) {
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
	 * @return the scrollPaneActivos
	 */
	public JScrollPane getScrollPaneActivos() {
		return scrollPaneActivos;
	}

	/**
	 * @param scrollPaneActivos the scrollPaneActivos to set
	 */
	public void setScrollPaneActivos(JScrollPane scrollPaneActivos) {
		this.scrollPaneActivos = scrollPaneActivos;
	}

	/**
	 * @return the encabezado
	 */
	public static JPanel getEncabezado() {
		return encabezado;
	}

	/**
	 * @param encabezado the encabezado to set
	 */
	public static void setEncabezado(JPanel encabezado) {
		VisualizarActivosVista.encabezado = encabezado;
	}

}
