package daos;

import java.sql.SQLException;
import java.util.List;

public interface ActivoFIATDAO<ActivoFIAT> {

	public abstract int insertarActivoFIAT(ActivoFIAT a) throws SQLException;

	public abstract void actualizarActivoFIATConID(ActivoFIAT a) throws SQLException; // Busca por el idActivo

	public abstract void actualizarActivoFIAT(ActivoFIAT a) throws SQLException; // Busca por el idUsuario y idMoneda

	public abstract ActivoFIAT buscarActivoFIAT(int idActivoFiat) throws SQLException;

	public abstract ActivoFIAT buscarActivoFIAT(int idUsuario, int idMoneda) throws SQLException;

	public abstract void eliminarActivoFIAT(int idActivoFiat) throws SQLException;

	public abstract void eliminarActivoFIAT(int idUsuario, int idMoneda) throws SQLException;

	public abstract List<ActivoFIAT> listarActivoFIAT(int idUsuario) throws SQLException;

}
