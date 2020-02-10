package com.ipartek.borja.globales;

import com.ipartek.borja.modelos.Actuaciones;
import com.ipartek.borja.modelos.Cliente;
import com.ipartek.borja.modelos.Resena;
import com.ipartek.borja.modelos.Servicio;
import com.ipartek.borja.modelos.Trabajador;
import com.ipartek.borja.repositorio.Dao;

public class Global {
	public static Dao<Servicio> daoServicio;
	public static Dao<Actuaciones> daoActuaciones;
	public static Dao<Resena> daoResena;
	public static Dao<Cliente> daoCliente;
	public static Dao<Trabajador> daoTrabajador;
}
