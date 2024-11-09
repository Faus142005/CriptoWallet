package interfacesDAO;

import java.sql.SQLException;

import javax.sql.DataSource;

import clases.FIAT;
import clases.Stock;

//NO IMPORTA, FUE FUSIONADO TODO EN MONEDA DAO

public interface FIATDAO {

	public abstract void insertarFIAT(DataSource data,FIAT c, Stock stock) throws SQLException;
	public abstract void actualizarFIAT(DataSource data, FIAT c, Stock stock) throws SQLException;
	public abstract void eliminarFIAT(DataSource data, String nomenclatura) throws SQLException;
	public abstract FIAT buscarFIAT(DataSource data, String nomenclatura) throws SQLException;
}
