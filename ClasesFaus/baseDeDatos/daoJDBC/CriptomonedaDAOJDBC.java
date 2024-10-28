package baseDeDatos.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import baseDeDatos.MyConnection;
import clases.Criptomoneda;

public class CriptomonedaDAOJDBC {

	public static void insertarCriptomoneda(Criptomoneda c) throws SQLException {

		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK) VALUES (?, ?, ?, ?, '0', '0')";

		Connection connection = MyConnection.getConnection();

		PreparedStatement pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, "C"); // Tipo 'C' para moneda Criptomoneda
		pstmt.setString(2, c.getNombre());
		pstmt.setString(3, c.getAbreviatura());
		pstmt.setDouble(4, c.getPrecio());

		pstmt.executeUpdate();

		System.out.println("Criptomoneda creada exitosamente!");

	}
}
