package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public interface StockDAO <Stock>{

	public abstract void insertarStock(DataSource data, Stock s) throws SQLException;
	public abstract void actualizarStock(DataSource data,Stock s) throws SQLException;
	public abstract Stock buscarStock(DataSource data, String nomenclatura) throws SQLException;
	public abstract void eliminarStock(DataSource data, String nomenclatura)throws SQLException;
	public abstract List <Stock> listarStock(DataSource data)throws SQLException;
}