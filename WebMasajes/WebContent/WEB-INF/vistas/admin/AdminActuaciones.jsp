<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Servicio</th>
					<th>Trabajador</th>
					<th>Valoracion</th>
					<th>Fecha</th>
					<th>Cliente</th>
					<th>Trabajador</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${actuaciones}" var="actuacion">
					<tr>
						<th>${valoracion.idValoracion}</th>
						<td>${valoracion.valoracion}</td>
						<td>${valoracion.resena}</td>
						<td>${valoracion.servicio.nombre}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>