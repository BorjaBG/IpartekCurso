<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<div>
		<a href="ServicioForm?&op=agregar"><button title="añadir" id="btn-anadir" type="button" class="btn btn-success">Añadir</button></a>
	</div>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Precio</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${servicios}" var="servicio">
					<tr>
						<th>${servicio.id}</th>
						<td>${servicio.nombre}</td>
						<td>${servicio.precio}</td>
						<td>
							<a href="ServicioForm?id=${servicio.id}&op=modificar"><button title="modificar" id="btn-modificar" type="button" class="btn btn-warning">Modificar</button></a>
							<a href="AdminServicio?id=${servicio.id}&op=eliminar"><button title="eliminar" id="btn-eliminar" type="button" class="btn btn-danger">Eliminar</button></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>