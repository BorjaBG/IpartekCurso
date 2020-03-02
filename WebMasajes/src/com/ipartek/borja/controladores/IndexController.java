package com.ipartek.borja.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.borja.globales.Global;


@WebServlet("/Index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*@SuppressWarnings("unchecked")
		Dao<Servicio> dao = (Dao<Servicio>) getServletContext().getAttribute("configuracion");*/
		
		request.setAttribute("masajes", Global.daoServicio.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
