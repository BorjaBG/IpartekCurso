package com.ipartek.borja.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.borja.globales.Global;
import com.ipartek.borja.modelos.Resena;


@WebServlet("/api/resenas/*")
public class ResenasAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String URL_ID_VALIDA = "^/\\d+$";
	private static Gson gson = new Gson();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = request.getPathInfo();
		
		if (path == null || path.equals("/")) {
			out.write(gson.toJson(Global.daoResena.obtenerTodos()));
			
		}

		if (path.matches(URL_ID_VALIDA)) {
			int id = Integer.parseInt(path.substring(1));

			Resena resena = Global.daoResena.obtenerPorId(id);
			//System.out.println(trabajador.toString());

			if(resena == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				out.write(gson.toJson(resena));
				response.setStatus(HttpServletResponse.SC_OK);
			}

			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = extraerJSON(request);

		Resena resena = gson.fromJson(json, Resena.class);
		
		Global.daoResena.agregar(resena);
		response.getWriter().write(gson.toJson(resena));
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = extraerJSON(request);

		Resena resena = gson.fromJson(json, Resena.class);
		
		Global.daoResena.actualizar(resena);
		response.getWriter().write(gson.toJson(resena));
		response.setStatus(HttpServletResponse.SC_CREATED);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();

		if(path == null || !path.matches(URL_ID_VALIDA)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int id = Integer.parseInt(path.substring(1));

		Global.daoResena.eliminar(id);

		response.getWriter().write("EL CLIENTE HA SIDO ELIMINADO");
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
