<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<form action="login" method="post">
	  <div class="form-group">
	    <label for="usuario">Usuario</label>
	    <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Usuario">
	  </div>
	  <div class="form-group">
		  <label for="contraseña">Contraseña</label>
		  <input type="password" class="form-control" id="contraseña" name="contraseña" placeholder="Contraseña">
		</div>
	  <button type="submit" class="btn btn-primary">Entrar</button>
	</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>