package aplicacion;

import java.sql.Connection;
import java.sql.SQLException;

import ventana.VistaMain;

public final class ClasePrincipal {

	public static void main(String[] args) {

		try (Connection con = MiDataSource.getDataSource().getConnection()) {
			
			CrearBaseDeDatos.creaciónDeTablasEnBD(con);			
			
			FuncionesDeLaAplicacion.cargarImagenes();
						
		} catch (SQLException e) {
			System.out.println("Error! " + e.getMessage());
			return;
		}

		VistaMain vistaInicio = new VistaMain();
		System.out.println("Bien");
	}
}