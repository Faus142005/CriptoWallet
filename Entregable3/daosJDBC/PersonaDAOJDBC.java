package daosJDBC;

import java.sql.SQLException;

import clases.Persona;
import daos.PersonaDAO;

public class PersonaDAOJDBC implements PersonaDAO<Persona>{

	@Override
	public int insertarPersona(Persona persona) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarPersona(int id, Persona persona) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Persona buscarPersona(int ID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarPersona(int ID) throws SQLException {
		// TODO Auto-generated method stub
		
	}

    
}