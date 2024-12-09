package daosJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import aplicacion.MiDataSource;
import clases.Criptomoneda;
import clases.FIAT;
import clases.Moneda;
import daos.MonedaDAO;


public class MonedaDAOJDBC implements MonedaDAO<Moneda> {

	private DataSource dataSource;

	// Constructor para inicializar el DataSource
	public MonedaDAOJDBC() {
		this.dataSource = MiDataSource.getDataSource();
	}

	// Método privado para obtener la conexión con cuidado de las excepciones
	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	// -----METODOS PARA
	// FIATS-------------------------------------------------------
	@Override
	public int insertarFIAT(FIAT f) throws SQLException {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, NOMBRE_ICONO) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			// Establecemos los parámetros de la consulta
			pstmt.setString(1, "F"); // Tipo 'F' para FIAT
			pstmt.setString(2, f.getNombre()); // Nombre de fiat
			pstmt.setString(3, f.getNomenclatura()); // Nomenclatura de fiat
			pstmt.setDouble(4, f.getValorDolar()); // Valor en dólares de fiat
			pstmt.setString(5, f.getNombreIcono()); // Nombre del ícono de fiat

			pstmt.executeUpdate();

			// Obtener el id generado
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					f.setIdMoneda(generatedKeys.getInt(1));
					return f.getIdMoneda();
				} else
					throw new SQLException("No se pudo obtener el ID generado para la moneda FIAT.");
			}
		}
	}

	@Override
	public void actualizarFIATConID(FIAT f) throws SQLException {
		String sql = "UPDATE MONEDA SET TIPO = ?, NOMBRE = ?, NOMENCLATURA = ?, VALOR_DOLAR = ?, NOMBRE_ICONO = ? WHERE ID = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, "F"); // Tipo 'F' para FIAT
			pstmt.setString(2, f.getNombre()); // Nombre de FIAT
			pstmt.setString(3, f.getNomenclatura()); // Nomenclatura de FIAT
			pstmt.setDouble(4, f.getValorDolar()); // Valor en dólares de FIAT
			pstmt.setString(5, f.getNombreIcono()); // Nombre del ícono de FIAT
			pstmt.setInt(6, f.getIdMoneda()); // Usar el ID de la FIAT para la actualización

			pstmt.executeUpdate();
			connection.close();
		}
	}

	// Actualizar FIAT con Nomenclatura
	@Override
	public void actualizarFIAT(FIAT f) throws SQLException {
		String sql = "UPDATE MONEDA SET TIPO = ?, NOMBRE = ?, VALOR_DOLAR = ?, NOMBRE_ICONO = ? WHERE NOMENCLATURA = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, "F"); // Tipo 'F' para FIAT
			pstmt.setString(2, f.getNombre()); // Nombre de FIAT
			pstmt.setDouble(3, f.getValorDolar()); // Valor en dólares de FIAT
			pstmt.setString(4, f.getNombreIcono()); // Nombre del ícono de FIAT
			pstmt.setString(5, f.getNomenclatura()); // Uso la Nomenclatura de FIAT para actualizar

			pstmt.executeUpdate();
			connection.close();
		}
	}

	// Buscar FIAT por ID
	@Override
	public FIAT buscarFIAT(int idFIAT) throws SQLException {
		String sql = "SELECT * FROM MONEDA WHERE ID = ? AND TIPO = 'F'";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, idFIAT); // Buscamos FIAT por su ID

			try (ResultSet rs = pstmt.executeQuery()) {
				// Si se encuentra la moneda FIAT creo y devuelvo el objeto
				if (rs.next()) {
					return new FIAT(idFIAT, // ID de la moneda
							rs.getString("NOMBRE"), // Nombre de la moneda
							rs.getString("NOMENCLATURA"), // Nomenclatura
							rs.getDouble("VALOR_DOLAR"), // Valor en dólares
							rs.getString("NOMBRE_ICONO") // Nombre del ícono
					);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar FIAT por ID: " + e.getMessage());
			throw e;
		}
		return null; // Si no se encuentra la moneda, retorna null

	}

	// Buscar FIAT por Nomenclatura
	@Override
	public FIAT buscarFIAT(String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ? AND TIPO = 'F'";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, nomenclatura); // Buscamos FIAT por su nomenclatura

			try (ResultSet rs = pstmt.executeQuery()) {
				// Si se encuentra la moneda FIAT creo y devuelvo el objeto
				if (rs.next()) {
					return new FIAT(rs.getInt("ID"), // ID de la moneda
							rs.getString("NOMBRE"), // Nombre de la moneda
							nomenclatura, // Nomenclatura
							rs.getDouble("VALOR_DOLAR"), // Valor en dólares
							rs.getString("NOMBRE_ICONO") // Nombre del ícono
					);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar FIAT por nomenclatura: " + e.getMessage());
			throw e;
		}
		return null; // Si no se encuentra la moneda, retorna null

	}

	public List<FIAT> listarFIATS() throws SQLException {

		List<FIAT> fiats = new LinkedList<FIAT>();

		// SQL para obtener todas las monedas
		String sql = "SELECT * FROM MONEDA WHERE TIPO = 'F'";

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				// Obtengo los datos de cada fiat
				int idMoneda = rs.getInt("ID");
				String nombre = rs.getString("NOMBRE");
				String nomenclatura = rs.getString("NOMENCLATURA");
				double valorDolar = rs.getDouble("VALOR_DOLAR");
				String nombreIcono = rs.getString("NOMBRE_ICONO");

				FIAT fiat = new FIAT(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono);

				fiats.add(fiat);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar las FIATS: " + e.getMessage());
			throw e;
		}

		return fiats;
	}

	// -----METODOS PARA
	// CRIPTOS-------------------------------------------------------
	@Override
	public int insertarCriptomoneda(Criptomoneda c) throws SQLException {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, NOMBRE_ICONO) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			// Establecemos los parámetros de la consulta
			pstmt.setString(1, "C"); // Tipo 'C' para CRIPTO
			pstmt.setString(2, c.getNombre()); // Nombre de CRIPTO
			pstmt.setString(3, c.getNomenclatura()); // Nomenclatura de CRIPTO
			pstmt.setDouble(4, c.getValorDolar()); // Valor en dólares de CRIPTO
			pstmt.setDouble(5, c.getVolatilidad()); // Volatilidad de CRIPTO
			pstmt.setString(6, c.getNombreIcono()); // Nombre del ícono de CRIPTO

			pstmt.executeUpdate();

			// Obtener el id generado
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					c.setIdMoneda(generatedKeys.getInt(1));
					return c.getIdMoneda();
				} else
					throw new SQLException("No se pudo obtener el ID generado para la Criptomoneda.");
			}
		}
	}

	@Override
	public void actualizarCriptomonedaConID(Criptomoneda c) throws SQLException {
		String sql = "UPDATE MONEDA SET TIPO = ?, NOMBRE = ?, NOMENCLATURA = ?, VALOR_DOLAR = ?, VOLATILIDAD = ?, NOMBRE_ICONO = ? WHERE ID = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, "C"); // Tipo 'C' para CRIPTO
			pstmt.setString(2, c.getNombre()); // Nombre de la CRIPTO
			pstmt.setString(3, c.getNomenclatura()); // Nomenclatura de la CRIPTO
			pstmt.setDouble(4, c.getValorDolar()); // Valor en dólares de la CRIPTO
			pstmt.setDouble(5, c.getVolatilidad()); // Valor en dólares de la CRIPTO
			pstmt.setString(6, c.getNombreIcono()); // Nombre del ícono de la CRIPTO
			pstmt.setInt(7, c.getIdMoneda()); // Usar el ID de la CRIPTO para la actualización

			pstmt.executeUpdate();
			connection.close();
		}
	}

	// Actualizar Criptomoneda por Nomenclatura
	@Override
	public void actualizarCriptomoneda(Criptomoneda c) throws SQLException {
		String sql = "UPDATE MONEDA SET TIPO = ?, NOMBRE = ?, VALOR_DOLAR = ?, VOLATILIDAD = ?, NOMBRE_ICONO = ? WHERE NOMENCLATURA = ?";
		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, "C"); // Tipo 'C' para Cripto
			pstmt.setString(2, c.getNombre()); // Nombre de Cripto
			pstmt.setDouble(3, c.getValorDolar()); // Valor en dólares de Cripto
			pstmt.setDouble(4, c.getVolatilidad()); // Volatilidad de Cripto
			pstmt.setString(5, c.getNombreIcono()); // Nombre del ícono de Cripto
			pstmt.setString(6, c.getNomenclatura()); // Uso la Nomenclatura de Cripto para actualizar

			pstmt.executeUpdate();
			connection.close();
		}
	}

	// Buscar Criptomoneda por ID
	@Override
	public Criptomoneda buscarCriptomoneda(int idCriptomoneda) throws SQLException {
		String sql = "SELECT * FROM MONEDA WHERE ID = ? AND TIPO = 'C'";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, idCriptomoneda); // Buscamos Cripto por su ID

			try (ResultSet rs = pstmt.executeQuery()) {
				// Si se encuentra la moneda Cripto creo y devuelvo el objeto
				if (rs.next()) {
					return new Criptomoneda(idCriptomoneda, // ID de la moneda
							rs.getString("NOMBRE"), // Nombre de la moneda
							rs.getString("NOMENCLATURA"), // Nomenclatura
							rs.getDouble("VALOR_DOLAR"), // Valor en dólares
							rs.getString("NOMBRE_ICONO"), // Nombre del ícono,
							rs.getDouble("VOLATILIDAD") // Volatilidad

					);
				}
			}

		} catch (SQLException e) {
			System.out.println("Error al buscar Cripto por ID: " + e.getMessage());
			throw e;
		}
		return null; // Si no se encuentra la moneda, retorna null

	}

	// Buscar Criptomoneda por Nomenclatura
	@Override
	public Criptomoneda buscarCriptomoneda(String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ? AND TIPO = 'C'";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, nomenclatura); // Buscamos Cripto por su ID

			try (ResultSet rs = pstmt.executeQuery()) {
				// Si se encuentra la moneda Cripto creo y devuelvo el objeto
				if (rs.next()) {
					return new Criptomoneda(rs.getInt("ID"), // ID de la moneda
							rs.getString("NOMBRE"), // Nombre de la moneda
							nomenclatura, // Nomenclatura
							rs.getDouble("VALOR_DOLAR"), // Valor en dólares
							rs.getString("NOMBRE_ICONO"), // Nombre del ícono,
							rs.getDouble("VOLATILIDAD") // Volatilidad

					);
				}
			}

		} catch (SQLException e) {
			System.out.println("Error al buscar Cripto por ID: " + e.getMessage());
			throw e;
		}
		return null; // Si no se encuentra la moneda, retorna null

	}

	public List<Criptomoneda> listarCriptomonedas() throws SQLException {

		List<Criptomoneda> criptomonedas = new LinkedList<Criptomoneda>();

		// SQL para obtener todas las monedas
		String sql = "SELECT * FROM MONEDA WHERE TIPO = 'C'";

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				// Obtengo los datos de cada cripto
				int idMoneda = rs.getInt("ID");
				String nombre = rs.getString("NOMBRE");
				String nomenclatura = rs.getString("NOMENCLATURA");
				double valorDolar = rs.getDouble("VALOR_DOLAR");
				String nombreIcono = rs.getString("NOMBRE_ICONO");
				double volatilidad = rs.getDouble("VOLATILIDAD");

				Criptomoneda criptomoneda = new Criptomoneda(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono, volatilidad);

				criptomonedas.add(criptomoneda);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar las criptos: " + e.getMessage());
			throw e;
		}

		return criptomonedas;
	}

	// -----METODOS PARA MONEDAS-------------------------------------------------------
	
	@Override
	public TipoDeMoneda buscarMonedaPorNomenclaturaTipo(String nomenclatura) throws SQLException{
		String sql = "SELECT TIPO FROM MONEDA WHERE NOMENCLATURA = ?";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nomenclatura);
			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					if(rs.getString("TIPO").charAt(0) == 'C')
						return TipoDeMoneda.CRIPTOMONEDA;
					return TipoDeMoneda.FIAT;
				}

				return TipoDeMoneda.NULO;
			}
		}
	}
	
	@Override
	public Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException {
		// SQL para buscar la moneda por nomenclatura
		String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ?";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, nomenclatura);
			try (ResultSet rs = pstmt.executeQuery()) {
				// Si se encuentra la moneda, crear y devolver el objeto Moneda correspondiente

				if (rs.next()) {

					int idMoneda = rs.getInt("ID");
					char tipo = rs.getString("TIPO").charAt(0);
					String nombre = rs.getString("NOMBRE");
					double valorDolar = rs.getDouble("VALOR_DOLAR");
					String nombreIcono = rs.getString("NOMBRE_ICONO");

					// Verificar si la moneda es una Criptomoneda o FIAT
					if (tipo == 'C') {
						// Retornar una instancia de Criptomoneda
						double volatilidad = rs.getDouble("VOLATILIDAD");
						return new Criptomoneda(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono, volatilidad);
					} else if (tipo == 'F') {
						// Retornar una instancia de FIAT
						return new FIAT(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono);
					}
				}
			}
		}
		// Si llegó hasta acá es porque hubo un error
		System.out.println("Error");
		return null;
	}

	@Override
	public Moneda buscarMonedaPorID(int idMoneda) throws SQLException {
		// SQL para buscar la moneda por ID
		String sql = "SELECT * FROM MONEDA WHERE ID = ?";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, idMoneda);
			try (ResultSet rs = pstmt.executeQuery()) {
				// Si se encuentra la moneda, crear y devolver el objeto Moneda correspondiente

				if (rs.next()) {
					char tipo = rs.getString("TIPO").charAt(0);
					String nombre = rs.getString("NOMBRE");
					String nomenclatura = rs.getString("NOMENCLATURA");
					double valorDolar = rs.getDouble("VALOR_DOLAR");
					String nombreIcono = rs.getString("NOMBRE_ICONO");

					// Verificar si la moneda es una Criptomoneda o FIAT
					if (tipo == 'C') {
						// Retornar una instancia de Criptomoneda
						double volatilidad = rs.getDouble("VOLATILIDAD");
						return new Criptomoneda(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono, volatilidad);
					} else if (tipo == 'F') {
						// Retornar una instancia de FIAT
						return new FIAT(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono);
					}
				}
			}
		}
		// Si llegó hasta acá es porque hubo un error
		System.out.println("Error");
		return null;
	}

	// Elimina moneda por su nomenclatura
	@Override
	public void eliminarMonedaPorNomenclatura(String nomenclatura) throws SQLException {
		// SQL para eliminar la moneda por su nomenclatura
		String sql = "DELETE FROM MONEDA WHERE NOMENCLATURA = ?";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, nomenclatura);
			pstmt.executeUpdate();
		}

		catch (SQLException e) {
			System.out.println("Error al eliminar la moneda: " + e.getMessage());
			throw e;
		}
	}

	// Elimina moneda por su ID
	@Override
	public void eliminarMonedaPorID(int idMoneda) throws SQLException {
		// SQL para eliminar la moneda por su id
		String sql = "DELETE FROM MONEDA WHERE ID = ?";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, idMoneda);
			pstmt.executeUpdate();
		}

		catch (SQLException e) {
			System.out.println("Error al eliminar la moneda: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Moneda> listarMonedas() throws SQLException {
		List<Moneda> monedas = new LinkedList<>();
		// SQL para obtener todas las monedas
		String sql = "SELECT * FROM MONEDA";

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				// Obtengo los datos de cada moneda
				int idMoneda = rs.getInt("ID");
				char tipo = rs.getString("TIPO").charAt(0);
				String nombre = rs.getString("NOMBRE");
				String nomenclatura = rs.getString("NOMENCLATURA");
				double valorDolar = rs.getDouble("VALOR_DOLAR");
				String nombreIcono = rs.getString("NOMBRE_ICONO");

				// Creo una Cripto o FIAT según corresponda
				Moneda moneda;
				if (tipo == 'C') {
					// creo una Criptomoneda
					double volatilidad = rs.getDouble("VOLATILIDAD");
					moneda = new Criptomoneda(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono, volatilidad);
				} else if (tipo == 'F') {
					// creo un FIAT
					moneda = new FIAT(idMoneda, nombre, nomenclatura, valorDolar, nombreIcono);
				} else {
					// Si hay error en la identifiación del tipo me lo salteo
					continue;
				}

				// Agregar la moneda a la lista
				monedas.add(moneda);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar las monedas: " + e.getMessage());
			throw e;
		}

		// Retornar la lista de monedas
		return monedas;
	}

}
