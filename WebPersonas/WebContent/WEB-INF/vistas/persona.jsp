<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<form action="persona" method="POST">
	  <div class="form-group">
	    <label for="id">ID</label>
	    <input type="number" class="form-control" id="id"  value="${persona.id}" readonly>
	  </div>
	  <div class="form-group">
	    <label for="nombre">Nombre</label>
	    <input type="text" class="form-control" id="nombre" placeholder="Nombre" value="${persona.nombre}" required>
	  </div>
	  <div class="form-group">
	    <label for="apellidos">Apellidos</label>
	    <input type="text" class="form-control" id="apellidos" placeholder="Apellidos" value="${persona.apellidos}" required>
	  </div>
	  <button type="submit">Aceptar</button>
	</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>