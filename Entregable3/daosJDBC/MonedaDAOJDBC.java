package daosJDBC;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import daos.MonedaDAO;

public class MonedaDAOJDBC implements MonedaDAO<Moneda>{

	@Override
	public void insertarFIAT(DataSource data, FIAT c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarFIAT(DataSource data, FIAT c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FIAT buscarFIAT(DataSource data, String nomenclatura) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarCriptomoneda(DataSource data, Criptomoneda c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarCriptomoneda(DataSource data, Criptomoneda c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Criptomoneda buscarCriptomoneda(DataSource data, String nomenclatura) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char buscarMonedaPorNomenclatura(DataSource data, String nomenclatura) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void eliminarMoneda(DataSource data, String nomenclatura) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Moneda> listarMonedas(DataSource data) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
