package daos;

import java.sql.SQLException;


public interface FIATDAO<FIAT> {

	public abstract void insertarFIAT(FIAT f) throws SQLException;

	public abstract void actualizarFIATConID(FIAT f) throws SQLException; // Hacer con id

	public abstract void actualizarFIAT(FIAT f) throws SQLException; // Hacer con nomenclatura

	public abstract FIAT buscarFIAT(int idFIAT) throws SQLException;

	public abstract FIAT buscarFIAT(String nomenclatura) throws SQLException;
}
