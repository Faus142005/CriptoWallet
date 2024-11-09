package clases;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Aplicacion es la mente maestra de todo el proyecto
 * Dirige el control de personas, si:
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

	private static ArrayList <Moneda> listaMonedas;
	
	private static ArrayList <Stock> listaStocks;

	private static ArrayList <Protocolo> listaProtocolos;
	
	
	/**
	 * @return La listaMonedas
	 */
	public static ArrayList<Moneda> getListaMonedas() {
		return listaMonedas;
	}

	/**
	 * @param listaMonedas: La listaMonedas para setear
	 */
	public static void setListaMonedas(ArrayList<Moneda> listaMonedas) {
		Aplicacion.listaMonedas = listaMonedas;
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

	/**
	 * Envia una cantidad de criptomonedas desde la billetera del usuario a una ubicacion
	 * @param billetera: la billetera del usuario
	 * @param moneda: moneda a enviar
	 * @param cantidad: cantidad de monedas
	 * @param ubicacion: destino de la moneda
	 */
	public static boolean enviarCripto(Billetera billetera, Moneda moneda, double cantidad, String ubicacion) {
		return true;
	}
	
	/**
	 * Retira una cantidad de monedas bajo un protocolo DeFi y las devuelve a billetera
	 * @param billetera: la billetera del usuario
	 * @param protocolo: protocolo del DeFi donde se encuentran las monedas
	 * @param moneda: moneda a retirar
	 * @param cantidad: cantidad de monedas a retirar
	 */
	public static boolean retirarEnDefi(Billetera billetera, String protocolo, Moneda moneda, double cantidad) {
		return true;
	}
	
	/**
	 * Coloca una cantidad de monedas bajo un protocolo DeFi y las saca de la billetera
	 * @param billetera: la billetera del usuario
	 * @param protocolo: protocolo del DeFi donde se colocarán las monedas
	 * @param moneda: moneda a colocar
	 * @param cantidad: cantidad de monedas a colocar
	 */
	public static boolean colocarEnDefi(Billetera billetera, String protocolo, Moneda moneda, double cantidad) {
		return true;
	}
	
	/**
	 * Retira una cantidad de monedas de la billetera del usuario y las reemplaza por su equivalente en la moneda fiduciaria
	 * @param billetera: la billetera del usuario	 
	 * @param moneda: moneda a vender
	 * @param cantidad: cantidad de monedas a vender
	 */
	public static boolean venderCripto(Billetera billetera, Moneda moneda, double cantidad) {
		return true;
	}
	
	/**
	 * Retira una cantidad de monedas de la moneda fiduciariad de la billetera del usuario y las reemplaza por su equivalente en la moneda a comprar
	 * @param billetera: la billetera del usuario	 
	 * @param moneda: moneda a comprar
	 * @param cantidad: cantidad de monedas a comprar
	 */
	public static boolean comprarCripto(Billetera billetera, Moneda moneda, double cantidad) {
		return true;
	}
	
	/**
	 * Retira una cantidad de monedas de la monedaOriginal de la billetera del usuario y las reemplaza por su equivalente en la monedaResultante
	 * @param billetera: la billetera del usuario	 
	 * @param monedaOriginal: moneda a intercambiar
	 * @param monedaResultante: moneda a recibir
	 * @param cantidad: cantidad de monedas a swapear
	 */
	public static boolean swap(Billetera billetera, Moneda monedaOriginal, Moneda monedaResultante, double cantidad) {
		return true;
	}	
}


