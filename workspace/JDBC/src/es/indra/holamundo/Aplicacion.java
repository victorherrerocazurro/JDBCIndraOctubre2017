package es.indra.holamundo;

import java.util.List;

import es.indra.holamundo.core.persistencia.PersonaDao;
import es.indra.holamundo.entidades.Persona;

public class Aplicacion {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Persona p1 = new Persona("Juan", "Martinez", 52);
		Object p2 = new Persona("Juan", "Martinez", 52);
				
		//1-Cargar el driver en memoria
		Class.forName("org.apache.derby.jdbc.ClientDriver40"); //TODAVIA NO SABEMOS DONDE PONERLO, PERO DEBE ESTAR
		
		PersonaDao personaDao = new PersonaDao();
		
		try {
			personaDao.insertarPersona(p1);
			
			List<Persona> personas = personaDao.consultarPersona();
			
			System.out.println(personas);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
