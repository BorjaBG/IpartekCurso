package com.ipartek.borja.modelos;

import java.sql.Date;

public class Actuaciones {
	
	private int idActuaciones;
	private int idServicio;
	private int idTrabajador;
	private int idValoracion;
	private Date fecha;
	
	public Actuaciones(int idActuaciones, int idServicio, int idCliente, int idValoracion, Date fecha) {
		setIdActuaciones(idActuaciones);
		setIdServicio(idServicio);
		setIdTrabajador(idCliente);
		setIdValoracion(idValoracion);
		setFecha(fecha);
	}
	
	public Actuaciones(){
		
	}

	public int getIdActuaciones() {
		return idActuaciones;
	}

	public void setIdActuaciones(int idActuaciones) {
		this.idActuaciones = idActuaciones;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idCliente) {
		this.idTrabajador = idCliente;
	}

	public int getIdValoracion() {
		return idValoracion;
	}

	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idActuaciones;
		result = prime * result + idServicio;
		result = prime * result + idTrabajador;
		result = prime * result + idValoracion;
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
		Actuaciones other = (Actuaciones) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idActuaciones != other.idActuaciones)
			return false;
		if (idServicio != other.idServicio)
			return false;
		if (idTrabajador != other.idTrabajador)
			return false;
		if (idValoracion != other.idValoracion)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Actuaciones [idActuaciones=" + idActuaciones + ", idServicio=" + idServicio + ", idTrabajador="
				+ idTrabajador + ", idValoracion=" + idValoracion + ", fecha=" + fecha + "]";
	}
	
	
	
}
