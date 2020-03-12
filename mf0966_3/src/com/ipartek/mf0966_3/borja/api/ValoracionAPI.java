package com.ipartek.mf0966_3.borja.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.mf0966_3.borja.global.Global;
import com.ipartek.mf0966_3.borja.modelos.Valoracion;


@WebServlet("/api/valoraciones/*")
public class ValoracionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String URL_ID_VALIDA = "^/\\d+$";
	private static Gson gson = new Gson();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = request.getPathInfo();
		
		if (path == null || path.equals("/")) {
			out.write(gson.toJson(Global.daoValoracion.obtenerTodos()));
			
		}

		if (path.matches(URL_ID_VALIDA)) {
			int id = Integer.parseInt(path.substring(1));

			Valoracion valoracion = Global.daoValoracion.obtenerPorId(id);

			if(valoracion == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				out.write(gson.toJson(valoracion));
				response.setStatus(HttpServletResponse.SC_OK);
			}

			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = extraerJSON(request);

		Valoracion valoracion = gson.fromJson(json, Valoracion.class);
		
		Global.daoValoracion.agregar(valoracion);
		response.getWriter().write(gson.toJson(valoracion));
		response.setStatus(HttpServletResponse.SC_CREATED);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = extraerJSON(request);
		String path = request.getPathInfo();

		Valoracion valoracion = gson.fromJson(json, Valoracion.class);
		
		if (path == null || path.equals("/")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		if(path.matches(URL_ID_VALIDA)) {
			int id = Integer.parseInt(path.substring(1));
			
			valoracion.setIdValoracion(id);
			//cliente = gson.fromJson(json, Cliente.class);
			Global.daoValoracion.actualizar(valoracion);
			response.getWriter().write(gson.toJson(valoracion));
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();

		if(path == null || path.equals("/")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int id = Integer.parseInt(path.substring(1));

		Global.daoValoracion.eliminar(id);

		response.getWriter().write("{}");
	}
	
	private String extraerJSON(HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();

		StringBuffer sb = new StringBuffer();
		String linea;

		while( ( linea = br.readLine() ) != null ) {
			sb.append(linea);
		}

		return sb.toString();
	}

}
