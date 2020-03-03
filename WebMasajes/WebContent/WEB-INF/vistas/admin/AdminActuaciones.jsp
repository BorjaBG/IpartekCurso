<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="btn-añadir">
	<a href="ActuacionesForm?&op=agregar"><button title="añadir"
			id="btn-anadir" type="button" class="btn btn-success">Añadir</button></a>
</div>

<div class="cuerpo-tablas">
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Servicio</th>
				<th>Cliente</th>
				<th>Trabajador</th>
				<th>Valoracion</th>
				<th>Fecha</th>
				<th></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${actuaciones}" var="actuacion">
				<tr>
					<th>${actuacion.idActuaciones}</th>
					<td>${actuacion.servicio.nombre}</td>
					<td>${actuacion.cliente.nombre} ${actuacion.cliente.apellidos}</td>
					<td>${actuacion.trabajador.nombre} ${actuacion.trabajador.apellidos}</td>
					<td>${actuacion.resenas.valoracion}</td>
					<td>${actuacion.fecha}</td>
					<td><a
						href="ActuacionesForm?id=${actuacion.idActuaciones}&op=modificar"><button
								title="modificar" id="btn-modificar" type="button"
								class="btn btn-warning">Modificar</button></a> <a
						href="AdminCliente?id=${actuacion.idActuaciones}&op=eliminar"><button
								title="eliminar" id="btn-eliminar" type="button"
								class="btn btn-danger">Eliminar</button></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>