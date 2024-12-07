package daosJDBC;

import java.sql.SQLException;
import java.util.List;

import clases.Stock;
import daos.StockDAO;

public class StockDAOJDBC implements StockDAO<Stock>{

	@Override
	public void insertarStock(Stock s) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarStock(Stock s) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stock buscarStock(String nomenclatura) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarStock(String nomenclatura) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stock> listarStock() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generarStock() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
