package com.ipartek.borja.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.borja.globales.Global;
import com.ipartek.borja.modelos.Servicio;


@WebServlet("/ServicioForm")
public class ServicioFormController extends HttpServlet {
	private static final String SERVICIO_JSP = "/WEB-INF/vistas/formularios/AdminServicioForm.jsp";
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		
		if(id != null) {
			//ServicioMySQL.getInstancia().obtenerPorId(Long.parseLong(id));
			request.setAttribute("servicio", Global.daoServicio.obtenerPorId(Integer.parseInt(id)));
		}
		
		request.setAttribute("op", op);
		request.getRequestDispatcher(SERVICIO_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Encoding en UTF-8 al enviar para que no haya ningun fallo con los caracteres
			request.setCharacterEncoding("UTF-8");	
				
			boolean fallo = true;
			String op = request.getParameter("op");
			String id = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String precio = request.getParameter("precio");
						
						
			Servicio servicio = null;
						
			switch(op) {
			case "agregar":
				servicio = new Servicio(nombre, Double.parseDouble(precio));
				Global.daoServicio.agregar(servicio);
				/*if(servicio.isCorrecto()) {
					dao.agregar(servicio);
				}*/
				fallo = false;
				response.sendRedirect(request.getContextPath() + "/AdminServicio");
				break;
			case "modificar":
				servicio = new Servicio(Integer.parseInt(id), nombre, Double.parseDouble(precio));
				Global.daoServicio.actualizar(servicio);
				/*if(servicio.isCorrecto()) {
					dao.actualizar(servicio);
				}*/
				fallo = false;
				response.sendRedirect(request.getContextPath() + "/AdminServicio");
				break;
			default:
				throw new RuntimeException("Operación no reconocida");
			}
			if(fallo) {
				/*HttpSession session = request.getSession();
				session.setAttribute("alertatexto", "La operación " + op + " ha sido completada exitosamente");
				session.setAttribute("alertanivel", "success");*/
				request.setAttribute("servicios", servicio);
				request.getRequestDispatcher("/AdminIndex").forward(request, response);
			}
				
			/* if(servicio.isCorrecto()) {
				HttpSession session = request.getSession();
				session.setAttribute("alertatexto", "La operación " + op + " ha sido completada exitosamente");
				session.setAttribute("alertanivel", "success");
				request.setAttribute("servicios", servicio);
				request.getRequestDispatcher("/admin/AdminIndex").forward(request, response);
			}else if(!servicio.isCorrecto()) {
				request.setAttribute("alertatexto", "Hay un error en el formulario. Revise los datos.");
				request.setAttribute("alertanivel", "danger");
				System.out.println(libro.getErrorDescuento());
				System.out.println(libro.getErrorNombre());
				System.out.println(libro.getErrorPrecio());
				request.setAttribute("primeravez", false);
				request.setAttribute("op", op);
				request.setAttribute("servicios", servicio);
				request.getRequestDispatcher(LIBRO_JSP).forward(request, response);
			}
			 */
				
		
		
		//doGet(request, response);
	}

}
