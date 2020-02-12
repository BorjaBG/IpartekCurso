<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<div>
		<a href="ClienteForm?&op=agregar"><button title="añadir" id="btn-anadir" type="button" class="btn btn-success">Añadir</button></a>
	</div>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>DNI</th>
					<th>Telefono</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${clientes}" var="cliente">
					<tr>
						<th>${cliente.id}</th>
						<td>${cliente.nombre}</td>
						<td>${cliente.apellidos}</td>
						<td>${cliente.dni}</td>
						<td>${cliente.telefono}</td>
						<td>
							<a href="ClienteForm?id=${cliente.id}&op=modificar"><button title="modificar" id="btn-modificar" type="button" class="btn btn-warning">Modificar</button></a>
							<a href="AdminCliente?id=${cliente.id}&op=eliminar"><button title="eliminar" id="btn-eliminar" type="button" class="btn btn-danger">Eliminar</button></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>