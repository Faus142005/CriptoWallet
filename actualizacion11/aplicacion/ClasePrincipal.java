package aplicacion;

import java.sql.Connection;
import java.sql.SQLException;

import funcionesAplicacion.FuncionesDeCreacionDeMonedasYStock;
import funcionesAplicacion.FuncionesRecursosPrograma;
import ventana.VistaMain;

public final class ClasePrincipal {

	public static void main(String[] args) {
		

		try (Connection con = MiDataSource.getDataSource().getConnection()) {
			
			CrearBaseDeDatos.creaciónDeTablasEnBD(con);			
			
			FuncionesDeCreacionDeMonedasYStock.crearMonedas();			
			FuncionesRecursosPrograma.cargarImagenesCriptomonedas();
			FuncionesRecursosPrograma.cargarImagenesFIAT();
						
		} catch (SQLException e) {
			System.err.println("Error! " + e.getMessage());
			return;
		}
		
		
		//Prueba p = new Prueba();		

		VistaMain vistaInicio = new VistaMain();		
		
	}
}