<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<div class="btn-añadir">
		<a href="TrabajadorForm?&op=agregar"><button title="añadir" id="btn-anadir" type="button" class="btn btn-success">Añadir</button></a>
	</div>
	
	<div class="cuerpo-tablas">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>DNI</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${trabajadores}" var="trabajador">
					<tr>
						<th>${trabajador.id}</th>
						<td>${trabajador.nombre}</td>
						<td>${trabajador.apellidos}</td>
						<td>${trabajador.dni}</td>
						<td>
							<a href="TrabajadorForm?id=${trabajador.id}&op=modificar"><button title="modificar" id="btn-modificar" type="button" class="btn btn-warning">Modificar</button></a>
							<a href="AdminTrabajador?id=${trabajador.id}&op=eliminar"><button title="eliminar" id="btn-eliminar" type="button" class="btn btn-danger">Eliminar</button></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>