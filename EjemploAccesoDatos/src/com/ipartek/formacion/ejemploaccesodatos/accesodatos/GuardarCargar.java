package com.ipartek.formacion.ejemploaccesodatos.accesodatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


import com.ipartek.formacion.ejemploaccesodatos.entidades.Persona;

public class GuardarCargar {
	
	public static void guardarDatos(String rutaFichero) {
		Crudable<Persona> dao = PersonaMemoria.getInstancia();
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
			
		try {
			fos = new FileOutputStream(new File(rutaFichero));
			oos = new ObjectOutputStream(fos);
				
			for(Persona persona: dao.getAll()) {
				//System.out.println(persona);
				Persona p1 = new Persona(persona.getId(),persona.getNombre(),persona.getApellidos());
				System.out.println(p1);
				oos.writeObject(p1);
			}
			
		} catch (FileNotFoundException e1) {
			throw new RuntimeException("No se ha encontrado el archivo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void CargarDatos() {
		/*Persona p = new Persona();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		File origen = new File(ARCHIVO_BBDD);
		PersonaMemoria.class.
		//File origen = new File("C:\\Users\\curso\\Desktop\\pruebaCSV.txt");
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
		}*/
	}

}
