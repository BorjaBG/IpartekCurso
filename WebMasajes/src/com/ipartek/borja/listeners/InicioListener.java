package com.ipartek.borja.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.ipartek.borja.globales.Global;
import com.ipartek.borja.globales.GlobalTrabajador;
import com.ipartek.borja.repositorio.ServicioMySQL;
import com.ipartek.borja.repositorio.TrabajadorMySQL;


@WebListener
public class InicioListener implements ServletContextListener {

    
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	String pathConfiguracion = sce.getServletContext().getRealPath("/WEB-INF/") + "configuracion.properties";
    	
    	Global.dao = ServicioMySQL.getInstancia(pathConfiguracion);
    	GlobalTrabajador.dao = TrabajadorMySQL.getInstancia(pathConfiguracion);
    }
	
}
