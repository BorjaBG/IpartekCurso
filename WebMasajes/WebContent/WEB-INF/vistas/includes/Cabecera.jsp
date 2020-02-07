<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Masajes</title>

<base href="${pageContext.request.contextPath}/" />

<!-- Bootstrap 4 -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- DataTables con aspecto Bootstrap 4 -->
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<!-- Font Awesome -->
<link rel="stylesheet" href="css/all.min.css" />

<!-- <link rel="stylesheet" href="css/ejemplomvc.css" /> -->

<!-- jQuery -->
<script src="js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap -->
<script src="js/bootstrap.bundle.min.js"></script>
<!-- DataTables -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap4.min.js"></script>
<!-- <script src="https://cdn.datatables.net/plug-ins/1.10.20/i18n/Spanish.json"></script> -->

<script>
	$(document).ready(function() {
		$('.table').DataTable();
	});
</script>

</head>
<body>
	<header>
		<h1>Masajes</h1>
	</header>
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="#">Logo</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item active">
	        <a class="nav-link" href="/ExamenWebLibros/index">Pagina principal<span class="sr-only"></span></a>
	      </li>
	      <!--<li class="nav-item">
	        <a class="nav-link" href="#">Features</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Pricing</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
	      </li> -->
	    </ul>
	    <!-- <span class="ml-3">
	    	<button class="btn btn-outline-success my-2 my-sm-0" type="button">Login</button>
	    </span> -->
	  </div>
	</nav>
	
	<c:if test="${alertatexto != null}">
		<div class="alert alert-${alertanivel} alert-dismissible fade show"
			role="alert">
			${alertatexto}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<%
			session.removeAttribute("alertatexto");
				session.removeAttribute("alertanivel");
		%>
	</c:if>
	
	<main class="container-fluid">