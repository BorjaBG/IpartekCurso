<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<div class="cuerpo-tablas">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Valoracion</th>
					<th>Reseña</th>
					<th>Nombre del servicio</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${valoraciones}" var="valoracion">
					<tr>
						<th>${valoracion.idValoracion}</th>
						<td>${valoracion.valoracion}</td>
						<td>${valoracion.resena}</td>
						<td>${valoracion.servicio.nombre}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>