package com.ipartek.borja.modelos;

public class Servicio {

	private static final String REGEX_NOMBRE = "[\\\\p{L} ]+";
	private int id;
	private String nombre;
	private double precio;

	public Servicio(int id, String nombre, double precio) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
	}
	
	public Servicio(String nombre, double precio) {
		this(0, nombre, precio);
	}
	
	public Servicio(String nombre) {
		setNombre(nombre);
	}
	
	public Servicio() {
		
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
		
		if(!nombre.matches(REGEX_NOMBRE)) {
			throw new ModelosException("Solo se admiten caracteres de tipo letra y espacios");
		}

		if(nombre.length() < 2 && nombre.length() > 51) {
			throw new ModelosException("El nombre del servicio debe contener entre 2 y 50 caracteres");
		}
		
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		
		if(precio == 0) {
			throw new ModelosException("El precio es un campo obligatorio");
		}
		
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Servicio other = (Servicio) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
	
	
}
