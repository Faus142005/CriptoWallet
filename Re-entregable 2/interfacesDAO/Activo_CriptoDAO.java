package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

public interface Activo_CriptoDAO <Activo>{
	public abstract void insertarActivoCripto(Activo a) throws SQLException;
	public abstract void actualizarActivoCripto(Activo a) throws SQLException;
	public abstract Activo buscarActivoCripto(String nomenclatura) throws SQLException;
	public abstract void eliminarActivoCripto(String nomenclatura)throws SQLException;
	public abstract List <Activo> listarActivoCripto()throws SQLException;
	
}
