package clasesDAOjdbc;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.DataSource;

import clases.Moneda;
import interfacesDAO.MonedaDAO;

public class MonedaDAOjdbc implements MonedaDAO<Moneda> {
	Scanner in=new Scanner (System.in); 	
	
	@Override
	//Para las criptomonedas
	public void insertarMoneda(DataSource connection,String nombre,String nomenclatura,double precio,double volatilidad) throws SQLException {
		
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, PRECIO, VOLATILIDAD) "
				+ "VALUES (Cripto, "+nombre+", "+nomenclatura+", "+precio+", "+volatilidad+")";
		
		try(Statement stmt=connection.getConnection().createStatement()){			
			stmt.executeUpdate(sql);
			System.out.println("Moneda "+nombre+" insertada exitosamente en la base de datos.");				
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }					
	}
	
	@Override
	//Para las FIAT, volatilidad=0, no tienen stock
	public void insertarMoneda(DataSource connection,String nombre,String nomenclatura,double precio) throws SQLException {
		
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, PRECIO, VOLATILIDAD) "
				+ "VALUES (FIAT, "+nombre+", "+nomenclatura+", "+precio+", "+0+")";
		
		try(Statement stmt=connection.getConnection().createStatement()){			
			stmt.executeUpdate(sql);
			System.out.println("Moneda "+nombre+" insertada exitosamente en la base de datos.");				
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }					
	}		
		
	//imprimir en el main, recuperar acá
	
}
