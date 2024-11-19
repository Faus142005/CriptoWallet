package clasesDAOjdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import clases.Transaccion;
import interfacesDAO.TransaccionDAO;

public class TransaccionDAOjdbc implements TransaccionDAO<Transaccion>{

	@Override
	public void insertarTransaccion(DataSource data, Transaccion t) throws SQLException {
		String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA) VALUES (?, ?)";
		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, t.getResumen());
		pstmt.setDate(2, t.getFecha_hora());		
		pstmt.executeUpdate();
	}
	
	@Override
	public void insertarTransaccionSoloResumen(DataSource data, Transaccion t) throws SQLException {
		String sql = "INSERT INTO TRANSACCION (RESUMEN) VALUES (?)";
		PreparedStatement pstmt = data.getConnection().prepareStatement(sql);
		pstmt.setString(1, t.getResumen());				
		pstmt.executeUpdate();
	}

	@Override
	public List<Transaccion> listarTransacciones(DataSource data) throws SQLException {
		List <Transaccion> l = new LinkedList<Transaccion>();
		
		String sql = "SELECT * FROM TRANSACCION";
		Statement stmt = data.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String resumen = rs.getString("RESUMEN");
			Date fecha_hora = rs.getDate("FECHA_HORA");
			
			Transaccion t=new Transaccion(resumen,fecha_hora);			
			
			l.add(t);			
		}	

		return l;
	}
	
}
