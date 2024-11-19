package clasesDAOjdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import clases.Activo;
import clases.Moneda;
import interfacesDAO.Activo_FIATDAO;

public class Activo_FIATDAOJDBC implements Activo_FIATDAO<Activo> {

	@Override
	public void insertarActivoFIAT(DataSource data, Activo a) throws SQLException {
		String sql = "INSERT INTO ACTIVO_FIAT (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";
		try (PreparedStatement pstmt = data.getConnection().prepareStatement(sql)) {
			pstmt.setString(1, a.getMoneda().getNomenclatura());
			pstmt.setDouble(2, a.getCantidad());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void actualizarActivoFIAT(DataSource data, Activo a) throws SQLException {
		String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		try (PreparedStatement pstmt = data.getConnection().prepareStatement(sql)) {
			pstmt.setDouble(1, a.getCantidad());
			pstmt.setString(2, a.getMoneda().getNomenclatura());
			pstmt.executeUpdate();
		}
	}

	@Override
	public Activo buscarActivoFIAT(DataSource data, String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
		try (PreparedStatement pstmt = data.getConnection().prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			try (ResultSet rs = pstmt.executeQuery()) {
				Activo a = null;
				if (rs.next())
					a = new Activo(rs.getDouble("CANTIDAD"), new Moneda(rs.getString("NOMENCLATURA")));

				return a;
			}
		}
	}

	@Override
	public void eliminarActivoFIAT(DataSource data, String nomenclatura) throws SQLException {
		String sql = "DELETE FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
		try (PreparedStatement pstmt = data.getConnection().prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			pstmt.executeUpdate();
		}
	}

	@Override
	public List<Activo> listarActivoFIAT(DataSource data) throws SQLException {
		List<Activo> l = new LinkedList<Activo>();

		String sql = "SELECT * FROM ACTIVO_FIAT";
		try (Statement stmt = data.getConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
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
