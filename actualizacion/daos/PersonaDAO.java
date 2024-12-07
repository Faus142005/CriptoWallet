package daos;

import java.sql.SQLException;

public interface PersonaDAO<Persona> {

	//Si se inserta correctamente la persona, devuelve el id generado automáticamente
	public abstract int insertarPersona(Persona persona) throws SQLException; 
	
	public abstract void actualizarPersona(int id,Persona persona) throws SQLException;
	
	public abstract Persona buscarPersona(int ID) throws SQLException; 
	
	public abstract void eliminarPersona(int ID)throws SQLException;
}