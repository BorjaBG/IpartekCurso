package com.ipartek.borja.modelos;

import com.ipartek.borja.utilidades.Biblioteca;

public class Trabajador {

	private static final String REGEX_DNI = "[XYZ\\d]\\d{7}[A-Z]";
	private static final String EXCEPTION_NOMBRE_APELLIDOS = "Solo se admiten caracteres de tipo letra y espacio";
	private static final String REGEX_NOMBRE_APELLIDOS = "[\\p{L} ]+";
	private int id;
	private String nombre;
	private String apellidos;
	private String dni;
	
	public Trabajador() {
		
	}
	
	public Trabajador(int id, String nombre, String apellidos, String dni) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setDni(dni);
	}
	
	public Trabajador(String nombre, String apellidos, String dni) {
		setNombre(nombre);
		setApellidos(apellidos);
		setDni(dni);
	}
	
	public Trabajador(String nombre) {
		setNombre(nombre);
	}
	
	public Trabajador(String nombre, String apellidos) {
		setNombre(nombre);
		setApellidos(apellidos);
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		
		if(nombre == null || nombre.trim().length() == 0) {
			throw new ModelosException("El nombre es un campo obligatorio");
		}
		
		if(!nombre.matches(REGEX_NOMBRE_APELLIDOS)) {
			throw new ModelosException(EXCEPTION_NOMBRE_APELLIDOS);
		}

		if(nombre.length() < 2 && nombre.length() > 41) {
			throw new ModelosException("El nombre debe contener entre 2 y 40 caracteres");
		}
		
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		
		if(apellidos == null || apellidos.trim().length() == 0) {
			throw new ModelosException("Los apellidos son un campo obligatorio");
		}
		
		if(!apellidos.matches(REGEX_NOMBRE_APELLIDOS)) {
			throw new ModelosException(EXCEPTION_NOMBRE_APELLIDOS);
		}

		if(apellidos.length() < 2 && nombre.length() > 101) {
			throw new ModelosException("Los apellidos deben contener entre 2 y 100 caracteres");
		}
		
		this.apellidos = apellidos;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		
		if(!dni.matches(REGEX_DNI)) {
			throw new ModelosException("No concuerda con el formato de un DNI");
		}
		
		//Comentado por que fallo con DNIs de la base de datos que no estan validados
		/*if(!Biblioteca.dniValido(dni)) {
			throw new ModelosException("El DNI introducido no es valido");
		}*/
		
		this.dni = dni;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trabajador other = (Trabajador) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + "]";
	}
	
	
}
