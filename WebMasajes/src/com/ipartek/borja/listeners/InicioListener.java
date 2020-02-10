package com.ipartek.borja.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.ipartek.borja.controladores.Global;
import com.ipartek.borja.repositorio.ServicioMySQL;


@WebListener
public class InicioListener implements ServletContextListener {

    
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	String pathConfiguracion = sce.getServletContext().getRealPath("/WEB-INF/") + "configuracion.properties";
    	
    	Global.dao = ServicioMySQL.getInstancia(pathConfiguracion);
    }
	
}
