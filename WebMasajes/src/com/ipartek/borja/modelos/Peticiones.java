package com.ipartek.borja.modelos;

import java.sql.Date;

public class Peticiones {
	
	private int idValoracion;
	private int idServicio;
	private int idTrabajador;
	private int idCliente;
	private String nombreServicio;
	private String nombreTrabajador;
	private String nombreCliente;
	private String resena;
	private String valoracion;
	private double precio;
	private Servicio servicio;
	private Cliente cliente;
	private Trabajador trabajador;
	private Resena resenas;
	private Date fecha;
	
	public Peticiones() {
		
	}
	
	public Peticiones(int idValoracion, String valoracion, String resena, Servicio servicio) {
		setIdValoracion(idValoracion);
		setValoracion(valoracion);
		setResena(resena);
		setServicio(servicio);
	}
	
	public Peticiones(int idValoracion, Servicio servicio, Cliente cliente, Trabajador trabajador, Resena resena, Date fecha) {
		setIdValoracion(idValoracion);
		setServicio(servicio);
		setCliente(cliente);
		setTrabajador(trabajador);
		setResenas(resena);
		setFecha(fecha);
	}

	public int getIdValoracion() {
		return idValoracion;
	}

	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
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

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getNombreTrabajador() {
		return nombreTrabajador;
	}

	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getResena() {
		return resena;
	}

	public void setResena(String resena) {
		this.resena = resena;
	}

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
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
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idCliente;
		result = prime * result + idServicio;
		result = prime * result + idTrabajador;
		result = prime * result + idValoracion;
		result = prime * result + ((nombreCliente == null) ? 0 : nombreCliente.hashCode());
		result = prime * result + ((nombreServicio == null) ? 0 : nombreServicio.hashCode());
		result = prime * result + ((nombreTrabajador == null) ? 0 : nombreTrabajador.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((resena == null) ? 0 : resena.hashCode());
		result = prime * result + ((resenas == null) ? 0 : resenas.hashCode());
		result = prime * result + ((servicio == null) ? 0 : servicio.hashCode());
		result = prime * result + ((trabajador == null) ? 0 : trabajador.hashCode());
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
		Peticiones other = (Peticiones) obj;
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
		if (idCliente != other.idCliente)
			return false;
		if (idServicio != other.idServicio)
			return false;
		if (idTrabajador != other.idTrabajador)
			return false;
		if (idValoracion != other.idValoracion)
			return false;
		if (nombreCliente == null) {
			if (other.nombreCliente != null)
				return false;
		} else if (!nombreCliente.equals(other.nombreCliente))
			return false;
		if (nombreServicio == null) {
			if (other.nombreServicio != null)
				return false;
		} else if (!nombreServicio.equals(other.nombreServicio))
			return false;
		if (nombreTrabajador == null) {
			if (other.nombreTrabajador != null)
				return false;
		} else if (!nombreTrabajador.equals(other.nombreTrabajador))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (resena == null) {
			if (other.resena != null)
				return false;
		} else if (!resena.equals(other.resena))
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
		if (valoracion == null) {
			if (other.valoracion != null)
				return false;
		} else if (!valoracion.equals(other.valoracion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Peticiones [idValoracion=" + idValoracion + ", idServicio=" + idServicio + ", idTrabajador="
				+ idTrabajador + ", idCliente=" + idCliente + ", nombreServicio=" + nombreServicio
				+ ", nombreTrabajador=" + nombreTrabajador + ", nombreCliente=" + nombreCliente + ", resena=" + resena
				+ ", valoracion=" + valoracion + ", precio=" + precio + ", servicio=" + servicio + ", cliente="
				+ cliente + ", trabajador=" + trabajador + ", resenas=" + resenas + ", fecha=" + fecha + "]";
	}

}
