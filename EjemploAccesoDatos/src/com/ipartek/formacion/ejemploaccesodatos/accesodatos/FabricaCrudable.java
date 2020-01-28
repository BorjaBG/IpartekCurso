package com.ipartek.formacion.ejemploaccesodatos.accesodatos;

import com.ipartek.formacion.ejemploaccesodatos.entidades.Persona;

public class FabricaCrudable {
	
	public static Crudable<Persona> getInstancia(String tipo){
		switch(tipo) {
		case "memoria":
			System.out.println("Estoy en la memoria");
			return PersonaMemoria.getInstancia();
		case "mysql":
			return PersonaMySQL.getInstancia();
		default:
			throw new AccesoDatosException("No conozco el tipo " + tipo);
		}
		
	}

}
