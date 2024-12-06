package daos;

import java.sql.SQLException;
import java.util.List;

import clases.Criptomoneda;
import clases.FIAT;


public interface MonedaDAO<Moneda> {

	public abstract int insertarFIAT(FIAT f) throws SQLException;
	
	public abstract void actualizarFIATConID(FIAT f) throws SQLException; // Hacer con id
	public abstract void actualizarFIAT(FIAT f) throws SQLException; // Hacer con nomenclatura
	
	public abstract FIAT buscarFIAT(int idFIAT) throws SQLException;
	public abstract FIAT buscarFIAT(String nomenclatura) throws SQLException;
	
	///////////////////////////////////////
	
	public abstract int insertarCriptomoneda(Criptomoneda c) throws SQLException;
	
	public abstract void actualizarCriptomonedaConID(Criptomoneda c) throws SQLException; //Hacer con id
	public abstract void actualizarCriptomoneda(Criptomoneda c) throws SQLException; //Hacer con nomenclatura
	
	public abstract Criptomoneda buscarCriptomoneda(int idCriptomoneda) throws SQLException;
	public abstract Criptomoneda buscarCriptomoneda(String nomenclatura) throws SQLException;	
	
	///////////////////////////////////////
	
	public abstract Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException;
	
	public abstract void eliminarMoneda(int idMoneda) throws SQLException;
	public abstract List<Moneda> listarMonedas() throws SQLException;
}
