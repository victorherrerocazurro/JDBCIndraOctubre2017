package es.indra.holamundo.core.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.indra.holamundo.entidades.Persona;

public class PersonaDao {

	private GestorTransaccional gestorTransacional;
	
	public PersonaDao(GestorTransaccional gestorTransacional) {
		super();
		this.gestorTransacional = gestorTransacional;
	}

	public void crearTablaPersonas() throws Exception {
			
			String sql = "CREATE TABLE APP.PERSONAS(" + "ID INT GENERATED ALWAYS AS IDENTITY,"
					+ "NOMBRE VARCHAR(255)," + "APELLIDO VARCHAR(255)," + "EDAD INT," + "PRIMARY KEY (ID)" + ")";
			
			gestorTransacional.executeUpdate(sql, new Object[] {});
	}

	// Insertar un registro en la base de datos con la informacion de un persona
	public void insertarPersona(Persona persona) throws Exception {

			String sql = "insert into APP.PERSONAS(NOMBRE, APELLIDO, EDAD) values(? ,? ,?)";

			gestorTransacional.executeUpdate(sql, new Object[] {persona.getNombre(), persona.getApellido(), persona.getEdad()});
	}

	// Borrar un registro en la base de datos con la informacion de un persona
	public void borrarPersonaPorId(long id) throws Exception {

			String sql = "delete from APP.PERSONAS where ID = ?";

			gestorTransacional.executeUpdate(sql, new Object[] {id});
			
			//throw new Exception();
	}

	// Consultar un registro en la base de datos por su Id
	public Persona consultarPersonaPorId(long id) throws Exception {

			String sql = "select * from APP.PERSONAS where ID = ?";

			ResultSet resultSet = gestorTransacional.executeQuery(sql, new Object[] {id});

			// Me aseguro que el cursor esta en la posicion inicial, que es la posicion
			// antorior al primer registro valido
			//resultSet.beforeFirst();

			if (resultSet.next()) {
				// Es prioritario conocer la proyeccion
				return new Persona(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
						resultSet.getString("APELLIDO"), resultSet.getInt("EDAD"));
			} else {
				return null;
			}
	}

	// Consultar todos los registros en la base de datos
	public List<Persona> consultarPersona() throws Exception {

		ArrayList<Persona> resultado = new ArrayList<Persona>();

			String sql = "select * from APP.PERSONAS";

			ResultSet resultSet = gestorTransacional.executeQuery(sql, new Object[] {});
			
			// Me aseguro que el cursor esta en la posicion inicial, que es la posicion
			// antorior al primer registro valido
			//resultSet.beforeFirst();

			while (resultSet.next()) {
				// Es prioritario conocer la proyeccion
				resultado.add(new Persona(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
						resultSet.getString("APELLIDO"), resultSet.getInt("EDAD")));
			}
			
			return resultado;
	}

}
