<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<form action="ClienteForm" method="post">
		<input type="hidden" id="op" name="op" value="${op}"/>
	  <div class="form-group">
	    <label for="id">ID</label>
	    <input type="number" class="form-control" id="id" name="id"  value="${cliente.id}" readonly>
	  </div>
	  <div class="form-group">
	    <label for="nombre">Nombre</label>
	    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜ?¿!¡,.:;-_/*'&$€%@ ]){2,40}" value="${cliente.nombre}"  required>
	  </div>
	  <div class="form-group">
	    <label for="apellidos">Apellidos</label>
	    <input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Apellido1 Apellidos2" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜ?¿!¡,.:;-_/*'&$€%@ ]){2,100}" value="${cliente.apellidos}"  required>
	  </div>
	  <div class="form-group">
	    <label for="dni">DNI</label>
	    <input type="text" class="form-control" id="dni" name="dni" placeholder="00000000P" pattern="([0-9]{8}[A-Za-z]{1})" value="${cliente.dni}"  required>
	  </div>
	  <div class="form-group">
	    <label for="telefono">Telefono</label>
	    <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="123456789" pattern="[0-9]{9}" value="${cliente.telefono}"  required>
	  </div>
	  <div class="form-group">
	    <label for="idActuacion">ID del Servicio</label>
	    <input type="number" class="form-control" id="idActuacion" name="idActuacion"  value="${cliente.idActuacion}">
	  </div>
	  <button type="submit" class="btn btn-success" >Aceptar</button>
	</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>