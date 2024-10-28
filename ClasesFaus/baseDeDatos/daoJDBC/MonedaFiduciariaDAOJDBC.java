package baseDeDatos.daoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import baseDeDatos.MyConnection;
import baseDeDatos.dao.MonedaFiduciariaDAO;
import clases.FIAT;

public class MonedaFiduciariaDAOJDBC {

	public static void insertarMonedaFiduciaria(FIAT f) throws SQLException {

		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK) VALUES (?, ?, ?, ?, '0', '0')";

		Connection connection = MyConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, "F"); // Tipo 'F' para moneda fiduciaria
		pstmt.setString(2, f.getNombre());
		pstmt.setString(3, f.getAbreviatura());
		pstmt.setDouble(4, f.getPrecio());

		pstmt.executeUpdate();
		System.out.println("Moneda fiduciaria creada exitosamente!");
	}
}
