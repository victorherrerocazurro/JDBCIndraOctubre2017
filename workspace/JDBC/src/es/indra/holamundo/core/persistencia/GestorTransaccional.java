package es.indra.holamundo.core.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class GestorTransaccional {

	private DataSource dataSource;
	private Connection connection;
	
	public GestorTransaccional(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void iniciarTransaccion() throws Exception {
		// 2-Obtener la conexion
		//connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "user", "user");
		connection = dataSource.getConnection();
		// desactivar el autocommit
		connection.setAutoCommit(false);
	}

	public void commit() throws Exception {
		try {
			// Al configurar el autocommit a false, hay que realizar el commit manualmente
			connection.commit();
		} finally {
			connection.close();
		}
		
	}

	public void rollback() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet executeQuery(String sql, Object[] parametros) throws Exception {
		PreparedStatement prepareStatement = connection.prepareStatement(sql);

		for (int i = 0; i < parametros.length; i++) {
			prepareStatement.setObject(i + 1, parametros[i]);
		}

		return prepareStatement.executeQuery();
	}

	public void executeUpdate(String sql, Object[] parametros) throws Exception {
		PreparedStatement prepareStatement = connection.prepareStatement(sql);

		for (int i = 0; i < parametros.length; i++) {
			prepareStatement.setObject(i + 1, parametros[i]);
		}

		prepareStatement.executeUpdate();
	}

}
