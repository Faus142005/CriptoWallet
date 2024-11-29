package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

public interface Activo_FIATDAO <Activo>{
	public abstract void insertarActivoFIAT(Activo a) throws SQLException;
	public abstract void actualizarActivoFIAT(Activo a) throws SQLException;
	public abstract Activo buscarActivoFIAT(String nomenclatura) throws SQLException;
	public abstract void eliminarActivoFIAT(String nomenclatura)throws SQLException;
	public abstract List <Activo> listarActivoFIAT()throws SQLException;

}
