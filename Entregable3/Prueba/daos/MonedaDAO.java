package daos;

import java.sql.SQLException;
import java.util.List;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;


public interface MonedaDAO {

	public abstract void insertarFIAT(FIAT f) throws SQLException;
	
	public abstract void actualizarFIATConID(FIAT f) throws SQLException; // Hacer con id
	public abstract void actualizarFIAT(FIAT f) throws SQLException; // Hacer con nomenclatura
	
	public abstract FIAT buscarFIAT(int idFIAT) throws SQLException;
	public abstract FIAT buscarFIAT(String nomenclatura) throws SQLException;
	
	///////////////////////////////////////
	
	
	public abstract void insertarCriptomoneda(Criptomoneda c) throws SQLException;
	
	public abstract void actualizarCriptomonedaConID(Criptomoneda c) throws SQLException; //Hacer con id
	public abstract void actualizarCriptomoneda(Criptomoneda c) throws SQLException; //Hacer con nomenclatura
	
	public abstract Criptomoneda buscarCriptomoneda(int idCriptomoneda) throws SQLException;
	public abstract Criptomoneda buscarCriptomoneda(String nomenclatura) throws SQLException;	
	
	public abstract char buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException;
	
	public abstract void eliminarMoneda(String nomenclatura) throws SQLException;
	public abstract List<Moneda> listarMonedas() throws SQLException;
}
