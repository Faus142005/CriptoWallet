package estilos;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel{

	public ModeloTabla(final Object [][] datos, final String[] titulos) {
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
