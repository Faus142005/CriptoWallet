package clasesDAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import clases.Criptomoneda;
import clases.Stock;
import interfacesDAO.CriptomonedaDAO;


//NO IMPORTA, FUE FUSIONADO TODO EN MONEDA DAOJDBC

public class CriptomonedaDAOJDBC implements CriptomonedaDAO<Criptomoneda> {

	@Override
	public void insertarCriptomoneda(DataSource data, Criptomoneda c, Stock stock) throws SQLException {
		String sql = "INSERT INTO MONEDA_CRIPTO (NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = data.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, c.getNombre());
			pstmt.setString(2, c.getNomenclatura());
			pstmt.setDouble(3, c.getPrecio());
			pstmt.setDouble(4, c.getVolatilidad());
			pstmt.setDouble(5, stock.getCantidadActual());

			pstmt.executeUpdate();
		}
	}

	@Override
	public void actualizarCriptomoneda(DataSource data, Criptomoneda c, Stock stock) throws SQLException {
		String sql = "UPDATE MONEDA_CRIPTO SET NOMBRE = ?, VALOR_DOLAR = ?, VOLATILIDAD = ?, STOCK = ? WHERE NOMENCLATURA = ?";

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);

		pstmt.setString(1, c.getNombre());
		pstmt.setDouble(2, c.getPrecio());
		pstmt.setDouble(3, c.getVolatilidad());
		pstmt.setDouble(4, stock.getCantidadActual());
		pstmt.setString(5, c.getNomenclatura());

		pstmt.executeUpdate();
	}

	@Override
	public void eliminarCriptomoneda(DataSource data, String nomenclatura) throws SQLException {
		String sql = "DELETE FROM MONEDA_CRIPTO WHERE NOMENCLATURA = ?";

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);

		pstmt.setString(1, nomenclatura);
	}

	@Override
	public Criptomoneda buscarCriptomoneda(DataSource data, String nomenclatura) throws SQLException {
		String sql = "SELECT NOMBRE, VALOR_DOLAR, VOLATILIDAD, STOCK FROM MONEDA_CRIPTO WHERE NOMENCLATURA = ?";
		Criptomoneda criptomoneda = null;

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);

		pstmt.setString(1, nomenclatura);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			criptomoneda = new Criptomoneda(rs.getString("NOMBRE"), nomenclatura, rs.getDouble("VALOR_DOLAR"),
					rs.getDouble("VOLATILIDAD"));
		} 

		return criptomoneda;
	}
}
