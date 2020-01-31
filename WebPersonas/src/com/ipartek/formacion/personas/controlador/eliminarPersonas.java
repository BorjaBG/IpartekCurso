package com.ipartek.formacion.personas.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.formacion.personas.modelos.Persona;
import com.ipartek.formacion.personas.repositirio.Dao;


@WebServlet("/eliminarPersonas")
public class eliminarPersonas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		@SuppressWarnings("unchecked")
		Dao<Persona> dao = (Dao<Persona>) getServletContext().getAttribute("dao");
		dao.eliminar(Long.parseLong(id));
		
		response.sendRedirect(request.getContextPath() + "/personas");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
