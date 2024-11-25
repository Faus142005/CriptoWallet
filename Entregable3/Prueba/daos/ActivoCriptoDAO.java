package daos;

import java.sql.SQLException;
import java.util.List;

import clases.ActivoCripto;
import clases.Usuario;

public interface ActivoCriptoDAO {	
	
	public abstract void insertarActivoCripto(ActivoCripto activo) throws SQLException; 
	public abstract void actualizarActivoCripto(ActivoCripto activo) throws SQLException;
	public abstract ActivoCripto buscarActivoCripto(Usuario usuario, String nomenclatura) throws SQLException; 
	public abstract void eliminarActivoCripto(String nomenclatura)throws SQLException;
	public abstract List <ActivoCripto> listarActivoCripto()throws SQLException;	
}
