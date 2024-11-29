package clasesDAOjdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import aplicacion.MiDataSource;
import clases.Transaccion;
import interfacesDAO.TransaccionDAO;

public class TransaccionDAOJDBC implements TransaccionDAO {

	@Override
	public void insertarTransaccion( Transaccion t) throws SQLException {
		String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA) VALUES (?, ?)";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, t.getResumen());
			pstmt.setDate(2, t.getFecha());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void insertarTransaccionSoloResumen( Transaccion t) throws SQLException {
		String sql = "INSERT INTO TRANSACCION (RESUMEN) VALUES (?)";
		try (Connection connection = MiDataSource.getDataSource().getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, t.getResumen());
			pstmt.executeUpdate();
		}
	}

	@Override
	public List<Transaccion> listarTransacciones() throws SQLException {
		List<Transaccion> l = new LinkedList<Transaccion>();

		String sql = "SELECT * FROM TRANSACCION";
		try (Connection connection = MiDataSource.getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String resumen = rs.getString("RESUMEN");
				Date fecha = rs.getDate("FECHA_HORA");

				Transaccion t = new Transaccion(resumen, fecha);

				l.add(t);
			}

			return l;
		}
	}

}
