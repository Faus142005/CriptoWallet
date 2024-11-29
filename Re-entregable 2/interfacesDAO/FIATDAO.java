package interfacesDAO;

import java.sql.SQLException;

import javax.sql.DataSource;

import clases.Stock;

//NO IMPORTA, FUE FUSIONADO TODO EN MONEDA DAO

public interface FIATDAO<FIAT> {

	public abstract void insertarFIAT(FIAT c, Stock stock) throws SQLException;
	public abstract void actualizarFIAT(FIAT c, Stock stock) throws SQLException;
	public abstract void eliminarFIAT(String nomenclatura) throws SQLException;
	public abstract FIAT buscarFIAT(String nomenclatura) throws SQLException;
}
