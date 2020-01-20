package com.ipartek.formacion.personas.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.formacion.personas.modelos.Persona;
import com.ipartek.formacion.personas.repositirio.Dao;
import com.ipartek.formacion.personas.repositirio.PersonaTreeMap;

/**
 * Servlet implementation class personaController
 */
@WebServlet("/persona")
public class personaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public personaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*if (id == null || nombre == null || apellidos == null) {
			throw new RuntimeException("Faltan datos para la petición");
		}*/
		
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		//Long idGuardar;
		
		Dao<Persona> dao = PersonaTreeMap.getInstancia();
		
		
		System.out.println("metodo utilizado: " + request.getMethod());
		
		
		//System.out.println(id + idGuardar);
		
		if("modificar".equals(op)) {
			request.setAttribute("persona", dao.obtenerPorId(Long.parseLong(id)));
		}

		request.getRequestDispatcher("/WEB-INF/vistas/persona.jsp").forward(request, response);
		//doPost(request, response);
	}

	//@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("He entrado en el post");
		
		/*if (id == null || nombre == null || apellidos == null) {
			throw new RuntimeException("Faltan datos para la petición");
		}*/
		
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String op = request.getParameter("op");
		//Long idGuardar;
		
		Dao<Persona> dao = PersonaTreeMap.getInstancia();
		Persona persona = null;
		System.out.println(dao.obtenerTodos().toString());
		
		
		System.out.println("agregar".equals(op) + "metodo utilizado: " + request.getMethod());
		
		if ("agregar".equals(op)) {
			System.out.println("He entrado en añadir");
			persona = new Persona(nombre, apellidos);
			dao.agregar(persona);
		} else if("modificar".equals(op)) {
			System.out.println(Long.parseLong(id) + "Hola modificar");
			persona = new Persona(nombre, apellidos);
			dao.actualizar(persona);
		}
		
		System.out.println(dao.obtenerTodos().toString());
		
		doGet(request, response);
		
		/*if("modificar".equals(op)) {
			request.setAttribute("persona", dao.obtenerPorId(Long.parseLong(id)));
		}
		request.getRequestDispatcher("/WEB-INF/vistas/persona.jsp").forward(request, response);*/
		
	}

}
