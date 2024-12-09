package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

import clases.Criptomoneda;
import clases.FIAT;


public interface MonedaDAO<Moneda> {
	
	public enum TipoDeMoneda {NULO, CRIPTOMONEDA, FIAT};

	public abstract void insertarFIAT(FIAT c) throws SQLException;
	public abstract void actualizarFIAT(FIAT c) throws SQLException;
	public abstract FIAT buscarFIAT(String nomenclatura) throws SQLException;
	
	public abstract void insertarCriptomoneda(Criptomoneda c) throws SQLException;
	public abstract void actualizarCriptomoneda(Criptomoneda c) throws SQLException;
	public abstract Criptomoneda buscarCriptomoneda(String nomenclatura) throws SQLException;
	
	public abstract TipoDeMoneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException;
	public abstract void eliminarMoneda(String nomenclatura) throws SQLException;
	public abstract List<Moneda> listarMonedas() throws SQLException;
}
