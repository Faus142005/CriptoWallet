package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.ActivoCripto;
import clases.Criptomoneda;
import clases.Persona;
import clases.Usuario;
import daos.ActivoCriptoDAO;

public class ActivoCriptoDAOJDBC implements ActivoCriptoDAO<ActivoCripto> {

	private DataSource dataSource;

    // Constructor para inicializar el DataSource
    public ActivoCriptoDAOJDBC() {
        this.dataSource = MiDataSource.getDataSource();
    }
    
    // Método privado para obtener la conexión con cuidado de las excepciones
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
	@Override
	public void insertarActivoCripto(ActivoCripto activo) throws SQLException {
		String sql = "INSERT INTO ACTIVO_CRIPTO (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";	
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, activo.getMoneda().getNomenclatura());
			pstmt.setDouble(2, activo.getCantidad());
			pstmt.executeUpdate();
			connection.close();
		}
	}

	@Override
	public void actualizarActivoCriptoConID(ActivoCripto activo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarActivoCripto(ActivoCripto activo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ActivoCripto buscarActivoCripto(int idActivoCripto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivoCripto buscarActivoCripto(int idUsuario, int idMoneda) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarActivoCriptoConID(int idActivoCripto) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarActivoCripto(int idUsuario, int idMoneda) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivoCripto> listarActivoCriptoUsuario(int idUsuario) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
