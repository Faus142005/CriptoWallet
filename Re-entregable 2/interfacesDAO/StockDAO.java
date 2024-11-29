package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

public interface StockDAO <Stock>{

	public abstract void insertarStock(Stock s) throws SQLException;
	public abstract void actualizarStock(Stock s) throws SQLException;
	public abstract Stock buscarStock(String nomenclatura) throws SQLException;
	public abstract void eliminarStock(String nomenclatura)throws SQLException;
	public abstract List <Stock> listarStock()throws SQLException;	
	public abstract void generarStock()throws SQLException;
}