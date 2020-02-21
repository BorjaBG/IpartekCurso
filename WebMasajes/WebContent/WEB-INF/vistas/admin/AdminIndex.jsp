<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col py-3 px-lg-5">
				<a href="/WebMasajes/AdminServicio"><button type="button" class="btn btn-info btn-lg btn-block">Servicios</button></a>
			</div>
			
			<div class="col py-3 px-lg-5">
				<a href="/WebMasajes/AdminTrabajador"><button type="button" class="btn btn-light btn-lg btn-block">Trabajadores</button></a>
			</div>
			
		</div>
		
		<div class="row">
			<div class="col py-3 px-lg-5">
				<a href="/WebMasajes/AdminValoracion"><button type="button" class="btn btn-primary btn-lg btn-block">Valoraciones</button></a>
			</div>
			
			<div class="col py-3 px-lg-5">
				<a href="/WebMasajes/AdminCliente"><button type="button" class="btn btn-light btn-lg btn-block">Clientes</button></a>
			</div>
		</div>
		
		<div class="row align-items-center">
			<div class="col py-3 px-lg-5">
				<a href="/WebMasajes/AdminActuaciones"><button type="button" class="btn btn-primary btn-lg btn-block">Servicios ofrecidos</button></a>
			</div>
		</div>
		
	</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>