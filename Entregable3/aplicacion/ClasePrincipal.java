package aplicacion;

import java.sql.Connection;
import java.sql.SQLException;

import clases.Usuario;
import ventana.VistaMain;

public final class ClasePrincipal {

	public static void main(String[] args) {

		try (Connection con = MyDataSource.getDataSource().getConnection()) {
			// CrearBaseDeDatos.creaciónDeTablasEnBD(con);
		} catch (SQLException e) {
			System.out.println("Error!" + e.getMessage());
			return;
		}

		try {

			//Usuario usr = GestorDAOS.getUsuarioDAO().buscarUsuario(1);
			//System.out.println(usr.getContraseña());
		} catch (Exception e) {

		}

		VistaMain vistaInicio = new VistaMain();
		System.out.println("A");
	}
}