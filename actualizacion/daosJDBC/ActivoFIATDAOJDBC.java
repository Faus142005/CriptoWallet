package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.ActivoFIAT;
import clases.FIAT;
import clases.Persona;
import clases.Usuario;
import daos.ActivoFIATDAO;

public class ActivoFIATDAOJDBC implements ActivoFIATDAO<ActivoFIAT> {

	@Override
	public void insertarActivoFIAT(ActivoFIAT a) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarActivoFIATConID(ActivoFIAT a) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarActivoFIAT(ActivoFIAT a) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ActivoFIAT buscarActivoFIAT(int idActivoFiat) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivoFIAT buscarActivoFIAT(int idUsuario, int idMoneda) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarActivoFIAT(int idActivoFiat) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarActivoFIAT(int idUsuario, int idMoneda) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivoFIAT> listarActivoFIAT(int idUsuario) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
