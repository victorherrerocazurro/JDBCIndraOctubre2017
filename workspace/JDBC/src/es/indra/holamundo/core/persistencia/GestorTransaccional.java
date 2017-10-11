package es.indra.holamundo.core.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorTransaccional {

	private Connection connection;

	public void iniciarTransaccion() throws Exception {
		// 2-Obtener la conexion
		Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "user", "user");
		// desactivar el autocommit
		connection.setAutoCommit(false);
	}

	public void commit() throws Exception {
		// Al configurar el autocommit a false, hay que realizar el commit manualmente
		connection.commit();
	}

	public void rollback() throws Exception {
		connection.rollback();
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
