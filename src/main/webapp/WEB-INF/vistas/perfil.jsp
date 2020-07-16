<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta content="width=device-width, initial-scale=1.0" name="viewport">

  		<title>Mi Perfil</title>
  		<meta content="" name="descriptison">
		<meta content="" name="keywords">
		
		<!-- Favicons -->
		<link href="img/favicon.png" rel="icon">
		<link href="img/apple-touch-icon.png" rel="apple-touch-icon">
		
		<!-- Google Fonts -->
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
		
		<!-- Vendor CSS Files -->
		<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="vendor/icofont/icofont.min.css" rel="stylesheet">
		<link href="vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
		<link href="vendor/remixicon/remixicon.css" rel="stylesheet">
		<link href="vendor/venobox/venobox.css" rel="stylesheet">
		<link href="vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
		<link href="vendor/aos/aos.css" rel="stylesheet">
		
		  <link href="css/style.css" rel="stylesheet">
	</head>
	<body>
		<!-- ======= Header ======= -->
		<header id="header" class="fixed-top d-flex align-items-center">
		    <div class="container">
		      <div class="header-container d-flex align-items-center">
		        <div class="logo mr-auto">
		          <h1 class="text-light"><a href="propiedad"><span>HouseHolds</span></a></h1>
		        </div>
				<form:form action="irAPropiedad" method="GET">
			        <nav class="nav-menu d-none d-lg-block">
			          <ul>
			            <li class="active"><button class="btn btn-home" style="margin-right: 400px;" type="Submit">Home</button></li>
			            <li><a href="#viviendasFavoritas">Mis Favoritas</a></li>
		            	<li class="get-started"><a class="btn btn-buscar">Hola ${usuarioBuscado.nombre}</a></li>            
			          </ul>
			        </nav><!-- .nav-menu -->
		        </form:form>
		      </div><!-- End Header Container -->
		    </div>
		 	<form:form action="login" method="GET">
			    <c:choose>
				    <c:when test="${usuarioBuscado.id == null}">
				    </c:when>    
				    <c:otherwise>
				        <button type="submit" class="btn-cerrarsesion">Cerrar Sesión</button>
				    </c:otherwise>
				</c:choose>
			</form:form>
		 </header><!-- End Header -->
		 <!-- ======= Perfil Section ======= -->
		  <section id="perfil" class="d-flex align-items-center">
		    <div class="container position-relative" data-aos="fade-in" data-aos-delay="200" style="color:#fff">
		      <h2> ${usuarioBuscado.nombre} con Perfil de ${usuarioBuscado.rol}</h2>
		      <h3>${usuarioBuscado.email}</h3>
		    </div>
		  </section>
		 <main id="main">
		 	<!-- SECTION VIVIENDAS FAVORITAS -->
			 <section id="viviendasFavoritas" class="portfolio">
	          <div class="container">
	              <div class="section-title" data-aos="fade-left">
	                  <h2>Viviendas Favoritas</h2>
	              </div>
	              <div class="row portfolio-container" data-aos="fade-up" data-aos-delay="200" style="min-height: 500px; height:auto!important">
	              	<c:if test="${not empty propiedadesFavs}">	                  
		                  <c:forEach items= "${propiedadesFavs}" var="propiedad">	    	
						    	<div class="col-lg-4 col-md-6 portfolio-item filter-web" style="position: absolute; left: 380px; top: 0px;">
						            <div class="portfolio-wrap">
						              <img src="img/portfolio/${propiedad.imagenUrl}" class="img-fluid" alt="">
						              
						              <div class="portfolio-info">
						                <h4>${propiedad.ambiente}, ${propiedad.titulo}</h4>
						                <p>${propiedad.direccion}, ${propiedad.localidad}</p>	
						                <p>${propiedad.condicion}, <i>${propiedad.precio}</i></p>
										<form:form action="detalle" method="POST" modelAttribute="detalle">	
											<input id="id" name="id" type="hidden" value="${propiedad.id}">
											<button type="submit" class="btn btn-buscar">Ver Detalle</button>	
										</form:form>
									  </div>
						            </div>
						         </div>					    
						  </c:forEach>
					 </c:if>
	              </div>	
	          </div>
	      </section><!-- End Portfolio Section -->
		 </main>
		 
		 
	</body>
	
	<!-- ======= Footer ======= -->
  <footer id="footer">

    <div class="footer-top">
      <div class="container">
        <div class="row">

          <div class="col-lg-3 col-md-6 footer-contact">
            <h3>HouseHolds</h3>
            <p>
              Florencio Varela 1903 <br>
              San Justo, Buenos Aires<br>
              Argentina <br><br>
              <strong>Phone:</strong> +1 5589 55488 55<br>
              <strong>Email:</strong> info@households.com<br>
            </p>
          </div>

          <div class="col-lg-2 col-md-6 footer-links">            
          </div>

          <div class="col-lg-3 col-md-6 footer-links">
            <h4>Nuestros Servicios</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Ayuda</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">¿Cómo funciona HouseHolds?</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Términos y Condiciones</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Información Legal</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Prensa</a></li>
            </ul>
          </div>

          <div class="col-lg-4 col-md-6 footer-newsletter">
            <h4>Nuestra Newsletter</h4>
            <p>¿Querés recibir ofertas exclusivas de viviendas? ¡Suscribite!</p>
            <form action="" method="post">
              <input type="email" name="email"><input type="submit" value="Subscribir">
            </form>
          </div>

        </div>
      </div>
    </div>

    <div class="container d-md-flex py-4">

      <div class="mr-md-auto text-center text-md-left">
        <div class="copyright">
          &copy; Copyright <strong><span>HouseHolds</span></strong> | 2020. Todos los derechos reservados
        </div>
      </div>
      <div class="social-links text-center text-md-right pt-3 pt-md-0">
        <div class="social-links text-center text-md-right pt-3 pt-md-0">
        <a href="https://twitter.com/BaHolds" class="twitter" target="_blank"><i class="bx bxl-twitter"></i></a>
        <a href="https://www.facebook.com/house.holds.988" class="facebook" target="_blank"><i class="bx bxl-facebook"></i></a>
        <a href="https://www.instagram.com/householdstw1/" class="instagram" target="_blank"><i class="bx bxl-instagram"></i></a>
        <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
        <a href="https://linkedin.com/in/house-holds-8270aa1b3" class="linkedin" target="_blank"><i class="bx bxl-linkedin"></i></a>
      </div>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

  <!-- Vendor JS Files -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="vendor/jquery.easing/jquery.easing.min.js"></script>
  <script src="vendor/php-email-form/validate.js"></script>
  <script src="vendor/waypoints/jquery.waypoints.min.js"></script>
  <script src="vendor/counterup/counterup.min.js"></script>
  <script src="vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="vendor/venobox/venobox.min.js"></script>
  <script src="vendor/owl.carousel/owl.carousel.min.js"></script>
  <script src="vendor/aos/aos.js"></script>

  <!-- Template Main JS File -->
  <script src="js/main.js"></script>
  <script src="js/myjs.js"></script>
</html>