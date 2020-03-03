<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<form action="ActuacionesForm" method="post" class="cuerpo-tablas">
		<input type="hidden" id="op" name="op" value="${op}"/>
	  <div class="form-group">
	    <label for="id">ID</label>
	    <input type="number" class="form-control" id="id" name="id"  value="${actuacion.idActuaciones}" readonly>
	  </div>
	  <div class="form-group">
	    <label for="servicio">Servicio</label>
	    <input type="text" class="form-control" id="servicio" name="servicio" placeholder="Servicio" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜÑ ]){2,140}" value="${actuacion.servicio.nombre}"  required>
	  </div>
	  <div class="form-group">
	    <label for="clientenombre">Nombre del Cliente</label>
	    <input type="text" class="form-control" id="clientenombre" name="clientenombre" placeholder="Nombre" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜÑ ]){2,40}" value="${actuacion.cliente.nombre}"  required>
	  </div>
	  <div class="form-group">
	    <label for="clienteapellidos">Apellidos del Cliente</label>
	    <input type="text" class="form-control" id="clienteapellidos" name="clienteapellidos" placeholder="Apellido1 Apellido2" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜÑ ]){2,100}" value="${actuacion.cliente.apellidos}"  required>
	  </div>
	  <div class="form-group">
	    <label for="trabajadornombre">Nombre del Trabajador</label>
	    <input type="text" class="form-control" id="trabajadornombre" name="trabajadornombre" placeholder="Nombre" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜÑ ]){2,40}" value="${actuacion.trabajador.nombre}"  required>
	  </div>
	  <div class="form-group">
	    <label for="trabajadorapellidos">Apellidos del Trabajador</label>
	    <input type="text" class="form-control" id="trabajadorapellidos" name="trabajadorapellidos" placeholder="Apellido1 Apellido2" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜÑ ]){2,40}" value="${actuacion.trabajador.apellidos}"  required>
	  </div>
	  <div class="form-group">
	    <label for="valoracion">Valoracion</label>
	    <input type="text" class="form-control" id="valoracion" name="valoracion" placeholder="Valoracion" pattern="([A-Za-z0-9éáíóúñüÁÉÍÓÚÜÑ ]){2,400}" value="${actuacion.resenas.valoracion}"  required>
	  </div>
	  <div class="form-group">
	    <label for="fecha">Fecha</label>
	    <input type="date" class="form-control" id="fecha" name="fecha"  value="${actuacion.fecha}">
	  </div>
	  <button type="submit" class="btn btn-success" >Aceptar</button>
	</form>
	

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>