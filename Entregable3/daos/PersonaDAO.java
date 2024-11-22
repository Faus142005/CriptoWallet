package daos;

import java.sql.SQLException;

public interface PersonaDAO<Persona> {

	public abstract int insertarPersona(Persona persona) throws SQLException; 
	public abstract void actualizarPersona(int id,Persona persona) throws SQLException;
	public abstract Persona buscarPersona(int ID) throws SQLException; 
	public abstract void eliminarPersona(int ID)throws SQLException;
}