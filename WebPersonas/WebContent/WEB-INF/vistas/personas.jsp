<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>
	<body>
	<div>
		<a href="persona?&op=agregar"><button title="añadir" id="btn-anadir" type="button" class="btn btn-success">Añadir</button></a>
	</div>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${personas}" var="persona">
					<tr>
						<th>${persona.id}</th>
						<td>${persona.nombre}</td>
						<td>${persona.apellidos}</td>
						<td>
							<a href="persona?id=${persona.id}&op=modificar"><button title="modificar" id="btn-modificar" type="button" class="btn btn-warning">Modificar</button></a>
							<a href="personas?id=${persona.id}&op=eliminar"><button title="eliminar" id="btn-eliminar" type="button" class="btn btn-danger">Eliminar</button></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>

