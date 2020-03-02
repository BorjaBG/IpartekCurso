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
	//"SELECT valoracion.idValoracion, valoracion, reseña, nombre FROM valoracion, actuaciones, servicio WHERE valoracion.idValoracion = actuaciones.idValoracion AND actuaciones.idServicio = servicio.idServicio"
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("valoraciones", Global.daoPeticiones.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/admin/AdminValoracion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
