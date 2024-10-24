package clasesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;import java.util.List;import java.util.ArrayList;
import java.util.Collections;

import clases.Moneda;
import comparators.ComparadorNomenclatura;

public class MonedaDAOjdbc implements MonedaDAO {
	Scanner in=new Scanner (System.in);
	@Override
	public void crearMoneda(Connection connection) throws SQLException {
		
		System.out.println("Elija qué moneda quiere crear:\n" + "1) Moneda fiduciaria\n" + "2) Criptomoneda");
		boolean seleccionado = false;
		
		while(!seleccionado) {
			
			switch (in.nextInt()){
			
			case 1: 				
				MonedaFiduciariaDAOjdbc fiat = new MonedaFiduciariaDAOjdbc();
				fiat.crearMonedaFiduciaria(connection);
				seleccionado = true;
				break;
				
			case 2:		
				CriptomonedaDAOjdbc cripto = new CriptomonedaDAOjdbc();
				cripto.crearCriptomoneda(connection);
				seleccionado = true;
				break;
			}
		}
	}
	

	@Override	
	public void listarMonedas(Connection connection) throws SQLException {
		System.out.println("Critero de Orden para listar Monedas:\n 1) Por precio en dólar\n2) Por nomenclatura");						
		int opcion;
		while ((opcion = in.nextInt()) != 0) {

			switch (opcion) {
			case 1:
				listarMonedasPrecioEnDolar(connection);
				break;

			case 2:
				listarMonedasNomenclatura(connection);
				break;
			default:
				System.out.println("Opción inválida, elija nuevamente");				
				break;
			}
		}
	}
	
	private void listarMonedasPrecioEnDolar(Connection connection) throws SQLException {		
		Statement stmt = connection.createStatement();
		ResultSet resul=stmt.executeQuery("SELECT * FROM moneda ORDER BY VALOR_DOLAR ASC");
			
		while(resul.next()) {
			System.out.println("Nombre: "+resul.getString("nombre")+
							   "\nNomenclatura: "+ resul.getString("nomenclatura")+
							   "\nPrecio en Dólares: "+ resul.getString("precio")+
							   "\nVolatilidad: "+ resul.getString("volatilidad")+
							   "\nStock: "+ resul.getString("stock"));
		}
		
		stmt.close();							
	}	
	
	private void listarMonedasNomenclatura(Connection connection) throws SQLException {
		List<Moneda> listaDeMonedas = new ArrayList<>();		
		
		Statement stmt = connection.createStatement();
		ResultSet resul=stmt.executeQuery("SELECT * FROM moneda");
			
		while(resul.next()) {
			Moneda moneda = new Moneda();
			moneda.setNomenclatura(resul.getString("NOMENCLATURA"));
            moneda.setNombre(resul.getString("NOMBRE"));
            moneda.setPrecio(resul.getDouble("VALOR_DOLAR"));
            moneda.setVolatilidad(resul.getDouble("VOLATILIDAD"));
            moneda.setStock(resul.getDouble("STOCK"));
            listaDeMonedas.add(moneda);
		}				
		
		resul.close();
		stmt.close();
		
		Collections.sort(listaDeMonedas, new ComparadorNomenclatura());
		
		for (Moneda moneda : listaDeMonedas) {
		    System.out.println("Nombre: "+ moneda.getNombre()+"\nNomenclatura: "+moneda.getNomenclatura()+"\nPrecio en Dólares: "
		    		+moneda.getPrecio()+"\nVolatilidad: "+ moneda.getVolatilidad()+"\nStock: "+ moneda.getStock());
		}
	}
	
	
	
}
