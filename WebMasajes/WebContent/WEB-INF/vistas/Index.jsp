<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/vistas/includes/Cabecera.jsp"%>

	<h1 id="">Masajes disponibles</h1>
	<div class="container-fluid">
		<div class="row">
			<c:forEach items="${masajes}" var="masaje">
				<div class="col-12 col-sm-4 col-md-4 col-lg-2">
					<div class="card" style="width: 13rem;">
						<div class="card-body">
								<!-- <img src="${libro.imagen}" class="card-img" alt="Imagen del libro">  -->
							<p class="card-title">${libro.nombre}</p>
							<h2 class="card-text">${libro.autor}</h2>
							<h2 class="card-text">${libro.precio}</h2>
							<h2 class="card-text">${libro.descuento}</h2>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

<%@ include file="/WEB-INF/vistas/includes/Pie.jsp"%>