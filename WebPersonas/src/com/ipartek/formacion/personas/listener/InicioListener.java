package com.ipartek.formacion.personas.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ipartek.formacion.personas.modelos.Persona;
import com.ipartek.formacion.personas.repositirio.Dao;
import com.ipartek.formacion.personas.repositirio.FabricaDao;


@WebListener
public class InicioListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 

    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	String pathConfiguracion = sce.getServletContext().getRealPath("/WEB-INF/") + "configuracion.properties";
        FabricaDao fabricaDao = FabricaDao.getInstancia(pathConfiguracion);

        Dao<Persona> dao = fabricaDao.getInstanciaPersona();

        sce.getServletContext().setAttribute("dao", dao);
    }
	
}
