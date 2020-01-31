package com.ipartek.formacion.personas.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final String LOGIN = "/WEB-INF/vistas/login.jsp";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher(LOGIN).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String usuario = request.getParameter("usuario");
		String contraseña = request.getParameter("contraseña");
		String sqlSelect = "SELECT * FROM usuarios_per WHERE usuario=?";
		String us = null;
		String contra = null;
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uf2213?serverTimezone=UTC", "root", "admin")){
			try(PreparedStatement ps = con.prepareStatement(sqlSelect)){
				ps.setString(1, usuario);
				try(ResultSet rs = ps.executeQuery()){
					if(rs.next()) {
						System.out.println("LLego hasta aqui");
						us = rs.getString("usuario");
						contra = rs.getString("contraseña");
					}
				}catch(SQLException e) {
					throw new RuntimeException("Error al pedir a la base de datos 2:", e);
				}
			}catch(SQLException e) {
				throw new RuntimeException("Error al pedir a la base de datos", e);
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al conectar con la base de datos", e);
		}
		System.out.println(us.equals(null));
		if(!us.equals(null)) {
			System.out.println("Usuario: " + us + "\n" + "Contraseña: " + contra + "\n");
			if(us.equals(usuario) && contra.equals(contraseña)) {
				request.getSession().setAttribute("email", usuario);
				response.sendRedirect("/WebPersonas/personas");
			}else {
				request.setAttribute("alertatexto", "El login no es correcto");
				request.setAttribute("alertanivel", "danger");
				request.setAttribute("email", usuario);
				
				request.getRequestDispatcher(LOGIN).forward(request, response);
			}
			
		} else {
			request.setAttribute("alertatexto", "El login no es correcto");
			request.setAttribute("alertanivel", "danger");
			//request.setAttribute("email", usuario);
			
			request.getRequestDispatcher(LOGIN).forward(request, response);
		//doGet(request, response);
		}
	}

}
