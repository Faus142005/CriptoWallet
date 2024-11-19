package clasesDAOjdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import clases.Moneda;
import clases.Stock;
import interfacesDAO.StockDAO;

public class StockDAOJDBC implements StockDAO<Stock> {
	
	private static final double MAX_CANTIDAD = 1000.0; // cantidad máxima de criptos a generar

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
	
	@Override
	public List <Stock> listarStock(DataSource data) throws SQLException{
		List <Stock> l = new LinkedList<Stock>();
	
		String sql = "SELECT * FROM STOCK";
		Statement stmt = data.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String nomenclatura = rs.getString("NOMENCLATURA");
			double cantidad = rs.getDouble("CANTIDAD");
			
			Stock s=new Stock(cantidad,new Moneda(nomenclatura));			
			
			l.add(s);			
		}	

		return l;
	}

	@Override
	public void generarStock(DataSource data) throws SQLException {
		String sql = "SELECT nomenclatura FROM Criptomonedas";
		String sqlUpdate = "UPDATE Stocks SET cantidad = ? WHERE nomenclatura = ?";

		PreparedStatement selectStmt = data.getConnection().prepareStatement(sql);
		PreparedStatement updateStmt = data.getConnection().prepareStatement(sqlUpdate);

		ResultSet resul = selectStmt.executeQuery();

		Random random = new Random();
		while (resul.next()) {
			String nomenclatura = resul.getString("nomenclatura");
			double stockAleatorio = 0.01 + (MAX_CANTIDAD - 0.01) * random.nextDouble(); // genera un valor aleatorio 0.01 y 1000
			updateStmt.setDouble(1, stockAleatorio);
			updateStmt.setString(2, nomenclatura);
			updateStmt.executeUpdate();
		}				
	}
	
}