package com.ipartek.formacion.personas.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.formacion.personas.modelos.Persona;
import com.ipartek.formacion.personas.repositirio.Dao;

@WebServlet("/index")
public class indexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*String id = request.getParameter("id");
		String op = request.getParameter("op");*/
		//Dao<Persona> dao = (Dao<Persona>) FabricaDao.getInstancia();
		@SuppressWarnings("unchecked")
		Dao<Persona> dao = (Dao<Persona>) getServletContext().getAttribute("dao");
		request.setAttribute("personas", dao.obtenerTodos());
		/*if(op != null) {
			switch(op) {
			case "modificar":
				Persona persona = dao.obtenerPorId((Long.parseLong(id)));
				request.setAttribute("persona", persona);
			case "agregar":
				request.getRequestDispatcher("persona.jsp").forward(request, response);
				return;
			case "eliminar":
				dao.eliminar((Long.parseLong(id)));
				break;
			}
		}*/
		request.setAttribute("personas", dao.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
