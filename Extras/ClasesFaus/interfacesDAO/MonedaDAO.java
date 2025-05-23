package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Stock;

public interface MonedaDAO {

	public abstract void insertarFIAT(DataSource data,FIAT c) throws SQLException;
	public abstract void actualizarFIAT(DataSource data, FIAT c) throws SQLException;
	public abstract FIAT buscarFIAT(DataSource data, String nomenclatura) throws SQLException;
	
	public abstract void insertarCriptomoneda(DataSource data, Criptomoneda c) throws SQLException;
	public abstract void actualizarCriptomoneda(DataSource data,Criptomoneda c) throws SQLException;
	public abstract Criptomoneda buscarCriptomoneda(DataSource data, String nomenclatura) throws SQLException;
	
	public abstract void eliminarMoneda(DataSource data, String nomenclatura) throws SQLException;
	public abstract List<Moneda> listarMonedas(DataSource data) throws SQLException;
}
