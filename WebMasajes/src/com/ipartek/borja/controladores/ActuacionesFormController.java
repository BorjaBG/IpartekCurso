package com.ipartek.borja.controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.borja.globales.Global;
import com.ipartek.borja.modelos.Actuaciones;
import com.ipartek.borja.modelos.Cliente;
import com.ipartek.borja.modelos.Resena;
import com.ipartek.borja.modelos.Servicio;
import com.ipartek.borja.modelos.Trabajador;

@WebServlet("/ActuacionesForm")
public class ActuacionesFormController extends HttpServlet {
	private static final String ACTUACIONES_JSP = "/WEB-INF/vistas/formularios/AdminActuacionesForm.jsp";
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		
		if(id != null) {
			//ServicioMySQL.getInstancia().obtenerPorId(Long.parseLong(id));
			request.setAttribute("actuacion", Global.daoActuaciones.obtenerPorId(Integer.parseInt(id)));
		}
		
		request.setAttribute("op", op);
		request.getRequestDispatcher(ACTUACIONES_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Encoding en UTF-8 al enviar para que no haya ningun fallo con los caracteres
		request.setCharacterEncoding("UTF-8");

		boolean fallo = true;
		String op = request.getParameter("op");
		String id = request.getParameter("id");
		String servicio = request.getParameter("servicio");
		String nombreC = request.getParameter("clientenombre");
		String apellidosC = request.getParameter("clienteapellidos");
		String nombreT = request.getParameter("trabajadornombre");
		String apellidosT = request.getParameter("trabajadorapellidos");
		String valoracion = request.getParameter("valoracion");
		String fecha = request.getParameter("fecha");

		Actuaciones actuacion = null;

		switch (op) {
		case "agregar":
			try {
				actuacion = new Actuaciones(new Servicio(servicio),
						new Cliente(nombreC, apellidosC),
						new Trabajador(nombreT, apellidosT),
						new Resena(valoracion),
						new SimpleDateFormat("yyyy/MM/dd").parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Global.daoActuaciones.agregar(actuacion);
			/*
			 * if(servicio.isCorrecto()) { dao.agregar(servicio); }
			 */
			fallo = false;
			response.sendRedirect(request.getContextPath() + "/AdminActuaciones");
			break;
		case "modificar":
			try {
				actuacion = new Actuaciones(Integer.parseInt(id), new Servicio(servicio),
						new Trabajador(nombreT, apellidosT),
						new Resena(valoracion),
						new Cliente(nombreC, apellidosC),
						new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(actuacion.getCliente().getNombre().toString());
			System.out.println(actuacion.getCliente().getApellidos().toString());
			System.out.println(actuacion.getTrabajador().getNombre().toString());
			System.out.println(actuacion.getTrabajador().getApellidos().toString());
			Global.daoActuaciones.actualizar(actuacion);
			/*
			 * if(servicio.isCorrecto()) { dao.actualizar(servicio); }
			 */
			fallo = false;
			response.sendRedirect(request.getContextPath() + "/AdminActuaciones");
			break;
		default:
			throw new RuntimeException("Operación no reconocida");
		}
		if (fallo) {
			/*
			 * HttpSession session = request.getSession();
			 * session.setAttribute("alertatexto", "La operación " + op +
			 * " ha sido completada exitosamente"); session.setAttribute("alertanivel",
			 * "success");
			 */
			request.setAttribute("actuaciones", actuacion);
			request.getRequestDispatcher("/AdminIndex").forward(request, response);
		}

		/*
		 * if(servicio.isCorrecto()) { HttpSession session = request.getSession();
		 * session.setAttribute("alertatexto", "La operación " + op +
		 * " ha sido completada exitosamente"); session.setAttribute("alertanivel",
		 * "success"); request.setAttribute("servicios", servicio);
		 * request.getRequestDispatcher("/admin/AdminIndex").forward(request, response);
		 * }else if(!servicio.isCorrecto()) { request.setAttribute("alertatexto",
		 * "Hay un error en el formulario. Revise los datos.");
		 * request.setAttribute("alertanivel", "danger");
		 * System.out.println(libro.getErrorDescuento());
		 * System.out.println(libro.getErrorNombre());
		 * System.out.println(libro.getErrorPrecio());
		 * request.setAttribute("primeravez", false); request.setAttribute("op", op);
		 * request.setAttribute("servicios", servicio);
		 * request.getRequestDispatcher(LIBRO_JSP).forward(request, response); }
		 */

		// doGet(request, response);
	}

}
