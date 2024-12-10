package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.ActivoFIAT;
import clases.FIAT;
import clases.Persona;
import clases.Usuario;
import daos.ActivoFIATDAO;

public class ActivoFIATDAOJDBC implements ActivoFIATDAO<ActivoFIAT> {
	
	private DataSource dataSource;

    // Constructor para inicializar el DataSource
    public ActivoFIATDAOJDBC() {
        this.dataSource = MiDataSource.getDataSource();
    }
    
    // Método privado para obtener la conexión con cuidado de las excepciones
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    //---------------------------------------------------------------
    
	@Override
	public int insertarActivoFIAT(ActivoFIAT a) throws SQLException {
		String sql = "INSERT INTO ACTIVO_FIAT (ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?, ?, ?)";	
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, a.getUsuario().getIdUsuario());
			pstmt.setInt(2, a.getMoneda().getIdMoneda());
			pstmt.setDouble(3, a.getCantidad());
			
			pstmt.executeUpdate();
			//Obtener el id generado
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
	public void actualizarActivoFIATConID(ActivoFIAT a) throws SQLException {
		String sql = "UPDATE ACTIVO_FIAT SET ID_USUARIO = ?, ID_MONEDA = ?, CANTIDAD = ? WHERE ID = ?";
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, a.getUsuario().getIdUsuario());
			pstmt.setInt(2, a.getMoneda().getIdMoneda());
			pstmt.setDouble(3, a.getCantidad());
			pstmt.setInt(4, a.getIdActivo());
			
