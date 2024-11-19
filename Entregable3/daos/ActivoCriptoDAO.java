package daos;

import java.sql.SQLException;

import clases.ActivoCripto;

public interface ActivoCriptoDAO {

	public abstract void insertarActivoCripto(ActivoCripto activo) throws SQLException; 
	public abstract void actualizarActivoCripto(ActivoCripto activo) throws SQLException;
	public abstract ActivoCripto buscarActivoCripto(String nomenclatura) throws SQLException; 
	public abstract void eliminarActivoCripto(String nomenclatura)throws SQLException;
}
