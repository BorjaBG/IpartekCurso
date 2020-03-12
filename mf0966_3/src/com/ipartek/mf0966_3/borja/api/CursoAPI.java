package com.ipartek.mf0966_3.borja.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.mf0966_3.borja.global.Global;
import com.ipartek.mf0966_3.borja.modelos.Curso;

/**
 * Servlet implementation class CursoAPI
 */
@WebServlet("/api/cursos/*")
public class CursoAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final String URL_ID_VALIDA = "^/\\d+$";
	private static Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = request.getPathInfo();
		
		if (path == null || path.equals("/")) {
			out.write(gson.toJson(Global.daoCurso.obtenerTodos()));
			
		}

		if (path.matches(URL_ID_VALIDA)) {
			String identificador = path.substring(1);

			Curso curso = Global.daoCurso.obtenerPorIdentificador(identificador);

			if(curso == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				out.write(gson.toJson(curso));
				response.setStatus(HttpServletResponse.SC_OK);
			}

			return;
		}
	}

}
