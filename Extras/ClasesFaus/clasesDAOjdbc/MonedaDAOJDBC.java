package clasesDAOjdbc;

import java.sql.Connection;
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
import interfacesDAO.MonedaDAO;

public class MonedaDAOJDBC implements MonedaDAO {

	@Override
	public void insertarFIAT(DataSource data, FIAT f) throws SQLException {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR) VALUES (?, ?, ?, ?)";

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, "F"); // Tipo para FIAT
		pstmt.setString(2, f.getNombre());
		pstmt.setString(3, f.getNomenclatura());
		pstmt.setDouble(4, f.getPrecio());

		pstmt.executeUpdate();
	}

	@Override
	public void actualizarFIAT(DataSource data, FIAT c) throws SQLException {
		String sql = "UPDATE MONEDA SET NOMBRE = ?, VALOR_DOLAR = ? WHERE NOMENCLATURA = ?";

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, c.getNombre());
		pstmt.setDouble(2, c.getPrecio());
		pstmt.setString(3, c.getNomenclatura());
		pstmt.executeUpdate();
	}

	@Override
	public FIAT buscarFIAT(DataSource data, String nomenclatura) throws SQLException {
		String sql = "SELECT NOMBRE, NOMENCLATURA, VALOR_DOLAR FROM MONEDA WHERE NOMENCLATURA = ? AND TIPO = ?";
		FIAT f = null;

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, nomenclatura);
		pstmt.setString(2, "F"); // Tipo para FIAT
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			String nombre = rs.getString("NOMBRE");
			String nomenclaturaDB = rs.getString("NOMENCLATURA");
			double valorDolar = rs.getDouble("VALOR_DOLAR");

			f = new FIAT(nombre, nomenclaturaDB, valorDolar);
		}

		return f;
	}

	@Override
	public void insertarCriptomoneda(DataSource data, Criptomoneda c) throws SQLException {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, "C"); // Tipo para criptomoneda
		pstmt.setString(2, c.getNombre());
		pstmt.setString(3, c.getNomenclatura());
		pstmt.setDouble(4, c.getPrecio());
		pstmt.setDouble(5, c.getVolatilidad());

		pstmt.executeUpdate();
	}

	@Override
	public void actualizarCriptomoneda(DataSource data, Criptomoneda c) throws SQLException {
		String sql = "UPDATE MONEDA SET NOMBRE = ?, VALOR_DOLAR = ?, VOLATILIDAD = ? WHERE NOMENCLATURA = ?";

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, c.getNombre());
		pstmt.setDouble(2, c.getPrecio());
		pstmt.setDouble(3, c.getVolatilidad());
		pstmt.setString(4, c.getNomenclatura());
		pstmt.executeUpdate();
	}

	@Override
	public Criptomoneda buscarCriptomoneda(DataSource data, String nomenclatura) throws SQLException {
		String sql = "SELECT NOMBRE, VALOR_DOLAR, VOLATILIDAD FROM MONEDA WHERE NOMENCLATURA = ? AND TIPO = ?";
		Criptomoneda criptomoneda = null;

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, nomenclatura);
		pstmt.setString(2, "C"); // Tipo para criptomoneda
		ResultSet rs = pstmt.executeQuery();

		if (rs.next())
			criptomoneda = new Criptomoneda(rs.getString("NOMBRE"), nomenclatura, rs.getDouble("VALOR_DOLAR"),
					rs.getDouble("VOLATILIDAD"));

		return criptomoneda;
	}

	@Override
	public void eliminarMoneda(DataSource data, String nomenclatura) throws SQLException {
		String sql = "DELETE FROM MONEDA WHERE NOMENCLATURA = ?";

		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, nomenclatura);
		pstmt.executeUpdate();
	}

	@Override
	public List<Moneda> listarMonedas(DataSource data) throws SQLException {
		String sql = "SELECT * FROM MONEDA";
		List<Moneda> l = new LinkedList<>();

		Statement stmt = data.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String tipo = rs.getString("TIPO");
			String nombre = rs.getString("NOMBRE");
			String nomenclatura = rs.getString("NOMENCLATURA");
			double valorDolar = rs.getDouble("VALOR_DOLAR");
			double volatilidad = rs.getDouble("VOLATILIDAD");

			Moneda m;

			if (tipo.equals("C"))
				m = new Criptomoneda(nombre, nomenclatura, valorDolar, volatilidad);
			else
				m = new FIAT(nombre, nomenclatura, valorDolar);

			l.add(m);
		}

		return l;
	}

}
