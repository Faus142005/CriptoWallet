package daos;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import aplicacion.MiDataSource;

public interface ActivoCriptoDAO<ActivoCripto> {	
	
	public abstract void insertarActivoCripto(ActivoCripto activo) throws SQLException; 
	public abstract void actualizarActivoCripto(ActivoCripto activo) throws SQLException;
	public abstract ActivoCripto buscarActivoCripto(String nomenclatura) throws SQLException; 
	public abstract void eliminarActivoCripto(String nomenclatura)throws SQLException;
	public abstract List <ActivoCripto> listarActivoCripto()throws SQLException;	
}
