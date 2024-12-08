package daos;

import java.sql.SQLException;
import java.util.List;

public interface StockDAO <Stock>{

	public abstract int insertarStock(Stock s) throws SQLException;
	
	public abstract void actualizarStock(Stock s) throws SQLException; //actualiza stock por ID
	
	public abstract Stock buscarStock(String nomenclatura) throws SQLException;
	
	public abstract Stock buscarStockporID(int idStock) throws SQLException;
	
	public abstract void eliminarStock(String nomenclatura)throws SQLException;
	
	public abstract void eliminarStockporID(int idStock)throws SQLException;
	
	public abstract List <Stock> listarStock()throws SQLException;	
	
}
