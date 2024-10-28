package clasesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;import java.util.List;import java.util.ArrayList;
import java.util.Collections;

import clases.Moneda;
import comparators.*;

public class MonedaDAOjdbc implements MonedaDAO<Moneda> {
	Scanner in=new Scanner (System.in); 	
	
	@Override
	//Para las criptomonedas
	public void insertarMoneda(Connection connection,String nombre,String nomenclatura,double precio,double volatilidad) throws SQLException {
		
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, PRECIO, VOLATILIDAD) "
				+ "VALUES (Cripto, "+nombre+", "+nomenclatura+", "+precio+", "+volatilidad+")";
		
		try(Statement stmt=connection.createStatement()){			
			stmt.executeUpdate(sql);
			System.out.println("Moneda "+nombre+" insertada exitosamente en la base de datos.");				
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }					
	}
	
	@Override
	//Para las FIAT, volatilidad=0, no tienen stock
	public void insertarMoneda(Connection connection,String nombre,String nomenclatura,double precio) throws SQLException {
		
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, PRECIO, VOLATILIDAD) "
				+ "VALUES (FIAT, "+nombre+", "+nomenclatura+", "+precio+", "+0+")";
		
		try(Statement stmt=connection.createStatement()){			
			stmt.executeUpdate(sql);
			System.out.println("Moneda "+nombre+" insertada exitosamente en la base de datos.");				
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }					
	}		

	@Override	
	public void listarMonedas(Connection connection) throws SQLException {
		List<Moneda> listaDeMonedas=crearListaMonedas(connection);
		
		System.out.println("Critero de Orden para listar Monedas:\n 1) Por precio en dólar\n2) Por nomenclatura");						
		int opcion;		
		while ((opcion = in.nextInt()) != 0) {									
			
			switch (opcion) {
			case 1:
				listarMonedasPrecioEnDolar(connection,listaDeMonedas);
				break;

			case 2:
				listarMonedasNomenclatura(connection,listaDeMonedas);
				break;
			default:
				System.out.println("Opción inválida, elija nuevamente");				
				break;
			}
		}
	}
	
	private List<Moneda> crearListaMonedas(Connection connection) throws SQLException {
		List<Moneda> listaDeMonedas = new ArrayList<>();		
		
		Statement stmt = connection.createStatement();
		ResultSet resul=stmt.executeQuery("SELECT * FROM moneda");
			
		while(resul.next()) {
			Moneda moneda = new Moneda();
			moneda.setNomenclatura(resul.getString("NOMENCLATURA"));
            moneda.setNombre(resul.getString("NOMBRE"));
            moneda.setPrecio(resul.getDouble("VALOR_DOLAR"));
            moneda.setVolatilidad(resul.getDouble("VOLATILIDAD"));            
            listaDeMonedas.add(moneda);
		}				
		
		resul.close();
		stmt.close();
		
		return listaDeMonedas;
	}
	
	private void listarMonedasPrecioEnDolar(Connection connection,List<Moneda> listaDeMonedas) throws SQLException {				
		Collections.sort(listaDeMonedas, new ComparadorPrecioEnDolar());
		
		for (Moneda moneda : listaDeMonedas) {
		    System.out.println("Nombre: "+ moneda.getNombre()+"\nNomenclatura: "+moneda.getNomenclatura()+"\nPrecio en Dólares: "
		    		+moneda.getPrecio()+"\nVolatilidad: "+ moneda.getVolatilidad());
		}											
	}	
	
	private void listarMonedasNomenclatura(Connection connection,List<Moneda> listaDeMonedas) throws SQLException {				
		Collections.sort(listaDeMonedas, new ComparadorNomenclatura());
		
		for (Moneda moneda : listaDeMonedas) {
		    System.out.println("Nombre: "+ moneda.getNombre()+"\nNomenclatura: "+moneda.getNomenclatura()+"\nPrecio en Dólares: "
		    		+moneda.getPrecio()+"\nVolatilidad: "+ moneda.getVolatilidad());
		}										
	}	
		
	
}
