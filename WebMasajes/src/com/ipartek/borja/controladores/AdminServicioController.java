package com.ipartek.borja.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.borja.globales.Global;

@WebServlet("/AdminServicio")
public class AdminServicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		boolean fallo = true;
		
		if("eliminar".equals(op)) {
			Global.daoServicio.eliminar(Integer.parseInt(id));
			response.sendRedirect(request.getContextPath() + "/AdminServicio");
			fallo = false;
		}
		
		if(fallo) {
			request.setAttribute("servicios", Global.daoServicio.obtenerTodos());
			request.getRequestDispatcher("/WEB-INF/vistas/admin/AdminServicio.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
