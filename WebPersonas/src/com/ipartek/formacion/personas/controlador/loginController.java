package com.ipartek.formacion.personas.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final String CONTRASEÑA_BBDD = "admin";
	private static final String USUARIO_BBDD = "root";
	private static final String URL_BBDD = "jdbc:mysql://localhost:3306/uf2213?serverTimezone=UTC";
	private static final String LOGIN = "/WEB-INF/vistas/login.jsp";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher(LOGIN).forward(request, response);
	}

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String usuario = request.getParameter("usuario");
		String contraseña = request.getParameter("contraseña");
		String sqlSelectUsuario = "SELECT usuario FROM usuarios_per";
		String sqlSelectContraseña = "SELECT contraseña FROM usuarios_per WHERE usuario=?";
		String us = null;
		ArrayList<String> arrayUs = new ArrayList<String>();
		String contra = null;
		
		try(Connection con = DriverManager.getConnection(URL_BBDD, USUARIO_BBDD, CONTRASEÑA_BBDD)){
			try(PreparedStatement ps = con.prepareStatement(sqlSelectUsuario)){
				//ps.setString(1, usuario);
				try(ResultSet rs = ps.executeQuery()){
					//int i = 0;
					for(int i = 1; rs.next(); i++) {
						us = rs.getString("usuario");
						arrayUs.add(us);
						System.out.println(arrayUs.get(0));
					}
					/*if(rs.next()) {
						//System.out.println("LLego hasta aqui");
						us = rs.getString("usuario");
						System.out.println(arrayUs[i].toString());
						arrayUs[i] = us;
						
						i++;
					}*/
				}catch(SQLException e) {
					//throw new RuntimeException("Error al pedir a la base de datos 2:", e);
				}
			}catch(SQLException e) {
				//throw new RuntimeException("Error al pedir a la base de datos", e);
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al conectar con la base de datos", e);
		}
		boolean encontrado = false;
		for(int j = 0; !encontrado || j < arrayUs.size(); j++) {
			if(usuario.equals(arrayUs.get(j))) {
				encontrado = true;
			}
		}
		if(encontrado) {
			try(Connection con = DriverManager.getConnection(URL_BBDD, USUARIO_BBDD, CONTRASEÑA_BBDD)){
				try(PreparedStatement ps = con.prepareStatement(sqlSelectContraseña)){
					ps.setString(1, us);
					try(ResultSet rs = ps.executeQuery()){
						contra = rs.getString("contraseña");
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException("Error al conectar con la base de datos", e);
			}
		} else {
			request.setAttribute("alertatexto", "El login no es correcto");
			request.setAttribute("alertanivel", "danger");
			request.setAttribute("email", usuario);
			request.getRequestDispatcher(LOGIN).forward(request, response);
		}
		if(contra.equals(contraseña)) {
			System.out.println("Usuario: " + us + "\n" + "Contraseña: " + contra + "\n");
			request.getSession().setAttribute("email", usuario);
			response.sendRedirect("/WebPersonas/personas");
			
		} else {
			request.setAttribute("alertatexto", "El login no es correcto");
			request.setAttribute("alertanivel", "danger");
			request.setAttribute("email", usuario);
			request.getRequestDispatcher(LOGIN).forward(request, response);
		//doGet(request, response);
		}
	}

}
