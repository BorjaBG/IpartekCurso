package com.ipartek.borja.modelos;

public class Reseña {
	
	private int id;
	private String valoracion;
	private String resena;
	
	public Reseña(int id, String valoracion, String resena) {
		setId(id);
		setValoracion(valoracion);
		setResena(resena);
	}
	
	public Reseña(String valoracion, String resena) {
		this(0, valoracion, resena);
	}
	
	public Reseña() {
		
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
		Reseña other = (Reseña) obj;
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
