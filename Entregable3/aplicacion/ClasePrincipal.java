package aplicacion;

import java.sql.Connection;
import java.sql.SQLException;

import ventana.VistaMain;

public final class ClasePrincipal {

	public static void main(String[] args) {

		try (Connection con = MyDataSource.getDataSource().getConnection()) {
			//CrearBaseDeDatos.creaciónDeTablasEnBD(con);
		} catch (SQLException e) {
			System.out.println("Error!" + e.getMessage());
			return;
		}

		VistaMain vistaInicio = new VistaMain();
		System.out.println("A");
	}
}