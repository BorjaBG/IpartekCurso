package com.ipartek.mf0966_3.borja.modelos;

import java.sql.Date;

public class Curso {
	
	int codigo, nHoras, cliente_codigo, profesor_codigo;
	String nombre, identificador, temario;
	Date fInicio, fFin;
	boolean activo;
	double precio;
	
	public Curso() {
		
	}
	
	public Curso(int codigo, int nHoras, int cliente_codigo, int profesor_codigo, String nombre, String identificador,
			String temario, Date fInicio, Date fFin, boolean activo, double precio) {
		super();
		setCodigo(codigo);
		setnHoras(nHoras);
		setCliente_codigo(cliente_codigo);
		setProfesor_codigo(profesor_codigo);
		setNombre(nombre);
		setIdentificador(identificador);
		setTemario(temario);
		setfInicio(fInicio);
		setfFin(fFin);
		setActivo(activo);
		setPrecio(precio);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getnHoras() {
		return nHoras;
	}

	public void setnHoras(int nHoras) {
		this.nHoras = nHoras;
	}

	public int getCliente_codigo() {
		return cliente_codigo;
	}

	public void setCliente_codigo(int cliente_codigo) {
		this.cliente_codigo = cliente_codigo;
	}

	public int getProfesor_codigo() {
		return profesor_codigo;
	}

	public void setProfesor_codigo(int profesor_codigo) {
		this.profesor_codigo = profesor_codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTemario() {
		return temario;
	}

	public void setTemario(String temario) {
		this.temario = temario;
	}

	public Date getfInicio() {
		return fInicio;
	}

	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	public Date getfFin() {
		return fFin;
	}

	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
		result = prime * result + cliente_codigo;
		result = prime * result + codigo;
		result = prime * result + ((fFin == null) ? 0 : fFin.hashCode());
		result = prime * result + ((fInicio == null) ? 0 : fInicio.hashCode());
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + nHoras;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + profesor_codigo;
		result = prime * result + ((temario == null) ? 0 : temario.hashCode());
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
		Curso other = (Curso) obj;
		if (activo != other.activo)
			return false;
		if (cliente_codigo != other.cliente_codigo)
			return false;
		if (codigo != other.codigo)
			return false;
		if (fFin == null) {
			if (other.fFin != null)
				return false;
		} else if (!fFin.equals(other.fFin))
			return false;
		if (fInicio == null) {
			if (other.fInicio != null)
				return false;
		} else if (!fInicio.equals(other.fInicio))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		if (nHoras != other.nHoras)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (profesor_codigo != other.profesor_codigo)
			return false;
		if (temario == null) {
			if (other.temario != null)
				return false;
		} else if (!temario.equals(other.temario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "curso [codigo=" + codigo + ", nHoras=" + nHoras + ", cliente_codigo=" + cliente_codigo
				+ ", profesor_codigo=" + profesor_codigo + ", nombre=" + nombre + ", identificador=" + identificador
				+ ", temario=" + temario + ", fInicio=" + fInicio + ", fFin=" + fFin + ", activo=" + activo
				+ ", precio=" + precio + "]";
	}
	
	

}
