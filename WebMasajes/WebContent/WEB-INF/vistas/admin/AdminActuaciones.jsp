<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

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
						<td>${actuacion.cliente.nombre}</td>
						<td>${actuacion.trabajador.nombre}</td>
						
						<td>${actuacion.fecha}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>