package com.ipartek.borja.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.borja.globales.Global;


@WebServlet("/AdminCliente")
public class AdminClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		boolean fallo = true;
		
		if("eliminar".equals(op)) {
			Global.daoCliente.eliminar(Integer.parseInt(id));
			response.sendRedirect(request.getContextPath() + "/AdminCliente");
			fallo = false;
		}
		
		if(fallo) {
			System.out.println(Global.daoCliente.obtenerTodos().toString());
			request.setAttribute("clientes", Global.daoCliente.obtenerTodos());
			request.getRequestDispatcher("/WEB-INF/vistas/admin/AdminCliente.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
