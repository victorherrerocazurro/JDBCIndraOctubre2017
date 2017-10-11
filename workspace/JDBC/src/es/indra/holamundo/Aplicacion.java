package es.indra.holamundo;

import java.util.List;

import org.apache.derby.jdbc.BasicClientDataSource40;

import es.indra.holamundo.core.persistencia.GestorTransaccional;
import es.indra.holamundo.core.persistencia.PersonaDao;
import es.indra.holamundo.entidades.Persona;

public class Aplicacion {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Persona p1 = new Persona("Juan", "Martinez", 52);
		Persona p2 = new Persona("Maria", "PErez", 32);
				
		//1-Cargar el driver en memoria
		Class.forName("org.apache.derby.jdbc.ClientDriver40"); //TODAVIA NO SABEMOS DONDE PONERLO, PERO DEBE ESTAR
		
		//jdbc:derby://localhost:1527/sample", "user", "user
		BasicClientDataSource40 dataSource = new BasicClientDataSource40();
		dataSource.setUser("user");
		dataSource.setPassword("user");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("sample");
		dataSource.setPortNumber(1527);
		
		GestorTransaccional gestorTransacional = new GestorTransaccional(dataSource);
		PersonaDao personaDao = new PersonaDao(gestorTransacional);
		
		
		List<Persona> personas = null;
		try {
			gestorTransacional.iniciarTransaccion();
			
			personaDao.insertarPersona(p1);
			
			personaDao.insertarPersona(p2);
			
			personaDao.borrarPersonaPorId(1);
			
			personas = personaDao.consultarPersona();
			
			gestorTransacional.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			gestorTransacional.rollback();
		}
		
		System.out.println(personas);
		
	}

}
