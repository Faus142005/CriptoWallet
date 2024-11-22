package daosJDBC;

import java.sql.SQLException;
import java.util.List;

import clases.Transaccion;
import daos.TransaccionDAO;

public class TransaccionDAOJDBC implements TransaccionDAO<Transaccion>{

	@Override
	public void insertarTransaccion(Transaccion t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarTransaccionSoloResumen(Transaccion t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transaccion> listarTransacciones() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
