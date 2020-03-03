<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<form action="ServicioForm" method="post" class="cuerpo-tablas">
		<input type="hidden" id="op" name="op" value="${op}"/>
	  <div class="form-group">
	    <label for="id">ID</label>
	    <input type="number" class="form-control" id="id" name="id"  value="${servicio.id}" readonly>
	  </div>
	  <div class="form-group">
	    <label for="nombre">Nombre</label>
	    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜÑ ]){2,50}" value="${servicio.nombre}"  required>
	  </div>
	  <div class="form-group">
	    <label for="precio">Precio</label>
	    <input type="text" class="form-control" id="precio" name="precio" placeholder="0.00" pattern="([0-9]+.[0-9]+)" value="${servicio.precio}" required>
	  </div>
	  <button type="submit" class="btn btn-success" >Aceptar</button>
	</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>