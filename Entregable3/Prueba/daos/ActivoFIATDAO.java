package daos;

import java.sql.SQLException;
import java.util.List;

import clases.ActivoFIAT;

public interface ActivoFIATDAO {

	public abstract void insertarActivoFIAT(ActivoFIAT a) throws SQLException;
	public abstract void actualizarActivoFIAT(ActivoFIAT a) throws SQLException;
	public abstract ActivoFIAT buscarActivoFIAT(String nomenclatura) throws SQLException;
	public abstract void eliminarActivoFIAT(String nomenclatura)throws SQLException;
	public abstract List <ActivoFIAT> listarActivoFIAT()throws SQLException;

}
