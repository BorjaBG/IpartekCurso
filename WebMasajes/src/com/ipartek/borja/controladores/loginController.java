package com.ipartek.borja.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final String LOGIN = "/WEB-INF/vistas/login.jsp";
	private static final long serialVersionUID = 1L;
       
    public loginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher(LOGIN).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String usuario = request.getParameter("usuario");
		String contraseña = request.getParameter("contraseña");
		
		if("administrador".equals(usuario) && "admin258".equals(contraseña)) {
			request.getSession().setAttribute("email", usuario);
			response.sendRedirect("/WebMasajes/AdminIndex");
		} else {
			request.setAttribute("alertatexto", "El login no es correcto");
			request.setAttribute("alertanivel", "danger");
			request.setAttribute("email", usuario);
			
			request.getRequestDispatcher(LOGIN).forward(request, response);
		//doGet(request, response);
		}
	}

}
