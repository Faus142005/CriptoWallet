package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
	
	@Override
	public void insertarActivoFIAT(ActivoFIAT a) throws SQLException {
		String sql = "INSERT INTO ACTIVO_FIAT (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";	
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, a.getMoneda().getNomenclatura());
			pstmt.setDouble(2, a.getCantidad());
			pstmt.executeUpdate();
			connection.close();
		}
	}

	@Override
	public void actualizarActivoFIAT(ActivoFIAT a) throws SQLException {
		String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setDouble(1, a.getCantidad());
			pstmt.setString(2, a.getMoneda().getNomenclatura());
			pstmt.executeUpdate();
			connection.close();
		}
	}

	@Override
	public ActivoFIAT buscarActivoFIAT(String nomenclatura) throws SQLException {
		
		//sql nuevo con el join
		String sql = """				
				SELECT 
					af.CANTIDAD, 
					m.ID AS MONEDA_ID, 
					m.TIPO,
					m.NOMENCLATURA, 
					m.NOMBRE, 
					m.VALOR_DOLAR, 					
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
					ACTIVO_FIAT af
				JOIN 
					MONEDA m ON af.ID_MONEDA = m.ID
				JOIN 
				    USUARIO u ON af.ID_USUARIO = u.ID
				JOIN 
				    PERSONA p ON u.ID_PERSONA = p.ID
				WHERE 
					m.NOMENCLATURA = ?""";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			ResultSet rs = pstmt.executeQuery();
			ActivoFIAT a = null;
			if (rs.next()) {
				// Extraer información de la moneda	 
				char tipo = rs.getString("TIPO").charAt(0);
	            String nombre = rs.getString("NOMBRE");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");	            
	            String nombreIcono = rs.getString("NOMBRE_ICONO");
	            double stock = rs.getDouble("STOCK");
	            
	            // Crear objeto FIAT
	            FIAT fiat = new FIAT(tipo, nombre, nomenclatura, valorDolar, nombreIcono, stock);	            
	            
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

	            // Extraer cantidad y crear el objeto ActivoFIAT
	            double cantidad = rs.getDouble("CANTIDAD");
	            a = new ActivoFIAT(usuario, fiat, cantidad);
	            				
			}
			connection.close();
			return a;			
		}
	}

	@Override
	public void eliminarActivoFIAT(String nomenclatura) throws SQLException {
		String sql = "DELETE FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			pstmt.executeUpdate();
			connection.close();
		}
	}

	@Override
	public List<ActivoFIAT> listarActivoFIAT() throws SQLException {
		List<ActivoFIAT> lista_activos = new LinkedList<ActivoFIAT>();								
		
		String sql = """
				SELECT 
					af.CANTIDAD, 
					m.ID AS MONEDA_ID, 
					m.NOMENCLATURA, 
					m.NOMBRE, 
					m.VALOR_DOLAR, 					
					m.NOMBRE_ICONO, 
					m.STOCK,
					u.ID AS USUARIO_ID, 
					u.EMAIL, 
					u.CONTRASEÑA, 
					u.TYC,
					p.NOMBRES, 
					p.APELLIDOS
				FROM 
					ACTIVO_FIAT af
				JOIN 
					MONEDA m ON af.ID_MONEDA = m.ID
				JOIN 
					USUARIO u ON af.ID_USUARIO = u.ID
				JOIN 
					PERSONA p ON u.ID_PERSONA = p.ID
				""";
				
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery(sql)) {						
			while (rs.next()) {
				
				// Extraer información de la moneda	 
				char tipo = rs.getString("TIPO").charAt(0);
				String nomenclatura = rs.getString("NOMENCLATURA");
	            String nombre = rs.getString("NOMBRE");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");	            
	            String nombreIcono = rs.getString("NOMBRE_ICONO");
	            double stock = rs.getDouble("STOCK");	            
	            // Crear objeto FIAT
	            FIAT fiat = new FIAT(tipo, nombre, nomenclatura, valorDolar, nombreIcono, stock);	            
	            
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

	            // Extraer cantidad y crear el objeto ActivoFIAT
	            double cantidad = rs.getDouble("CANTIDAD");
	            ActivoFIAT activo = new ActivoFIAT(usuario, fiat, cantidad);
	            
	            // Agregar el activo a la lista
	            lista_activos.add(activo);								
				
			}
			connection.close();
			return lista_activos;
		}		
		
	}

}
