package com.ipartek.borja.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.ipartek.borja.globales.Global;
import com.ipartek.borja.repositorio.ActuacionesMySQL;
import com.ipartek.borja.repositorio.ClienteMySQL;
import com.ipartek.borja.repositorio.PeticionesMySQL;
import com.ipartek.borja.repositorio.ResenaMySQL;
import com.ipartek.borja.repositorio.ServicioMySQL;
import com.ipartek.borja.repositorio.TrabajadorMySQL;


@WebListener
public class InicioListener implements ServletContextListener {

    
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	String pathConfiguracion = sce.getServletContext().getRealPath("/WEB-INF/") + "configuracion.properties";
    	
    	Global.daoServicio = ServicioMySQL.getInstancia("jdbc/salonmasajes");
    	Global.daoTrabajador = TrabajadorMySQL.getInstancia(pathConfiguracion);
    	Global.daoActuaciones = ActuacionesMySQL.getInstancia(pathConfiguracion);
    	Global.daoResena = ResenaMySQL.getInstancia(pathConfiguracion);
    	Global.daoCliente = ClienteMySQL.getInstancia(pathConfiguracion);
    	Global.daoPeticiones = PeticionesMySQL.getInstancia(pathConfiguracion);
    }
	
}
