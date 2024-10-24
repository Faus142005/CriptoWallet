package clasesDAO;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class CriptomonedaDAOjdbc implements CriptomonedaDAO {
		
	public void crearCriptomoneda(Connection connection) throws SQLException {
		Scanner in = new Scanner (System.in);
		System.out.println("Ingrese el nombre de la criptomoneda");
		String nombre = in.nextLine();

		System.out.println("Ingrese la nomenclatura de la criptomoneda");
		String nomenclatura = in.nextLine();

		System.out.println("Ingrese el path de la imagen para el icono de la criptomoneda");
		String imagePath = in.nextLine();

		System.out.println("Ingrese el precio de " + nombre + "en dolares");
		double precio = in.nextDouble();
		
		System.out.println("Desea confirmar?\n Ingrese 1 para confirmar, y cualquier otra para cancelar.");
		int confirmacion=in.nextInt();
		if(confirmacion==1) {
			
		}
		
	}
	

}
