package com.ipartek.formacion.personas.repositirio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.ipartek.formacion.personas.modelos.Persona;

public class FabricaDao {
	public String pathConfiguracion;
	public String tipo;
	
	//SINGLETON
	
	private static FabricaDao instancia;
	
	public static FabricaDao getInstancia(String pathConfiguracion) {
		if(instancia == null) {
			instancia = new FabricaDao(pathConfiguracion);
		}
		return instancia;
	}
	
	private FabricaDao(String pathConfiguracion) {
		
		this.pathConfiguracion = pathConfiguracion;
		
		Properties configuracion = new Properties();
		
		try {
			configuracion.load(new FileInputStream(pathConfiguracion));
			tipo = configuracion.getProperty("tipo");
		} catch (FileNotFoundException e) {
			throw new RuntimeException("No se ha podido encontrar el archivo", e);
		} catch (IOException e) {
			throw new RuntimeException("Ha ocurrido un error inesperado", e);
		}
		
	}
	
	public Dao<Persona> getInstanciaPersona(String tipo){
		switch(tipo) {
		case "memoria":
			return PersonaTreeMap.getInstancia();
		case "mysql":
			return PersonaMySQL.getInstancia(pathConfiguracion);
		default:
			throw new RuntimeException("No conozco el tipo " + tipo);
		}
	
	}
}
