package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


public interface TransaccionDAO<Transaccion>{
	public abstract void insertarTransaccion(DataSource data,Transaccion t) throws SQLException;
	public abstract void insertarTransaccionSoloResumen(DataSource data,Transaccion t) throws SQLException;
	//public abstract void actualizarTransaccion(DataSource data, Transaccion t) throws SQLException;				
	//public abstract void eliminarTransaccion(DataSource data, String nomenclatura) throws SQLException;
	public abstract List<Transaccion> listarTransacciones(DataSource data) throws SQLException;
}
