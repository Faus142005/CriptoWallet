package daos;

import java.sql.SQLException;

import clases.Persona;

public abstract class PersonaDAO {

	public abstract void insertarPersona(Persona persona) throws SQLException; 
	public abstract void actualizarPersona(Persona persona) throws SQLException;
	public abstract Persona buscarPersona(String nomenclatura) throws SQLException; 
	public abstract void eliminarPersona(String nomenclatura)throws SQLException;
}