package com.ipartek.formacion.ejemploaccesodatos.accesodatos;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.util.TreeMap;

import com.ipartek.formacion.ejemploaccesodatos.entidades.Persona;

public class PersonaMemoria implements Crudable<Persona> {

	private static TreeMap<Long, Persona> personas = new TreeMap<>();
	
	
	//SINGLETON
	
	private PersonaMemoria() {
		/*personas.put(1L, new Persona(1L, "Javier", "Lete"));
		personas.put(2L, new Persona(2L, "Pepe", "Perez"));*/
		PersonaMemoria.CargarDatosInicio("C:\\Users\\curso\\Desktop\\bbdd.txt");
		
	}
	
	private final static PersonaMemoria INSTANCIA = new PersonaMemoria();
	
	public static PersonaMemoria getInstancia() {
		return INSTANCIA;
	}
	
	//FIN SINGLETON
	
	public static void CargarDatosInicio(String rutaFichero) {
		Persona p = new Persona();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		File origen = new File(rutaFichero);
		 try {
			 fis = new FileInputStream(origen);
			 ois = new ObjectInputStream(fis);
			 try {
				 for(long i = 0;;i++) {
					 p = (Persona) ois.readObject();
					 personas.put(i, p);
				 }
			 } catch(EOFException | SocketTimeoutException e) {
				 //
			 }
			 
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Hola");
			//e1.printStackTrace();
		} 
	}
	
	
	@Override
	public Iterable<Persona> getAll() {
		return personas.values();
	}

	@Override
	public Persona getById(Long id) {
		//TODO Implementar getById
		throw new AccesoDatosException("No esta implementado");
	}

	@Override
	public Persona insert(Persona persona) {
		personas.put(persona.getId(), persona);
		return persona;
	}

	@Override
	public Persona update(Persona persona, String Nombre, String Apellidos, String opcion, Long id) {
		//TODO Implementar update
		throw new AccesoDatosException("No esta implementado");
	}

	@Override
	public Persona delete(Persona persona) {
		
		//TODO Implementar delete por persona
		throw new AccesoDatosException("No esta implementado");
	}

	@Override
	public Persona delete(Long id) {
		return personas.remove(id);
		//TODO Implementar delete por id
		//throw new AccesoDatosException("No est� implementado");
	}

}
