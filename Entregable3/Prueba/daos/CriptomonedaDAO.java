package daos;

import java.sql.SQLException;

import clases.Criptomoneda;

public interface CriptomonedaDAO {

	public abstract void insertarCriptomoneda(Criptomoneda c) throws SQLException;

	public abstract void actualizarCriptomonedaConID(Criptomoneda c) throws SQLException; // Hacer con id

	public abstract void actualizarCriptomoneda(Criptomoneda c) throws SQLException; // Hacer con nomenclatura

	public abstract Criptomoneda buscarCriptomoneda(int idCriptomoneda) throws SQLException;

	public abstract Criptomoneda buscarCriptomoneda(String nomenclatura) throws SQLException;
}
