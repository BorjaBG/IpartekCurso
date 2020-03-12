package com.ipartek.mf0966_3.borja.modelos;

public class Valoracion {

	int idValoracion, alumno_codigo, curso_codigo;
	String opinion;
	
	
	public Valoracion(int idValoracion, int alumno_codigo, int curso_codigo, String opinion) {
		setIdValoracion(idValoracion);
		setAlumno_codigo(alumno_codigo);
		setCurso_codigo(curso_codigo);
		setOpinion(opinion);
	}

	public int getIdValoracion() {
		return idValoracion;
	}
	
	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
	}
	
	public int getAlumno_codigo() {
		return alumno_codigo;
	}
	
	public void setAlumno_codigo(int alumno_codigo) {
		this.alumno_codigo = alumno_codigo;
	}
	
	public int getCurso_codigo() {
		return curso_codigo;
	}
	
	public void setCurso_codigo(int curso_codigo) {
		this.curso_codigo = curso_codigo;
	}
	
	public String getOpinion() {
		return opinion;
	}
	
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alumno_codigo;
		result = prime * result + curso_codigo;
		result = prime * result + idValoracion;
		result = prime * result + ((opinion == null) ? 0 : opinion.hashCode());
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
		Valoracion other = (Valoracion) obj;
		if (alumno_codigo != other.alumno_codigo)
			return false;
		if (curso_codigo != other.curso_codigo)
			return false;
		if (idValoracion != other.idValoracion)
			return false;
		if (opinion == null) {
			if (other.opinion != null)
				return false;
		} else if (!opinion.equals(other.opinion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Valoracion [idValoracion=" + idValoracion + ", alumno_codigo=" + alumno_codigo + ", curso_codigo="
				+ curso_codigo + ", opinion=" + opinion + "]";
	}
	
	
	
}
