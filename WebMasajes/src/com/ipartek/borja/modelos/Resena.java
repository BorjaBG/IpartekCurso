package com.ipartek.borja.modelos;

public class Resena {
	
	private static final String REGEX_RESENA = "[\\p{L}\\d ?¿!¡,.:;]+";
	private int id;
	private String valoracion;
	private String resena;
	
	public Resena(int id, String valoracion, String resena) {
		setId(id);
		setValoracion(valoracion);
		setResena(resena);
	}
	
	public Resena(String valoracion, String resena) {
		this(0, valoracion, resena);
	}
	
	public Resena(String valoracion) {
		setValoracion(valoracion);
	}
	
	public Resena() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public String getReseña() {
		return resena;
	}

	public void setResena(String resena) {
		
		if(resena == null || resena.trim().length() == 0) {
			
		} else {
			if(!resena.matches(REGEX_RESENA)) {
				throw new ModelosException("Solo se admiten carcateres alfanumericos y caracteres especiales (¿?!¡.:,;)");
			}
			
			if(resena.length() < 2 && resena.length() > 401) {
				throw new ModelosException("La reseña debe contener entre 2 y 400 caracteres");
			}
			
		}
		
		this.resena = resena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((resena == null) ? 0 : resena.hashCode());
		result = prime * result + ((valoracion == null) ? 0 : valoracion.hashCode());
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
		Resena other = (Resena) obj;
		if (id != other.id)
			return false;
		if (resena == null) {
			if (other.resena != null)
				return false;
		} else if (!resena.equals(other.resena))
			return false;
		if (valoracion == null) {
			if (other.valoracion != null)
				return false;
		} else if (!valoracion.equals(other.valoracion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reseña [id=" + id + ", valoracion=" + valoracion + ", reseña=" + resena + "]";
	}
	
	
	
}
