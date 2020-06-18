<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  <meta http-equiv="X-UA-Compatible" content="ie=edge">
	  <title>Ingresar HouseHolds</title>
	  <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
	  <link rel="stylesheet" href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css">
	  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	  <link rel="stylesheet" href="css/login.css">
	  <link href="css/style.css" rel="stylesheet">
	</head>
	<body>
	  <main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
	    <div class="container">
	      <div class="card login-card">
	        <div class="row no-gutters">
	          <div class="col-md-5">
	            <img id="img-logo" class="login-card-img">
	          </div>
	          <div class="col-md-7">
	            <div class="card-body">
	              <h1 style="color:#009970"><span>HouseHolds</span></h1>	              
	              <p class="login-card-description">Ingresar a tu cuenta</p>
	              <form:form action="validar-login" method="POST" modelAttribute="usuario">
	                  <div class="form-group">
	                    <label for="email" class="sr-only">Email</label>
	                    <input type="email" name="email" id="email" class="form-control" placeholder="Email">
	                  </div>
	                  <div class="form-group mb-4">
	                    <label for="password" class="sr-only">Contraseña</label>
	                    <input type="password" name="password" id="password" class="form-control" placeholder="***********">
	                  </div>
	                  <input name="login" id="login" class="btn btn-block login-btn mb-4" type="Submit" value="Ingresar">
	                </form:form>
	                <%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
					<c:if test="${not empty error}">
				        <h4><span>${error}</span></h4>
				        <br>
			        </c:if>	
	                <a href="#!" class="forgot-password-link">¿Olvidaste tu contraseña?</a>
	                <p class="login-card-footer-text">¿No tienes una cuenta? <a href="#!" class="text-reset">Regístrate Aquí</a></p>                
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </main>
	  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>
