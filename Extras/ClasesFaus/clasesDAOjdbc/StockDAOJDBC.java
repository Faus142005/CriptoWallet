package clasesDAOjdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import clases.Stock;
import interfacesDAO.StockDAO;

public class StockDAOJDBC implements StockDAO<Stock> {

	@Override
	public void insertarStock(DataSource data, Stock s) throws SQLException {
		String sql = "INSERT INTO STOCK (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";
		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, s.getMoneda().getNomenclatura());
		pstmt.setDouble(2, s.getCantidadActual());
		pstmt.executeUpdate();
	}

	@Override
	public void actualizarStock(DataSource data, Stock s) throws SQLException {
		String sql = "UPDATE STOCK SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setDouble(1, s.getCantidadActual());
		pstmt.setString(2, s.getMoneda().getNomenclatura());
		pstmt.executeUpdate();
	}

	@Override
	public Stock buscarStock(DataSource data, String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM STOCK WHERE NOMENCLATURA = ?";
		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, nomenclatura);
		ResultSet rs = pstmt.executeQuery();
		Stock s = null;
		if (rs.next())
			s = new Stock();

		return s;
	}

	@Override
	public void eliminarStock(DataSource data, String nomenclatura) throws SQLException {
		String sql = "DELETE FROM STOCK WHERE NOMENCLATURA = ?";
		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, nomenclatura);
		pstmt.executeUpdate();
	}
	
	public List <Stock> listarStock(DataSource data) throws SQLException{
		List <Stock> l = new LinkedList<Stock>();
	
		String sql = "SELECT * FROM STOCK";
		Statement stmt = data.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String nomenclatura = rs.getString("NOMENCLATURA");
			double valorDolar = rs.getDouble("CANTIDAD");
			
			//Que hacer aca??
			//Crear una moneda vacia solo con nomenclatura?
		}

		return l;
	}
}