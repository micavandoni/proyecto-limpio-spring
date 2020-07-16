<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>HouseHolds - Detalle</title>
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
	  	<style>
			#map_canvas {
				height: 400px; /* The height is 400 pixels */
				width: 100%; /* The width is the width of the web page */
			}
		</style>
	</head>
	<body onload="propiedad('${propiedad.latitud}', '${propiedad.longitud}')">
		<!-- ======= Header ======= -->
		<header id="header" class="fixed-top d-flex align-items-center">
		    <div class="container">
		      <div class="header-container d-flex align-items-center">
		        <div class="logo mr-auto">
		          <h1 class="text-light"><a href="propiedad"><span>HouseHolds</span></a></h1>
		        </div>				
			    <nav class="nav-menu d-none d-lg-block" style="margin-left: -840px!important;">
			   	  <ul>
			        <form:form action="irAPropiedad" method="GET" style="height: 0px!important;
    margin-left: -800px!important;
    width: 200px!important;
    padding: 0!important;">
			        <li class="active"><button class="btn btn-home" style="margin-right: 400px; margin-top: 12px;" type="Submit">Home</button></li>
			        </form:form>
			        <form:form action="loguearse" method="GET" style="height: 60px!important;
    margin-left: -200px;!important">
		            <c:choose>
					    <c:when test="${usuarioBuscado.id == null}">
					        <li class="get-started"><button class="btn btn-buscar" style="margin-top: 12px;" type="Submit">Ingresar</button></li>
					        <br />
					    </c:when>    
					    <c:otherwise>
					        <li class="get-started"><button class="btn btn-buscar" style="margin-top: 12px;" type="Submit">Mi Perfil</button></li>
					        <br />
					    </c:otherwise>
					</c:choose> 
					</form:form>          
			          </ul>
			        </nav><!-- .nav-menu -->
		        
		      </div><!-- End Header Container -->
		    </div>
		 </header><!-- End Header -->
		<section id="perfil" class="d-flex align-items-center">
		    <div class="container position-relative" data-aos="fade-in" data-aos-delay="200" style="color:#fff">		      
		    </div>
		</section>
		<main id="main">
		  <section id="detalle" class="portfolio">
	          <div class="container">
	              <div class="section-title" data-aos="fade-left">
	                  <h2>Detalle Vivienda</h2>
	              </div>
	              <div class="portfolio-container " data-aos="fade-up" data-aos-delay="200" style="min-height: 600px; height:800px!important">
	              	<div class="row">
	              		<div class="col-lg-12">
	              		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
						  <ol class="carousel-indicators">
						    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
						    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
						  </ol>
						  <div class="carousel-inner ">
						    <div class="carousel-item active">
						      <img class="d-block w-100" src="img/portfolio/${propiedad.imagenUrl}" height="550"  alt="FtoPrincipal">
						    </div>
						    <div class="carousel-item">
						      <img class="d-block w-100" src="img/portfolio/${propiedad.imagenUrl2}" height="550" alt="Detalle">
						    </div>
						    <div class="carousel-item">
						      <img class="d-block w-100" src="img/portfolio/${propiedad.imagenUrl3}" height="550" alt="Detalle2">
						    </div>
						    <div class="carousel-item">
						      <img class="d-block w-100" src="img/portfolio/${propiedad.imagenUrl4}" height="550" alt="Detalle3">
						    </div>
						  </div>
						  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
						    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
						  </a>
						  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
						    <span class="carousel-control-next-icon" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						  </a>
						</div>
		              		
		              	</div>
	              	</div>
	              	<div class="row">
	              		<div class="col-lg-12">
	              			<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col">Ambiente</th>
										<th scope="col">Detalle</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th>${propiedad.ambiente}</th>
										<td>${propiedad.detalle}</td>
									</tr>
								</tbody>
							</table>
	              		</div>
	              	</div>	              	
	              </div>	
	          </div>
	      </section>
	      <section id="publicaciones" class="portfolio">
	      	<div class="container">
	      		<div class="section-title" data-aos="fade-left">
	                  <h2>Publicaciones</h2>
	              </div>
	              <div class="row portfolio-container" data-aos="fade-up" data-aos-delay="200" style="height:auto!important">          
			          <table class="table table-striped">
					    <tbody> 
					    	<c:forEach items="${listaPublis}" var="publicacion">
	              			<div class="col-lg-4 col-md-6 portfolio-item filter-web" style="position: absolute; left: 380px; top: 0px;">
					            <div class="portfolio-wrap">
					              <img src="img/inmobiliarias/${publicacion.inmobiliaria.nombreInmobiliaria}.jpg" class="img-fluid" alt="">					              
					              <div class="portfolio-info">
					              	<p>Precio por </p><h4>${publicacion.inmobiliaria.nombreInmobiliaria}</h4><p> $${publicacion.precio}</p>
					              	<c:choose>
									    <c:when test="${usuarioBuscado.id == null}">
									    	<form:form action="loguearse" method="GET">
												<button type="submit" class="btn btn-buscar">Ingresar para enviar mail</button>	
											</form:form>
									    </c:when>    
									    <c:otherwise>
									       <form:form action="envioMail" method="POST" modelAttribute="email">	
												<input id="email" name="email" type="hidden" value="${publicacion.inmobiliaria.email}">
												<input id="id" name="id" type="hidden" value="${propiedad.id}">
												<button type="submit" class="btn btn-buscar">Enviar email</button>	
											</form:form>
									    </c:otherwise>
									</c:choose>
					              	
					              </div>
					             </div>
					         </div>	         		
	              			</c:forEach>	
	              		</tbody>
	              	  </table>
	              	</div>
	      		</div>
	      		<c:if test="${not empty mail}">
	      			<div class="container">
	      				<div class="alert alert-success" role="alert">
		      				<h4 class="alert-heading">${mail}</h4>
							<p>${msj}</p>						
						</div>
	      			</div>
	      		</c:if>
	      </section>
	        
	      <section id="ubicacion" class="portfolio">
	      	<div class="container">
	      		<div class="section-title" data-aos="fade-left">
	                  <h2>Ubicación</h2>
	              </div>
	              <div class="row portfolio-container" data-aos="fade-up" data-aos-delay="200" style="min-height:120px; height:120px!important">
	              	<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Direccion</th>
								<th scope="col">Localidad</th>
								<th scope="col">Provincia</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${propiedad.direccion}</td>
								<td>${propiedad.localidad}</td>
								<td>${propiedad.provincia}</td>
							</tr>
						</tbody>
					</table>
	              </div>
	      		<div class="row">
	          		<div class="col-lg-12">
	          			<div clas id="map_canvas"></div>
	          		</div>
	          	</div>
	      	</div>
	      	
	      </section>
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
  <script src="js/mapa.js"></script>
  <script src="js/myjs.js"></script>
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyDrptfptHbZNZcJR3V3SUWyLClIOV0fBDY&sensor=false"></script>
</html>