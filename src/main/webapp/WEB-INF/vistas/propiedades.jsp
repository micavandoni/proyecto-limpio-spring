<%--
  Created by IntelliJ IDEA.
  User: Diego
  Date: 15/05/2020
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Propiedades</title>
</head>
<body>

<table class="table table-striped">
    <thead>
    <tr>
        <th><h1>Tipo</h1></th>
        <th><h1>Dirección</h1></th>
        <th><h1>Detalle</h1></th>
        <th><h1>Localidad</h1></th>
        <th><h1>Condición</h1></th>
        <th><h1>Precio (U$S)</h1></th>
        <th><h1>Imagen</h1></th>
    </tr>
    </thead>
    <tbody>    
    <c:forEach items= "${propiedades}" var="propiedad">
        <tr>
            <td>${propiedad.tipo}</td>
            <td>${propiedad.direccion}</td>
            <td>${propiedad.detalle}</td>
            <td>${propiedad.localidad}</td>
            <td>${propiedad.condicion}</td>
            <td>${propiedad.precio}</td>
            <td><img src="${propiedad.imagenUrl}"></td>                      
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
