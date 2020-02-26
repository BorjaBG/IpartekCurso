package com.ipartek.borja.modelos;

import java.util.Date;

public class Actuaciones {
	
	private int idActuaciones;
	private int idServicio;
	private int idTrabajador;
	private int idValoracion;
	private Date fecha;
	private Servicio servicio;
	private Cliente cliente;
	private Trabajador trabajador;
	private Resena resenas;
	
	public Actuaciones(Servicio servicio, Cliente cliente, Trabajador trabajador, Resena resenas, Date fecha) {
		setServicio(servicio);
		setCliente(cliente);
		setTrabajador(trabajador);
		setResenas(resenas);
		setFecha(fecha);
	}
	
	
	public Actuaciones(int idActuaciones, int idServicio, int idCliente, int idValoracion, Date fecha) {
		setIdActuaciones(idActuaciones);
		setIdServicio(idServicio);
		setIdTrabajador(idCliente);
		setIdValoracion(idValoracion);
		setFecha(fecha);
	}
	
	public Actuaciones(int idActuaciones, Servicio servicio, Trabajador trabajador, Resena resenas, Cliente cliente, Date fecha) {
		setIdActuaciones(idActuaciones);
		setServicio(servicio);
		setTrabajador(trabajador);
		setResenas(resenas);
		setCliente(cliente);
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

	public void setFecha(Date date) {
		this.fecha = date;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Resena getResenas() {
		return resenas;
	}

	public void setResenas(Resena resenas) {
		this.resenas = resenas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idActuaciones;
		result = prime * result + idServicio;
		result = prime * result + idTrabajador;
		result = prime * result + idValoracion;
		result = prime * result + ((resenas == null) ? 0 : resenas.hashCode());
		result = prime * result + ((servicio == null) ? 0 : servicio.hashCode());
		result = prime * result + ((trabajador == null) ? 0 : trabajador.hashCode());
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
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
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
		if (resenas == null) {
			if (other.resenas != null)
				return false;
		} else if (!resenas.equals(other.resenas))
			return false;
		if (servicio == null) {
			if (other.servicio != null)
				return false;
		} else if (!servicio.equals(other.servicio))
			return false;
		if (trabajador == null) {
			if (other.trabajador != null)
				return false;
		} else if (!trabajador.equals(other.trabajador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Actuaciones [idActuaciones=" + idActuaciones + ", idServicio=" + idServicio + ", idTrabajador="
				+ idTrabajador + ", idValoracion=" + idValoracion + ", fecha=" + fecha + ", servicio=" + servicio
				+ ", cliente=" + cliente + ", trabajador=" + trabajador + ", resenas=" + resenas + "]";
	}
	
	
	
}
