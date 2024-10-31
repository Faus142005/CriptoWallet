package clases;

import java.util.ArrayList;

/**
 * Protocolo representa los protocolos de DeFi
 * Tiene un nombre y los rendimientos que presenta
 * @author Fausto Y Albertina
 */

public class Protocolo {

	private String nombre;
	//Otras clases hechas por nosotros
	private ArrayList<Rendimiento> listaRendimientos;
	/**
	 * @return El nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre El nombre para setear
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return La listaRendimientos
	 */
	public ArrayList<Rendimiento> getListaRendimientos() {
		return listaRendimientos;
	}
	/**
	 * @param listaRendimientos La listaRendimientos para setear
	 */
	public void setListaRendimientos(ArrayList<Rendimiento> listaRendimientos) {
		this.listaRendimientos = listaRendimientos;
	}
	
	
}
