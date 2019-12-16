package com.ipartek.formacion.ejemploaccesodatos.presentacionconsola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ipartek.formacion.ejemploaccesodatos.accesodatos.Crudable;
import com.ipartek.formacion.ejemploaccesodatos.accesodatos.GuardarCargar;
import com.ipartek.formacion.ejemploaccesodatos.accesodatos.PersonaMemoria;
import com.ipartek.formacion.ejemploaccesodatos.entidades.Persona;

public class PresentacionConsola {

	private static final String SALIR_OPCION_ELEGIDA = "¿Desea salir(0) o quedarse(1)?: ";

	public static void main(String[] args) {
		//DAO: Data Access Object
		final String ARCHIVO_BBDD = "C:\\Users\\curso\\Desktop\\bbdd.txt";
		Crudable<Persona> dao = PersonaMemoria.getInstancia();
		PersonaMemoria.CargarDatosInicio(ARCHIVO_BBDD);
		
		// TODO Eliminar una persona
		// TODO Importación Excel
		// TODO Guardar/Cargar
		
		//System.out.println(dao.insert(new Persona(3L, "Nuevo", "Nuevez")));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int opcionMenu = 0;
		long id = 0;
		int salirOpcion = 0;
		String menu;
		String texto;
		String nombre = "";
		String apellido = "";
		String cogerId;
		String salir;
		
		
		do {
			System.out.println("Eliga una de las opciones que apareceran a continuacion: \n" 
						+ "1.- Listado de personas \n"
						+ "2.- Introducir una persona en la BBDD \n"
						+ "3.- Buscar una persona \n"
						+ "4.- Eliminar una persona de la BBDD \n"
						+ "5.- Modificar una persona \n"
						+ "6.- Guardar BBDD en un archivo \n"
						+ "7.- Importar a un archivo CSV (no implementado) \n"
						+ "8.- Exportar a un archivo CSV \n"
						+ "0.- Salir de la aplicacion \n");
			
			try {
				menu = br.readLine();
				opcionMenu = Integer.parseInt(menu);
			} catch (IOException e) {
				System.out.println("Error");
			}
				
			switch(opcionMenu) {
			case 1:
				do {
					System.out.println("Aqui estan todas las personas disponibles en nuestra BBDD: \n");
					for(Persona persona: dao.getAll()) {
						System.out.println(persona);
					}
					
					System.out.println(SALIR_OPCION_ELEGIDA);
					try {
						salir = br.readLine();
						salirOpcion = Integer.parseInt(salir);
					} catch (IOException e) {
						System.out.println("Error");
					}
				}while(salirOpcion == 1);
				
				break;
			case 2:
				
				do {
					System.out.println("Introduzca el nombre de la persona: ");
					try {
						nombre = br.readLine();
					} catch (IOException e) {
						System.out.println("Error");
					}
					System.out.println("Introduzca el apellido de la persona: ");
					try {
						apellido = br.readLine();
					} catch (IOException e) {
						System.out.println("Error");
					}
					//Hacer ID automatica
					for(Persona persona: dao.getAll()) {
						//System.out.println(persona);
						id = persona.getId() + 1;
					}
					dao.insert(new Persona(id, nombre, apellido));
					
					System.out.println(SALIR_OPCION_ELEGIDA);
					try {
						salir = br.readLine();
						salirOpcion = Integer.parseInt(salir);
					} catch (IOException e) {
						System.out.println("Error");
					}
				}while(salirOpcion == 1);
				
				break;
			case 3:
				do {
					System.out.println("Introduzca la ID de la persona que quiere buscar: ");
					try {
						cogerId = br.readLine();
						id = Long.parseLong(cogerId);
					} catch (IOException e) {
						System.out.println("Error");
					}
					
					for(Persona persona: dao.getAll()) {
						if(persona.getId() == id) {
							System.out.println(persona);
							System.out.println(dao.getById(id));
						}
					}
					
					System.out.println(SALIR_OPCION_ELEGIDA);
					try {
						salir = br.readLine();
						salirOpcion = Integer.parseInt(salir);
					} catch (IOException e) {
						System.out.println("Error");
					}
				}while(salirOpcion == 1);
				
				break;
			case 4:
				do {
					System.out.println("Introduzca la ID de la persona que quiere eliminar de la BBDD: ");
					try {
						cogerId = br.readLine();
						id = Long.parseLong(cogerId);
					} catch (IOException e) {
						System.out.println("Error");
					}
					
					for(Persona persona: dao.getAll()) {
						if(persona.getId() == id) {
							System.out.println(persona.getNombre() + persona.getApellidos());
							dao.delete(id);
						}
					}
					
					System.out.println(SALIR_OPCION_ELEGIDA);
					try {
						salir = br.readLine();
						salirOpcion = Integer.parseInt(salir);
					} catch (IOException e) {
						System.out.println("Error");
					}
				}while(salirOpcion == 1);
				break;
			case 5:
				do {
					System.out.println("Introduzca la ID de la persona que quiere modificar de la BBDD: ");
					try {
						cogerId = br.readLine();
						id = Long.parseLong(cogerId);
					} catch (IOException e) {
						System.out.println("Error");
					}
					
					for(Persona persona: dao.getAll()) {
						if(persona.getId() == id) {
							System.out.println(persona);
							System.out.println("¿Que desea modificar, nombre o apellido?");
							try {
								texto = br.readLine();
								if(texto.matches("nombre")) {
									System.out.println("Introduzca el nuevo nombre: ");
									nombre = br.readLine();
									for(Persona persona2: dao.getAll()) {
										if(persona2.getId() == id) {
											persona2.setNombre(nombre);
										}
									}
								}else if (texto.matches("apellido")){
									System.out.println("Introduzca el nuevo apellido: ");
									apellido = br.readLine();
									for(Persona persona3: dao.getAll()) {
										if(persona3.getId() == id) {
											persona3.setApellidos(apellido);
										}
									}
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							
						}
					}
					
					
					System.out.println(SALIR_OPCION_ELEGIDA);
					try {
						salir = br.readLine();
						salirOpcion = Integer.parseInt(salir);
					} catch (IOException e) {
						System.out.println("Error");
					}
				}while(salirOpcion == 1);
				break;
			case 6:
				
				do {
					GuardarCargar.guardarDatos(ARCHIVO_BBDD);
					System.out.println("Archivo guardado con exito");
					
					System.out.println(SALIR_OPCION_ELEGIDA);
					try {
						salir = br.readLine();
						salirOpcion = Integer.parseInt(salir);
					} catch (IOException e) {
						System.out.println("Error");
					}
				}while(salirOpcion == 1);
				
				break;
				
			case 7:
				throw new RuntimeException("No implementado");
				
			case 8:
				FileWriter writer = null;
				File file = null;
				try {
					file = new File("C:\\Users\\curso\\Desktop\\pruebaCSV.csv");
			        writer = new FileWriter(file);
			        
			        for(Persona persona: dao.getAll()) {
						Persona p1 = new Persona(persona.getId(),persona.getNombre(),persona.getApellidos());
						writer.append(p1.getId().toString());
						writer.append(";");
						writer.append(p1.getNombre().intern());
						writer.append(";");
						writer.append(p1.getApellidos().intern());
						writer.append(";");
						writer.append("\n");
			            writer.flush();
					}
			        writer.close();
			        
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			case 0:
				
				
			}
		
		}while (opcionMenu != 0);
		
	}

}
