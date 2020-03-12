package com.ipartek.mf0966_3.borja.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ipartek.mf0966_3.borja.global.Global;
import com.ipartek.mf0966_3.borja.repositorio.CursoMySQL;
import com.ipartek.mf0966_3.borja.repositorio.ValoracionMySQL;



@WebListener
public class Listener implements ServletContextListener {
	
	private static final String NOMBRE_POOL = "jdbc/mf0966_3";

    public void contextDestroyed(ServletContextEvent sce)  { 
        
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	Global.daoCurso = CursoMySQL.getInstancia(NOMBRE_POOL);
    	Global.daoValoracion = ValoracionMySQL.getInstancia(NOMBRE_POOL);
    }
	
}
