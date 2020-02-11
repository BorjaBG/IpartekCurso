<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Valoracion</th>
					<th>Reseña</th>
					
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${valoraciones}" var="valoracion">
					<tr>
						<th>${valoracion.id}</th>
						<td>${valoracion.valoracion}</td>
						<td>${valoracion.reseña}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>