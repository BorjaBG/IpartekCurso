package com.ipartek.borja.api;

import javax.ws.rs.*;

import com.ipartek.borja.globales.Global;
import com.ipartek.borja.modelos.Servicio;


@Path("/servicios")
@Produces("application/json")
@Consumes("application/json")
public class ServiciosAPI {
	
	
	@GET
	public Iterable<Servicio> getAll(){
		return Global.daoServicio.obtenerTodos();
	}
	
	@GET
	@Path("/{id}")
	public Servicio getById(@PathParam("id") int id) {
		return Global.daoServicio.obtenerPorId(id);
	}
	
	@POST
	public Servicio insert(Servicio servicio) {
		Global.daoServicio.agregar(servicio);
		return servicio;
	}
	
	@PUT
	@Path("/{id}")
	public Servicio update(@PathParam("id") int id, Servicio servicio) {
			//throw new WebApplicationException("No se ha encontrado el id a modificar", Status.NOT_FOUND);
		servicio.setId(id);
		Global.daoServicio.actualizar(servicio);
		return servicio;
	}
	
	@DELETE
	@Path("/{id}")
	public String delete(@PathParam("id") int id) {
		Global.daoServicio.eliminar(id);
		return "{}";
	}
	
	/*
	 	
	 	private static final String URL_ID_VALIDA = "^/\\d+$";
		private static Gson gson = new Gson();
	 	
	 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = request.getPathInfo();
		
		if (path == null || path.equals("/")) {
			out.write(gson.toJson(Global.daoServicio.obtenerTodos()));
			
		}

		if (path.matches(URL_ID_VALIDA)) {
			int id = Integer.parseInt(path.substring(1));

			Servicio servicio = Global.daoServicio.obtenerPorId(id);
			//System.out.println(trabajador.toString());

			if(servicio == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				out.write(gson.toJson(servicio));
				response.setStatus(HttpServletResponse.SC_OK);
			}

			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = extraerJSON(request);

		Servicio servicio = gson.fromJson(json, Servicio.class);
		
		Global.daoServicio.agregar(servicio);
		response.getWriter().write(gson.toJson(servicio));
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = extraerJSON(request);

		Servicio servicio = gson.fromJson(json, Servicio.class);
		
		Global.daoServicio.actualizar(servicio);
		response.getWriter().write(gson.toJson(servicio));
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();

		if(path == null || !path.matches(URL_ID_VALIDA)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int id = Integer.parseInt(path.substring(1));

		Global.daoServicio.eliminar(id);

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
	 */

}
