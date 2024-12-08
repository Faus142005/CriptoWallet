package daos;

import java.sql.SQLException;
import java.util.List;

import clases.Criptomoneda;
import clases.FIAT;


public interface MonedaDAO<Moneda> {

	public abstract int insertarFIAT(FIAT f) throws SQLException;
	
	public abstract void actualizarFIATConID(FIAT f) throws SQLException; // actualiza FIAT con id
	public abstract void actualizarFIAT(FIAT f) throws SQLException; // actualiza FIAT con nomenclatura
	
	public abstract FIAT buscarFIAT(int idFIAT) throws SQLException; //busca FIAT con id
	public abstract FIAT buscarFIAT(String nomenclatura) throws SQLException; //busca FIAT con nomenclatura
	
	public abstract List<FIAT> listarFIATS() throws SQLException;
	
	///////////////////////////////////////
	
	public abstract int insertarCriptomoneda(Criptomoneda c) throws SQLException;
	
	public abstract void actualizarCriptomonedaConID(Criptomoneda c) throws SQLException; //actualiza Cripto con id
	public abstract void actualizarCriptomoneda(Criptomoneda c) throws SQLException; //actualiza Cripto con nomenclatura
	
	public abstract Criptomoneda buscarCriptomoneda(int idCriptomoneda) throws SQLException;  //busca Cripto con id
	public abstract Criptomoneda buscarCriptomoneda(String nomenclatura) throws SQLException;	//busca Cripto con nomenclatura
	public abstract List<Criptomoneda> listarCriptomonedas() throws SQLException;
	
	///////////////////////////////////////
	
	public abstract Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException; //busca moneda con nomenclatura
	public abstract Moneda buscarMonedaPorID(int idMoneda) throws SQLException; //busca moneda con id
	
	public abstract void eliminarMonedaPorNomenclatura(String nomenclatura) throws SQLException; //elimina moneda por nomenclatura
	public abstract void eliminarMonedaPorID(int idMoneda) throws SQLException; //elimina moneda por ID
	
	public abstract List<Moneda> listarMonedas() throws SQLException;
}
