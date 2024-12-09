package ventana.cotizacionesCriptomonedas;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaCotizaciones extends DefaultTableModel{

	public ModeloTablaCotizaciones(final Object [][] datos, final String[] titulos) {
		super(datos, titulos);
	}
	
	 @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	
	public Class getColumnClass(final int column) {
		return this.getValueAt(0, column).getClass();
	}
}
