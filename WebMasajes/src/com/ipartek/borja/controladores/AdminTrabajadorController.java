package com.ipartek.borja.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.borja.globales.Global;

/**
 * Servlet implementation class AdminTrabajadorController
 */
@WebServlet("/AdminTrabajador")
public class AdminTrabajadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		boolean fallo = true;
		
		if("eliminar".equals(op)) {
			Global.daoTrabajador.eliminar(Integer.parseInt(id));
			response.sendRedirect(request.getContextPath() + "/AdminTrabajador");
			fallo = false;
		}
		
		if(fallo) {
			request.setAttribute("trabajadores", Global.daoTrabajador.obtenerTodos());
			request.getRequestDispatcher("/WEB-INF/vistas/admin/AdminTrabajador.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
