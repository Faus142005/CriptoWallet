package aplicacion;

import java.sql.Connection;
import java.sql.SQLException;

import funcionesAplicacion.FuncionesDeConexionCriptomonedas;
import funcionesAplicacion.FuncionesDeCreacionDeMonedasYStock;
import funcionesAplicacion.FuncionesRecursosPrograma;
import ventana.VistaMain;
import ventana.PRUEBALOCA.Prueba;

public final class ClasePrincipal {

	public static void main(String[] args) {
		

		try (Connection con = MiDataSource.getDataSource().getConnection()) {
			
			CrearBaseDeDatos.creaciónDeTablasEnBD(con);			
			
			FuncionesDeCreacionDeMonedasYStock.crearMonedas();			
			FuncionesRecursosPrograma.cargarImagenesCriptomonedas();
						
		} catch (SQLException e) {
			System.out.println("Error! " + e.getMessage());
			return;
		}
		
		
		//Prueba p = new Prueba();		

		VistaMain vistaInicio = new VistaMain();		
		
	}
}