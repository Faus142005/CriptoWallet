package clasesDAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import aplicacion.MiDataSource;
import clases.Moneda;
import clases.Stock;
import interfacesDAO.StockDAO;

public class StockDAOJDBC implements StockDAO<Stock> {

	private static final double MAX_CANTIDAD = 1000.0; // cantidad máxima de criptos a generar

	@Override
	public void insertarStock(Stock s) throws SQLException {
		String sql = "INSERT INTO STOCK (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, s.getMoneda().getNomenclatura());
			pstmt.setDouble(2, s.getCantidadActual());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void actualizarStock(Stock s) throws SQLException {
		String sql = "UPDATE STOCK SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setDouble(1, s.getCantidadActual());
			pstmt.setString(2, s.getMoneda().getNomenclatura());
			pstmt.executeUpdate();
		}
	}

	@Override
	public Stock buscarStock(String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM STOCK WHERE NOMENCLATURA = ?";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			try (ResultSet rs = pstmt.executeQuery()) {
				Stock s = null;
				if (rs.next()) {
					s = new Stock(rs.getDouble("CANTIDAD"), new Moneda(rs.getString("NOMENCLATURA")));
				}

				return s;
			}
		}
	}

	@Override
	public void eliminarStock(String nomenclatura) throws SQLException {
		String sql = "DELETE FROM STOCK WHERE NOMENCLATURA = ?";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			pstmt.executeUpdate();
		}
	}

	@Override
	public List<Stock> listarStock() throws SQLException {
		List<Stock> l = new LinkedList<Stock>();

		String sql = "SELECT * FROM STOCK";
		try (Connection connection = MiDataSource.getDataSource().getConnection();Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String nomenclatura = rs.getString("NOMENCLATURA");
				double cantidad = rs.getDouble("CANTIDAD");

				Stock s = new Stock(cantidad, new Moneda(nomenclatura));

				l.add(s);
			}

			return l;
		}
	}

	@Override
	public void generarStock() throws SQLException {
	    String sql = "SELECT NOMENCLATURA, CANTIDAD FROM STOCK";
	    String sqlUpdate = "UPDATE STOCK SET CANTIDAD = ? WHERE NOMENCLATURA = ?";

	    try (Connection c = MiDataSource.getDataSource().getConnection();
	         PreparedStatement selectStmt = c.prepareStatement(sql);
	         PreparedStatement updateStmt = c.prepareStatement(sqlUpdate)) {

	        Random random = new Random();
	        try (ResultSet resul = selectStmt.executeQuery()) {
	            while (resul.next()) {
	                String nomenclatura = resul.getString("NOMENCLATURA");
	                double cantidadActual = resul.getDouble("CANTIDAD");

	                double stockAleatorio = 0.01 + (MAX_CANTIDAD - 0.01) * random.nextDouble() + cantidadActual;

	                updateStmt.setDouble(1, stockAleatorio);
	                updateStmt.setString(2, nomenclatura);
	                updateStmt.executeUpdate();
	            }
	        }
	    }
	}

}