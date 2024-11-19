package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public interface Activo_CriptoDAO <Activo>{
	public abstract void insertarActivoCripto(DataSource data, Activo a) throws SQLException;
	public abstract void actualizarActivoCripto(DataSource data,Activo a) throws SQLException;
	public abstract Activo buscarActivoCripto(DataSource data, String nomenclatura) throws SQLException;
	public abstract void eliminarActivoCripto(DataSource data, String nomenclatura)throws SQLException;
	public abstract List <Activo> listarActivoCripto(DataSource data)throws SQLException;
	
}
