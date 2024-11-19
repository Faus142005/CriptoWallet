package clasesDAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import clases.FIAT;
import clases.Stock;
import interfacesDAO.FIATDAO;

//NO IMPORTA, FUE FUSIONADO TODO EN MONEDA DAOJDBC

public class FIATDAOJDBC implements FIATDAO<FIAT> {

	@Override
	public void insertarFIAT(DataSource data, FIAT f, Stock stock) throws SQLException {
		String sql = "INSERT INTO MONEDA_FIAT (NOMBRE, NOMENCLATURA, VALOR_DOLAR, STOCK) VALUES (?, ?, ?, ?)";

		try (Connection connection = data.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, f.getNombre());
			pstmt.setString(2, f.getNomenclatura());
			pstmt.setDouble(3, f.getPrecio());
			pstmt.setDouble(4, stock.getCantidadActual());

			pstmt.executeUpdate();
		}
	}

	@Override
	public void actualizarFIAT(DataSource data, FIAT c, Stock stock) throws SQLException {
		String sql = "UPDATE MONEDA_FIAT SET NOMBRE = ?, VALOR_DOLAR = ?, STOCK = ? WHERE NOMENCLATURA = ?";

		try (PreparedStatement pstmt = data.getConnection().prepareStatement(sql)) {

			pstmt.setString(1, c.getNombre());
			pstmt.setDouble(2, c.getPrecio());
			pstmt.setDouble(3, stock.getCantidadActual());
			pstmt.setString(4, c.getNomenclatura());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void eliminarFIAT(DataSource data, String nomenclatura) throws SQLException {
		String sql = "DELETE FROM MONEDA_FIAT WHERE NOMENCLATURA = ?";

		try (PreparedStatement pstmt = data.getConnection().prepareStatement(sql)) {

			pstmt.setString(1, nomenclatura);
			pstmt.executeUpdate();
		}
	}

	@Override
	public FIAT buscarFIAT(DataSource data, String nomenclatura) throws SQLException {
		String sql = "SELECT NOMBRE, NOMENCLATURA, VALOR_DOLAR, STOCK FROM MONEDA_FIAT WHERE NOMENCLATURA = ?";

		try (PreparedStatement pstmt = data.getConnection().prepareStatement(sql)) {
			FIAT f = null;
			pstmt.setString(1, nomenclatura);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("NOMBRE");
				String nomenclaturaDB = rs.getString("NOMENCLATURA");
				double valorDolar = rs.getDouble("VALOR_DOLAR");

				f = new FIAT(nombre, nomenclaturaDB, valorDolar);
			}

			return f;
		}
	}
}
