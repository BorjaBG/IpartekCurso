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

    
    private static final String NOMBRE_POOL = "jdbc/salonmasajes";

	public void contextDestroyed(ServletContextEvent sce)  { 
         
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	String pathConfiguracion = sce.getServletContext().getRealPath("/WEB-INF/") + "configuracion.properties";
    	
    	Global.daoServicio = ServicioMySQL.getInstancia(NOMBRE_POOL);
    	Global.daoTrabajador = TrabajadorMySQL.getInstancia(NOMBRE_POOL);
    	Global.daoActuaciones = ActuacionesMySQL.getInstancia(NOMBRE_POOL);
    	Global.daoResena = ResenaMySQL.getInstancia(NOMBRE_POOL);
    	Global.daoCliente = ClienteMySQL.getInstancia(NOMBRE_POOL);
    	Global.daoPeticiones = PeticionesMySQL.getInstancia(pathConfiguracion);
    }
	
}
