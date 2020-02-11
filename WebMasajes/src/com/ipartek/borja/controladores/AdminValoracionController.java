package com.ipartek.borja.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.borja.globales.Global;


@WebServlet("/AdminValoracion")
public class AdminValoracionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(Global.daoTrabajador.obtenerTodos());
		request.setAttribute("valoraciones", Global.daoResena.obtenerTodos());
		request.setAttribute("trabajadores", Global.daoTrabajador.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/admin/AdminValoracion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
