package daos;

import java.sql.SQLException;
import java.util.List;

public interface TransaccionDAO<Transaccion> {
	
    public abstract int insertarTransaccion(Transaccion t) throws SQLException;
	
	public abstract void actualizarTransaccion(int idTransaccion, Transaccion t) throws SQLException;				
	
	public abstract void eliminarTransaccion(int idTransaccion) throws SQLException;
	
	public abstract List<Transaccion> listarTransacciones(int idUsuario) throws SQLException;
	
	public abstract Transaccion buscarTransaccion(int idTransaccion) throws SQLException;

}
