package clasesDAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import aplicacion.MiDataSource;
import clases.Activo;
import clases.Moneda;
import interfacesDAO.Activo_CriptoDAO;

public class Activo_CriptoDAOJDBC implements Activo_CriptoDAO<Activo> {
	
	@Override
	public void insertarActivoCripto(Activo a) throws SQLException {
		String sql = "INSERT INTO ACTIVO_CRIPTO (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, a.getMoneda().getNomenclatura());
			pstmt.setDouble(2, a.getCantidad());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void actualizarActivoCripto(Activo a) throws SQLException {
		String sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setDouble(1, a.getCantidad());
			pstmt.setString(2, a.getMoneda().getNomenclatura());
			pstmt.executeUpdate();
		}
	}

	@Override
	public Activo buscarActivoCripto(String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			ResultSet rs = pstmt.executeQuery();
			Activo a = null;
			if (rs.next()) {
				a = new Activo(rs.getDouble("CANTIDAD"), new Moneda(rs.getString("NOMENCLATURA")));
			}
			return a;
		}
	}

	@Override
	public void eliminarActivoCripto(String nomenclatura) throws SQLException {
		String sql = "DELETE FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			pstmt.executeUpdate();
		}
	}

	@Override
	public List<Activo> listarActivoCripto() throws SQLException {
		List<Activo> l = new LinkedList<Activo>();

		String sql = "SELECT * FROM ACTIVO_CRIPTO";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String nomenclatura = rs.getString("NOMENCLATURA");
				double cantidad = rs.getDouble("CANTIDAD");

				Activo a = new Activo(cantidad, new Moneda(nomenclatura));

				l.add(a);
			}

			return l;
		}
	}

}
