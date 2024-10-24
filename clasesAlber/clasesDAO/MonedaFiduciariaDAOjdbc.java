package clasesDAO;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MonedaFiduciariaDAOjdbc implements MonedaFiduciariaDAO {
	
	public void insertarMonedaFiduciaria(Connection connection) throws SQLException {
		Scanner in= new Scanner (System.in);
		
		System.out.println("Ingrese el nombre de la moneda fiduciaria");
		String nombre = in.nextLine();
		
		System.out.println("Ingrese la nomenclatura de la moneda fiduciaria");
		String nomenclatura = in.nextLine();		
						
		Statement stmt = connection.createStatement();
		String query = "INSERT INTO moneda (nombre,nomenclatura) VALUES(nombre, nomenclatura)";
		int res = stmt.executeUpdate(query);
		stmt.close();			

	}
}
