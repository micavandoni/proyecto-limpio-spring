<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="ar.edu.unlam.tallerweb1.modelo.Propiedad"%>
<html>
<head>
<title>Title</title>
</head>

<body>
	<div>
		<div class="container">
			<div id="loginbox" style="margin-top: 50px;"
				class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<form:form action="filtro-propiedad" method="POST" modelAttribute="propiedadFiltro">
					<h3 class="form-signin-heading">Filtro</h3>
					<hr class="colorgraph">
					<br>
					<form:input path="condicion" id="condicion" type="text" class="form-control" />
					<button class="btn btn-lg btn-primary btn-block" Type="Submit">Buscar</button>
				</form:form>
			</div>
		</div>
	</div>
	<div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Tipo Propiedad</th>
					<th>Condicion</th>
					<th>Precio</th>
					<th>Direccion</th>
					<th>Imgen</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${propiedades}" var="propiedad">
					<tr>
						<td>${propiedad.tipo}</td>
						<td>${propiedad.condicion}</td>
						<td>${propiedad.precio}</td>
						<td>${propiedad.direccion}${propiedad.localidad}
							${propiedad.provincia}</td>
						<td>${propiedad.imagenUrl}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
			<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
