package interfacesDAO;

import java.sql.SQLException;

import javax.sql.DataSource;

import clases.Criptomoneda;
import clases.Stock;

//NO IMPORTA, FUE FUSIONADO TODO EN MONEDA DAO

public interface CriptomonedaDAO {
	
	public abstract void insertarCriptomoneda(DataSource data, Criptomoneda c, Stock stock) throws SQLException;
	public abstract void actualizarCriptomoneda(DataSource data,Criptomoneda c, Stock stock) throws SQLException;
	public abstract void eliminarCriptomoneda(DataSource data, String nomenclatura) throws SQLException;
	public abstract Criptomoneda buscarCriptomoneda(DataSource data, String nomenclatura) throws SQLException;
}
