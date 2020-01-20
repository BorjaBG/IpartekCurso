package com.ipartek.formacion.personas.repositirio;

import java.util.TreeMap;
import com.ipartek.formacion.personas.modelos.Persona;

public class PersonaTreeMap implements Dao<Persona> {
	
	private TreeMap<Long, Persona> personas = new TreeMap<>();
	
	//Inicio SINGLETON
	
	private final static PersonaTreeMap INSTANCIA = new PersonaTreeMap();
	
	private PersonaTreeMap() {
		agregar(new Persona("Borja", "Bueno"));
		agregar(new Persona("Pepe", "Perez"));
		agregar(new Persona("Aitor", "Tilla"));
	}
	
	public static PersonaTreeMap getInstancia() { return INSTANCIA; }
	
	//Fin SINGLETON

	@Override
	public Iterable<Persona> obtenerTodos() {
		return personas.values();
	}

	@Override
	public Persona obtenerPorId(Long id) {
		return personas.get(id);
	}

	@Override
	public void agregar(Persona persona) {
		//Comprobamos si hay algun id, si no ponemos id a 1 y si ya tenemos le sumamos 1
		Long id = personas.size() == 0 ? 1L : personas.lastKey() + 1;
		persona.setId(id);
		personas.put(id, persona);
	}

	@Override
	public void eliminar(Long id) {
		personas.remove(id);
	}

	@Override
	public void actualizar(Persona persona) {
		personas.put(persona.getId(), persona);
	}

}
