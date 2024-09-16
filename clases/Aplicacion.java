package clases;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Aplicacion es la mente maestra de todo el proyecto
 * Dirige el control de personas, si 
 * -Son personas con activos altos
 * -Son personas con cuenta activada
 * -Son personas con cuenta desactivada
 * -Son personas baneadas
 * Dirige el contro de las monedas, stock y protocolos
 * @author Fausto Y Albertina
 */

public class Aplicacion {
	
	//Otras clases hechas por nosotros
	
	private static LinkedList <Persona> listaPersonas ;//= new <>(); //

	private static LinkedList <Persona> listaActivosAltos; //

	private static LinkedList <Persona> listaAltas; //

	private static LinkedList <Persona> listaBajas; //
	
	private static LinkedList <Persona> listaBaneados;

	private static ArrayList <MonedaCompleta> listaMonedas;
	
	private static ArrayList <Stock> listaStocks;

	private static ArrayList <Protocolo> listaProtocolos;
	
	
	/**
	 * @return La listaMonedas
	 */
	public static ArrayList<MonedaCompleta> getListaMonedas() {
		return listaMonedas;
	}

	/**
	 * @param listaMonedas: La listaMonedas para setear
	 */
	public static void setListaMonedas(ArrayList<MonedaCompleta> listaMonedas) {
		Aplicacion.listaMonedas = listaMonedas;
	}

	/**
	 * @return La listaStocks
	 */
	public static ArrayList<Stock> getListaStocks() {
		return listaStocks;
	}

	/**
	 * @param listaStocks: La listaStocks para setear
	 */
	public static void setListaStocks(ArrayList<Stock> listaStocks) {
		Aplicacion.listaStocks = listaStocks;
	}

	/**
	 * @return La listaProtocolos
	 */
	public static ArrayList<Protocolo> getListaProtocolos() {
		return listaProtocolos;
	}

	/**
	 * @param listaProtocolos: La listaProtocolos para setear
	 */
	public static void setListaProtocolos(ArrayList<Protocolo> listaProtocolos) {
		Aplicacion.listaProtocolos = listaProtocolos;
	}
	
	private void registrarse() {
	}
	
	private void verificarIdentidad() {
	}
	
	private void iniciarSesion() {
	}
	
	private void cerrarSesion() {
	}
	
	private void darseDeBaja() {
	}

	public boolean enviarCripto(Billetera billetera, Moneda moneda, double cantidad, String ubicacion) {
		return true;
	}
	
	public boolean retirarEnDefi(Billetera billetera, String protocolo, Moneda moneda, double cantidad) {
		return true;
	}
	
	public boolean colocarEnDefi(Billetera billetera, String protocolo, Moneda moneda, double cantidad) {
		return true;
	}
	
	public boolean venderCripto(Billetera billetera, Moneda moneda, double cantidad) {
		return true;
	}
	
	public boolean comprarCripto(Billetera billetera, Moneda moneda, double cantidad) {
		return true;
	}
	
	public boolean swap(Billetera billetera, Moneda monedaOriginal, Moneda monedaResultante, double cantidad) {
		return true;
	}
}


