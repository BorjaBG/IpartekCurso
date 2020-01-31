package com.ipartek.formacion.personas.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.formacion.personas.modelos.Persona;
import com.ipartek.formacion.personas.repositirio.Dao;

/**
 * Servlet implementation class personaControler
 */
@WebServlet("/persona")
public class personaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public personaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		
		//System.out.println("ID: " + id + " Operacion: " + op);
		
		if(id != null) {
			@SuppressWarnings("unchecked")
			Dao<Persona> dao = (Dao<Persona>) getServletContext().getAttribute("dao");
			/*System.out.println(dao.obtenerPorId(Long.parseLong(id)));
			Persona persona = PersonaTreeMap.getInstancia().obtenerPorId(Long.parseLong(id));*/
			request.setAttribute("persona", dao.obtenerPorId(Long.parseLong(id)));
		}
		
		request.setAttribute("op", op);
		
		request.getRequestDispatcher("/WEB-INF/vistas/persona.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Dao<Persona> dao = PersonaTreeMap.getInstancia();
		
		request.setCharacterEncoding("UTF-8");
		
		@SuppressWarnings("unchecked")
		Dao<Persona> dao = (Dao<Persona>) getServletContext().getAttribute("dao");
		
		String op = request.getParameter("op");
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		
		Persona persona = null;
		
		switch(op) {
		case "agregar":
			persona = new Persona(nombre, apellidos);
			
			dao.agregar(persona);
			break;
		case "modificar":
			persona = new Persona(Long.parseLong(id), nombre, apellidos);
			
			dao.actualizar(persona);
			break;
		default:
			throw new RuntimeException("Operación no reconocida");
		}
		
		response.sendRedirect(request.getContextPath() + "/personas");
		
		//doGet(request, response);
	}

}
