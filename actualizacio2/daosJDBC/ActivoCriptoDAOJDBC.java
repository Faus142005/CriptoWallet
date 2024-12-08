package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.ActivoCripto;
import clases.ActivoFIAT;
import clases.Criptomoneda;
import clases.FIAT;
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
	public int insertarActivoCripto(ActivoCripto a) throws SQLException {
		String sql = "INSERT INTO ACTIVO_CRIPTO (ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?, ?, ?)";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, a.getUsuario().getIdUsuario());
			pstmt.setInt(2, a.getMoneda().getIdMoneda());
			pstmt.setDouble(3, a.getCantidad());

			pstmt.executeUpdate();

			// Obtener el id generado
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					a.setIdActivo(generatedKeys.getInt(1));
					return a.getIdActivo();
				} else
					throw new SQLException("No se pudo obtener el ID generado para la moneda FIAT.");
			}
		}
	}

	@Override
	public void actualizarActivoCriptoConID(ActivoCripto a) throws SQLException {
		String sql = "UPDATE ACTIVO_CRIPTO SET ID_USUARIO = ?, ID_MONEDA = ?, CANTIDAD = ? WHERE ID = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, a.getUsuario().getIdUsuario());
			pstmt.setInt(2, a.getMoneda().getIdMoneda());
			pstmt.setDouble(3, a.getCantidad());
			pstmt.setInt(4, a.getIdActivo());

			pstmt.executeUpdate();
			connection.close();
		}
	}

	// Actualiza activoCripto por el idUsuario y idMoneda
	@Override
	public void actualizarActivoCripto(ActivoCripto a) throws SQLException {
		String sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE ID_MONEDA = ? AND ID_USUARIO = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setDouble(1, a.getCantidad());
			pstmt.setInt(2, a.getMoneda().getIdMoneda());
			pstmt.setInt(3, a.getUsuario().getIdUsuario());

			pstmt.executeUpdate();
			connection.close();
		}
	}

	// Busca activoCripto por su ID
	@Override
	public ActivoCripto buscarActivoCripto(int idActivoCripto) throws SQLException {
		String sql = """
					SELECT
					ac.CANTIDAD,
					m.ID AS ID_MONEDA,
					m.NOMENCLATURA,
					m.NOMBRE,
					m.VALOR_DOLAR,
					m.VOLATILIDAD,
					m.NOMBRE_ICONO,
					u.ID AS ID_USUARIO,
				    u.EMAIL,
				    u.PASSWORD,
				    u.ACEPTA_TERMINOS,
				    p.ID AS ID_PERSONA,
				    p.NOMBRES AS PERSONA_NOMBRE,
				    p.APELLIDOS AS PERSONA_APELLIDO
				FROM
					ACTIVO_CRIPTO ac
				JOIN
					MONEDA m ON ac.ID_MONEDA = m.ID
				JOIN
				    USUARIO u ON ac.ID_USUARIO = u.ID
				JOIN
				    PERSONA p ON u.ID_PERSONA = p.ID
				WHERE
					ac.ID = ? AND m.TIPO = 'C' """;

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, idActivoCripto);
			ResultSet rs = pstmt.executeQuery();

			ActivoCripto activoCripto = null;

			if (rs.next()) {
				// Extraer información de la Criptomoneda
				int idMoneda = rs.getInt("ID_MONEDA");
				String nombre = rs.getString("NOMBRE");
				String nomenclatura = rs.getString("NOMENCLATURA");
				double valorDolar = rs.getDouble("VALOR_DOLAR");
				double volatilidad = rs.getDouble("VOLATILDAD");
				String nombreIcono = rs.getString("NOMBRE_ICONO");

				// Crear objeto Criptomoneda
				Criptomoneda criptomoneda = new Criptomoneda(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono,
						volatilidad);

				// Extraer información de la persona
				int idPersona = rs.getInt("ID_PERSONA");
				String personaNombres = rs.getString("PERSONA_NOMBRE");
				String personaApellidos = rs.getString("PERSONA_APELLIDO");

				// Crear objeto Persona
				Persona persona = new Persona(idPersona, personaNombres, personaApellidos);

				// Extraer información del usuario
				int idUsuario = rs.getInt("ID_USUARIO");
				String email = rs.getString("EMAIL");
				String contraseña = rs.getString("PASSWORD");
				boolean tyc = rs.getBoolean("ACEPTA_TERMINOS");

				// Crear objeto Usuario
				Usuario usuario = new Usuario(idUsuario, persona, email, contraseña, tyc);

				// Extraer cantidad y crear el objeto ActivoFIAT
				int idActivo = rs.getInt("ID");
				double cantidad = rs.getDouble("CANTIDAD");
				activoCripto = new ActivoCripto(idActivo, usuario, criptomoneda, cantidad);

			}
			connection.close();
			return activoCripto;
		}
	}

	// Busca ActivoCripto por el idUsuario e idMoneda
	@Override
	public ActivoCripto buscarActivoCripto(int idUsuario, int idMoneda) throws SQLException {
		String sql = """
					SELECT
					ac.CANTIDAD,
					ac.ID,
					m.ID AS ID_MONEDA,
					m.NOMENCLATURA,
					m.NOMBRE,
					m.VALOR_DOLAR,
					m.VOLATILIDAD,
					m.NOMBRE_ICONO,
					u.ID AS ID_USUARIO,
				    u.EMAIL,
				    u.PASSWORD,
				    u.ACEPTA_TERMINOS,
				    p.ID AS ID_PERSONA,
				    p.NOMBRES AS PERSONA_NOMBRE,
				    p.APELLIDOS AS PERSONA_APELLIDO
				FROM
					ACTIVO_CRIPTO ac
				JOIN
					MONEDA m ON ac.ID_MONEDA = m.ID
				JOIN
				    USUARIO u ON ac.ID_USUARIO = u.ID
				JOIN
				    PERSONA p ON u.ID_PERSONA = p.ID
				WHERE
					m.ID = ? AND u.ID = ? AND m.TIPO = 'C' """;

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, idMoneda);
			pstmt.setInt(2, idUsuario);
			ResultSet rs = pstmt.executeQuery();

			ActivoCripto activoCripto = null;

			if (rs.next()) {
				// Extraer información de la criptomoneda
				String nombre = rs.getString("NOMBRE");
				String nomenclatura = rs.getString("NOMENCLATURA");
				double valorDolar = rs.getDouble("VALOR_DOLAR");
				double volatilidad = rs.getDouble("VOLATILIDAD");
				String nombreIcono = rs.getString("NOMBRE_ICONO");

				// Crear objeto criptomoneda
				Criptomoneda criptomoneda = new Criptomoneda(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono,
						volatilidad);

				// Extraer información de la persona
				int idPersona = rs.getInt("ID_PERSONA");
				String personaNombres = rs.getString("PERSONA_NOMBRE");
				String personaApellidos = rs.getString("PERSONA_APELLIDO");

				// Crear objeto Persona
				Persona persona = new Persona(idPersona, personaNombres, personaApellidos);

				// Extraer información del usuario
				String email = rs.getString("EMAIL");
				String contraseña = rs.getString("PASSWORD");
				boolean tyc = rs.getBoolean("ACEPTA_TERMINOS");

				// Crear objeto Usuario
				Usuario usuario = new Usuario(idUsuario, persona, email, contraseña, tyc);

				// Extraer cantidad y crear el objeto ActivoCripto
				int idActivo = rs.getInt("ID");
				double cantidad = rs.getDouble("CANTIDAD");
				activoCripto = new ActivoCripto(idActivo, usuario, criptomoneda, cantidad);

			}
			connection.close();
			return activoCripto;
		}
	}

	// Eliminar ActivoCripto por ID
	@Override
	public void eliminarActivoCriptoConID(int idActivoCripto) throws SQLException {
		String sql = "DELETE FROM ACTIVO_CRIPTO WHERE ID = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, idActivoCripto);
			pstmt.executeUpdate();
		}
	}

	// Eliminar ActivoCripto por idUsuario e idMoneda
	@Override
	public void eliminarActivoCripto(int idUsuario, int idMoneda) throws SQLException {
		String sql = "DELETE FROM ACTIVO_CRIPTO WHERE ID_USUARIO = ? AND ID_MONEDA = ? AND ";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, idUsuario);
			pstmt.setInt(2, idMoneda);
			pstmt.executeUpdate();
		}
	}

	// Listas activos criptos de un usuario (o sea por idUsuario)
	@Override
	public List<ActivoCripto> listarActivoCriptoUsuario(int idUsuario) throws SQLException {
		List<ActivoCripto> activosCripto = new ArrayList<>();
	     
		//SQL para obtener todos los activos de tipo Cripto
		String sql = """
				SELECT 
				ac.ID,
				ac.CANTIDAD, 
				m.ID AS ID_MONEDA, 
				m.NOMENCLATURA, 
				m.NOMBRE, 
				m.VALOR_DOLAR, 					
				m.NOMBRE_ICONO,								
				u.ID AS ID_USUARIO, 
			    u.EMAIL,
			    u.PASSWORD, 
			    u.ACEPTA_TERMINOS,
			    p.ID AS ID_PERSONA, 
			    p.NOMBRES AS PERSONA_NOMBRE, 
			    p.APELLIDOS AS PERSONA_APELLIDO
			FROM 
				ACTIVO_CRIPTO ac
			JOIN 
				MONEDA m ON ac.ID_MONEDA = m.ID
			JOIN 
			    USUARIO u ON ac.ID_USUARIO = u.ID
			JOIN 
			    PERSONA p ON u.ID_PERSONA = p.ID
			WHERE 
				u.ID = ? AND m.TIPO = 'C' """;
		
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, idUsuario);
			ResultSet rs = pstmt.executeQuery();
			
			ActivoCripto activoCripto = null;
			
			while (rs.next()) {
				// Extraer información de la criptomoneda	 
				int idMoneda = rs.getInt("ID_MONEDA");				
	            String nombre = rs.getString("NOMBRE");
	            String nomenclatura = rs.getString("NOMENCLATURA");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");
	            double volatilidad = rs.getDouble("VOLATILIDAD");	
	            String nombreIcono = rs.getString("NOMBRE_ICONO");	            
	            
	            // Crear objeto criptomoneda
	            Criptomoneda criptomoneda = new Criptomoneda(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono, volatilidad);	            
	            
	            // 	Extraer información de la persona	
	            int idPersona = rs.getInt("ID_PERSONA");
	            String personaNombres = rs.getString("PERSONA_NOMBRE");
	            String personaApellidos = rs.getString("PERSONA_APELLIDO");

	            // Crear objeto Persona
	            Persona persona = new Persona(idPersona, personaNombres, personaApellidos);
	            
	            // Extraer información del usuario  
                String email = rs.getString("EMAIL");
                String contraseña = rs.getString("PASSWORD");
                boolean tyc = rs.getBoolean("ACEPTA_TERMINOS");

                // Crear objeto Usuario
                Usuario usuario = new Usuario(idUsuario, persona, email, contraseña, tyc);

	            // Extraer cantidad y crear el objeto ActivoCripto
                int idActivo = rs.getInt("ID");
	            double cantidad = rs.getDouble("CANTIDAD");
	            activoCripto = new ActivoCripto(idActivo, usuario, criptomoneda, cantidad);	            
									
	            activosCripto.add(activoCripto);
	            
			} 
		} catch (SQLException e) {
	        System.out.println("ErrorA al listar los activos: " + e.getMessage());
	        throw e;
	    }

	    // Retornar la lista de activosCripto
	    return activosCripto;
	}

}
