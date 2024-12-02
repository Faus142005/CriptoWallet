package daos;

import java.sql.SQLException;
import java.util.List;

import clases.ActivoCripto;
import clases.Usuario;

public interface ActivoCriptoDAO {

	public abstract void insertarActivoCripto(ActivoCripto activo) throws SQLException;

	public abstract void actualizarActivoCriptoConID(ActivoCripto activo) throws SQLException; // Busca por el idActivo

	public abstract void actualizarActivoCripto(ActivoCripto activo) throws SQLException; // Busca por el idUsuario y
																							// idMoneda

	public abstract ActivoCripto buscarActivoCripto(int idActivoCripto) throws SQLException;

	public abstract ActivoCripto buscarActivoCripto(int idUsuario, int idMoneda) throws SQLException;

	public abstract void eliminarActivoCriptoConID(int idActivoCripto) throws SQLException;

	public abstract void eliminarActivoCripto(int idUsuario, int idMoneda) throws SQLException;

	public abstract List<ActivoCripto> listarActivoCriptoUsuario(int idUsuario) throws SQLException;
}
