package interfacesDAO;

import java.sql.SQLException;

import javax.sql.DataSource;

import clases.Stock;

//NO IMPORTA, FUE FUSIONADO TODO EN MONEDA DAO

public interface CriptomonedaDAO<Criptomoneda> {
	
	public abstract void insertarCriptomoneda(Criptomoneda c, Stock stock) throws SQLException;
	public abstract void actualizarCriptomoneda(Criptomoneda c, Stock stock) throws SQLException;
	public abstract void eliminarCriptomoneda(String nomenclatura) throws SQLException;
	public abstract Criptomoneda buscarCriptomoneda(String nomenclatura) throws SQLException;
}
