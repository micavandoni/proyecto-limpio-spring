<%--
  Created by IntelliJ IDEA.
  User: Diego
  Date: 15/05/2020
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Actividades</th>
        <th>Lugar</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${propiedades}" var="propiedad">
        <tr>
            <td>${propiedad.nombre}</td>
            <td>${propiedad.descripcion}</td>
            <td>${propiedad.lugar}</td>
            <td>${propiedad.lugar}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
