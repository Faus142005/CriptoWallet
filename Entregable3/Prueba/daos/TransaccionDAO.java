package daos;

import java.sql.SQLException;
import java.util.List;

import clases.Transaccion;

public interface TransaccionDAO{
	
    public abstract void insertarTransaccion(Transaccion t) throws SQLException;
	public abstract void insertarTransaccionSoloResumen(Transaccion t) throws SQLException;
	//public abstract void actualizarTransaccion(DataSource data, Transaccion t) throws SQLException;				
	//public abstract void eliminarTransaccion(DataSource data, String nomenclatura) throws SQLException;
	public abstract List<Transaccion> listarTransacciones() throws SQLException;

}
