<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<h1 id="">Amigos</h1>
    <div class="container-fluid">
			<div class="row">
				<c:forEach items="${personas}" var="persona">
					<div class="col-12 col-sm-4 col-md-4 col-lg-2">
						<div class="card" style="width: 13rem;">
						  <div class="card-body">
							<img src="https://images.generated.photos/ww3Ht-Tf6Zh17fJYFQEJeNWcqwUMhVlAVykzQH0Uu90/rs:fit:512:512/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yy/XzAwMDIzOTYuanBn.jpg" class="card-img" alt="Imagen de perfil de la persona">
							<h2 class="card-title">${persona.nombre}</h2>
							<p class="card-text">${persona.apellidos}</p>
						  </div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>