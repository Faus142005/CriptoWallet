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
	public void insertarActivoCripto(ActivoCripto a) throws SQLException {
		String sql = "INSERT INTO ACTIVO_CRIPTO (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";	
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, a.getMoneda().getNomenclatura());
			pstmt.setDouble(2, a.getCantidad());
			pstmt.executeUpdate();
			connection.close();
		}
	}

	@Override
	public void actualizarActivoCripto(ActivoCripto a) throws SQLException {
		String sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setDouble(1, a.getCantidad());
			pstmt.setString(2, a.getMoneda().getNomenclatura());
			pstmt.executeUpdate();
			connection.close();
		}
	}

	@Override
	public ActivoCripto buscarActivoCripto(String nomenclatura) throws SQLException {
		
		//sql nuevo con el join
		String sql = """				
				SELECT 
					ac.CANTIDAD, 
					m.ID AS MONEDA_ID, 
					m.NOMENCLATURA, 
					m.NOMBRE, 
					m.VALOR_DOLAR, 
					m.VOLATILIDAD,
					m.NOMBRE_ICONO,
					m.STOCK,
					u.ID AS USUARIO_ID, 
				    u.EMAIL,
				    u.CONTRASEÑA, 
				    u.TYC,
				    p.ID AS PERSONA_ID, 
				    p.NOMBRE AS PERSONA_NOMBRE, 
				    p.APELLIDO AS PERSONA_APELLIDO
				FROM 
					ACTIVO_CRIPTO ac
				JOIN 
					MONEDA m ON ac.ID_MONEDA = m.ID
				JOIN 
				    USUARIO u ON ac.ID_USUARIO = u.ID
				JOIN 
				    PERSONA p ON u.ID_PERSONA = p.ID
				WHERE 
					m.NOMENCLATURA = ?""";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			ResultSet rs = pstmt.executeQuery();
			ActivoCripto a = null;
			if (rs.next()) {
				// Extraer información de la moneda	            
	            String nombre = rs.getString("NOMBRE");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");
	            double volatilidad = rs.getDouble("VOLATILIDAD");
	            String nombreIcono = rs.getString("NOMBRE_ICONO");
	            double stock = rs.getDouble("STOCK");
	            
	            // Crear objeto Criptomoneda
	            Criptomoneda cripto = new Criptomoneda(nombre, nomenclatura, valorDolar, volatilidad, nombreIcono, stock);
	            
	            // 	Extraer información de la persona	            
	            String personaNombres = rs.getString("PERSONA_NOMBRE");
	            String personaApellidos = rs.getString("PERSONA_APELLIDO");

	            // Crear objeto Persona
	            Persona persona = new Persona(personaNombres, personaApellidos);
	            
	            // Extraer información del usuario   	            
                String email = rs.getString("EMAIL");
                String contraseña = rs.getString("CONTRASEÑA");
                boolean tyc = rs.getBoolean("TYC");

                // Crear objeto Usuario
                Usuario usuario = new Usuario(persona, email, contraseña, tyc);

	            // Extraer cantidad y crear el objeto ActivoCripto
	            double cantidad = rs.getDouble("CANTIDAD");
	            a = new ActivoCripto(usuario, cripto, cantidad);
	            				
			}
			connection.close();
			return a;			
		}
	}

	@Override
	public void eliminarActivoCripto(String nomenclatura) throws SQLException {
		String sql = "DELETE FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			pstmt.executeUpdate();
			connection.close();
		}
	}

	@Override
	public List<ActivoCripto> listarActivoCripto() throws SQLException {
		List<ActivoCripto> lista_activos = new LinkedList<ActivoCripto>();			
		
		String sql = """
				SELECT 
					ac.CANTIDAD, 
					m.ID AS MONEDA_ID, 
					m.NOMENCLATURA, 
					m.NOMBRE, 
					m.VALOR_DOLAR, 
					m.VOLATILIDAD, 
					m.NOMBRE_ICONO, 
					m.STOCK,
					u.ID AS USUARIO_ID, 
					u.EMAIL, 
					u.CONTRASEÑA, 
					u.TYC,
					p.NOMBRES, 
					p.APELLIDOS
				FROM 
					ACTIVO_CRIPTO ac
				JOIN 
					MONEDA m ON ac.ID_MONEDA = m.ID
				JOIN 
					USUARIO u ON ac.ID_USUARIO = u.ID
				JOIN 
					PERSONA p ON u.ID_PERSONA = p.ID
				""";
				
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery(sql)) {						
			while (rs.next()) {
				
				// Extraer información de la criptomoneda
	            String nomenclatura = rs.getString("NOMENCLATURA");
	            String nombreMoneda = rs.getString("NOMBRE");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");
	            double volatilidad = rs.getDouble("VOLATILIDAD");
	            String nombreIcono = rs.getString("NOMBRE_ICONO");
	            double stock = rs.getDouble("STOCK");
	            // Crear Criptomoneda
	            Criptomoneda cripto = new Criptomoneda(nombreMoneda, nomenclatura, valorDolar, volatilidad, nombreIcono, stock);

	            // Extraer información de la persona
	            String nombresPersona = rs.getString("NOMBRES");
	            String apellidosPersona = rs.getString("APELLIDOS");
	            // Crear Persona
	            Persona persona = new Persona(nombresPersona, apellidosPersona);

	            // Extraer información del usuario
	            String email = rs.getString("EMAIL");
	            String contraseña = rs.getString("CONTRASEÑA");
	            boolean tyc = rs.getBoolean("TYC");
	            // Crear usuario
	            Usuario usuario = new Usuario(persona, email, contraseña, tyc);

	            // Extraer cantidad de cripto y crear el objeto ActivoCripto
	            double cantidad = rs.getDouble("CANTIDAD");
	            ActivoCripto activo = new ActivoCripto(usuario, cripto, cantidad);

	            // Agregar el activo a la lista
	            lista_activos.add(activo);								
				
			}
			connection.close();
			return lista_activos;
		}		
	}

}
