package interfacesDAO;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public interface Activo_FIATDAO <Activo>{
	public abstract void insertarActivoFIAT(DataSource data, Activo a) throws SQLException;
	public abstract void actualizarActivoFIAT(DataSource data,Activo a) throws SQLException;
	public abstract Activo buscarActivoFIAT(DataSource data, String nomenclatura) throws SQLException;
	public abstract void eliminarActivoFIAT(DataSource data, String nomenclatura)throws SQLException;
	public abstract List <Activo> listarActivoFIAT(DataSource data)throws SQLException;

}