			pstmt.executeUpdate();
			connection.close();
		}
	}

	// Busca activoFIAT por el idUsuario y idMoneda
	@Override
	public void actualizarActivoFIAT(ActivoFIAT a) throws SQLException {
		String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE ID_MONEDA = ? AND ID_USUARIO = ?";
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {			
			
			pstmt.setDouble(1, a.getCantidad());
			pstmt.setInt(2, a.getMoneda().getIdMoneda());
			pstmt.setInt(3, a.getUsuario().getIdUsuario());						
			
			pstmt.executeUpdate();
			connection.close();
		}
	}

	//Busca activoFIAT por su ID
	@Override
	public ActivoFIAT buscarActivoFIAT(int idActivoFiat) throws SQLException {
		String sql = """				
				SELECT 
				af.CANTIDAD, 
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
				ACTIVO_FIAT af
			JOIN 
				MONEDA m ON af.ID_MONEDA = m.ID
			JOIN 
			    USUARIO u ON af.ID_USUARIO = u.ID
			JOIN 
			    PERSONA p ON u.ID_PERSONA = p.ID
			WHERE 
				af.ID = ? """;
		
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, idActivoFiat);
			ResultSet rs = pstmt.executeQuery();
			
			ActivoFIAT activoFIAT = null;
			
			if (rs.next()) {
				// Extraer información de la moneda fiat	 
				int idMoneda = rs.getInt("ID_MONEDA");				
	            String nombre = rs.getString("NOMBRE");
	            String nomenclatura = rs.getString("NOMENCLATURA");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");	            
	            String nombreIcono = rs.getString("NOMBRE_ICONO");	            
	            
	            // Crear objeto FIAT
	            FIAT fiat = new FIAT(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono);	            
	            
	            // 	Extraer información de la persona	
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
	            activoFIAT = new ActivoFIAT(idActivo, usuario, fiat, cantidad);	            
			}
			connection.close();
			return activoFIAT;			
		}
	}

	//Busca activoFIAT por el idUsuario e idMoneda
	@Override
	public ActivoFIAT buscarActivoFIAT(int idUsuario, int idMoneda) throws SQLException {
		String sql = """				
				SELECT 
				af.CANTIDAD, 
				af.ID,
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
				ACTIVO_FIAT af
			JOIN 
				MONEDA m ON af.ID_MONEDA = m.ID
			JOIN 
			    USUARIO u ON af.ID_USUARIO = u.ID
			JOIN 
			    PERSONA p ON u.ID_PERSONA = p.ID
			WHERE 
				m.ID = ? AND u.ID = ? """;
		
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, idMoneda);
			pstmt.setInt(2, idUsuario);
			ResultSet rs = pstmt.executeQuery();
			
			ActivoFIAT activoFIAT = null;
			
			if (rs.next()) {
				// Extraer información de la moneda fiat	 				
	            String nombre = rs.getString("NOMBRE");
	            String nomenclatura = rs.getString("NOMENCLATURA");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");	            
	            String nombreIcono = rs.getString("NOMBRE_ICONO");	            
	            
	            // Crear objeto FIAT
	            FIAT fiat = new FIAT(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono);	            
	            
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

	            // Extraer cantidad y crear el objeto ActivoFIAT
                int idActivo = rs.getInt("ID");
	            double cantidad = rs.getDouble("CANTIDAD");
	            activoFIAT = new ActivoFIAT(idActivo, usuario, fiat, cantidad);
	            
			}
			connection.close();
			return activoFIAT;			
		}
	}

	//Eliminar ActivoFiat por ID
	@Override
	public void eliminarActivoFIAT(int idActivoFiat) throws SQLException {
		String sql = "DELETE FROM ACTIVO_FIAT WHERE ID = ?";
	    try (Connection connection = getConnection(); 
	    		PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, idActivoFiat);
	        pstmt.executeUpdate();
	    }
	}

	//Eliminar ActivoFiat por idUsuario e idMoneda
	@Override
	public void eliminarActivoFIAT(int idUsuario, int idMoneda) throws SQLException {
		String sql = "DELETE FROM ACTIVO_FIAT WHERE ID_USUARIO = ? AND ID_MONEDA = ? AND ";
	    try (Connection connection = getConnection(); 
	    		PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, idUsuario);
	        pstmt.setInt(2, idMoneda);
	        pstmt.executeUpdate();
	    }
	}

	//Listas activos fiat de un usuario (o sea por idUsuario)
	@Override
	public List<ActivoFIAT> listarActivoFIAT(int idUsuario) throws SQLException {
		List<ActivoFIAT> activosFIAT = new ArrayList<>();
	     
		//SQL para obtener todos los activos de tipo FIAT
		String sql = """				
				SELECT 
				af.ID,
				af.CANTIDAD, 
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
				ACTIVO_FIAT af
			JOIN 
				MONEDA m ON af.ID_MONEDA = m.ID
			JOIN 
			    USUARIO u ON af.ID_USUARIO = u.ID
			JOIN 
			    PERSONA p ON u.ID_PERSONA = p.ID
			WHERE 
				u.ID = ? """;
		
		try (Connection connection = getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, idUsuario);
			ResultSet rs = pstmt.executeQuery();
			
			ActivoFIAT activoFIAT = null;
			
			while (rs.next()) {
				// Extraer información de la moneda fiat	 
				int idMoneda = rs.getInt("ID_MONEDA");				
	            String nombre = rs.getString("NOMBRE");
	            String nomenclatura = rs.getString("NOMENCLATURA");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");	            
	            String nombreIcono = rs.getString("NOMBRE_ICONO");	            
	            
	            // Crear objeto FIAT
	            FIAT fiat = new FIAT(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono);	            
	            
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

	            // Extraer cantidad y crear el objeto ActivoFIAT
                int idActivo = rs.getInt("ID");
	            double cantidad = rs.getDouble("CANTIDAD");
	            activoFIAT = new ActivoFIAT(idActivo, usuario, fiat, cantidad);	            
									
	            activosFIAT.add(activoFIAT);
	            
			} 
		} catch (SQLException e) {
	        System.err.println("Error al listar las monedas: " + e.getMessage());
	        throw e;
	    }

	    // Retornar la lista de activosFIAT
	    return activosFIAT;
	}

	

}
