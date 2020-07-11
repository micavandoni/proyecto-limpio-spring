
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>HouseHolds</title>
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

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top d-flex align-items-center">
    <div class="container">
      <div class="header-container d-flex align-items-center">
        <div class="logo mr-auto">
          <h1 class="text-light"><a href="index.html"><span>HouseHolds</span></a></h1>
        </div>
		<form:form action="loguearse" method="GET">
	        <nav class="nav-menu d-none d-lg-block">
	          <ul>
	            <li class="active"><a href="#header">Home</a></li>
	            <li><a href="#about">Nosotros</a></li>
	            <li><a href="#viviendas">Viviendas</a></li>
	            <li><a href="#viviendasNuevas">Nuevas</a></li>
            	<c:choose>
				    <c:when test="${usuarioBuscado.id == null}">
				        <li class="get-started"><button class="btn btn-buscar" type="Submit">Ingresar</button></li>
				        <br />
				    </c:when>    
				    <c:otherwise>
				        <li class="get-started"><button class="btn btn-buscar" type="Submit">Mi Perfil</button></li>
				        <br />
				    </c:otherwise>
				</c:choose>	            
	          </ul>
	        </nav><!-- .nav-menu -->
        </form:form>
      </div><!-- End Header Container -->
    </div>
  </header><!-- End Header -->

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex align-items-center">
    <div class="container text-center position-relative" data-aos="fade-in" data-aos-delay="200">
      <h1>Encontrá tu vivienda ideal y compará precios de diferentes inmobiliarias</h1>
      <h2>Te simplificaremos tu búsqueda</h2>
      <a href="#about" class="btn-get-started scrollto">Comencemos</a>
    </div>
  </section><!-- End Hero -->

  <main id="main">

    <!-- ======= Clients Section ======= -->
    <section id="clients" class="clients">
      <div class="container">

        <div class="row">

          <div class="col-lg-2 col-md-4 col-6 d-flex align-items-center" data-aos="zoom-in" data-aos-delay="100">
            <img src="img/inmobiliarias/inmo1.png" class="img-fluid" alt="">
          </div>

          <div class="col-lg-2 col-md-4 col-6 d-flex align-items-center" data-aos="zoom-in" data-aos-delay="200">
            <img src="img/inmobiliarias/inmo2.png" class="img-fluid" alt="">
          </div>

          <div class="col-lg-2 col-md-4 col-6 d-flex align-items-center" data-aos="zoom-in" data-aos-delay="300">
            <img src="img/inmobiliarias/inmo3.png" class="img-fluid" alt="">
          </div>

          <div class="col-lg-2 col-md-4 col-6 d-flex align-items-center" data-aos="zoom-in" data-aos-delay="400">
            <img src="img/inmobiliarias/inmo4.png" class="img-fluid" alt="">
          </div>

          <div class="col-lg-2 col-md-4 col-6 d-flex align-items-center" data-aos="zoom-in" data-aos-delay="500">
            <img src="img/inmobiliarias/inmo5.png" class="img-fluid" alt="">
          </div>

          <div class="col-lg-2 col-md-4 col-6 d-flex align-items-center" data-aos="zoom-in" data-aos-delay="600">
            <img src="img/inmobiliarias/inmo6.png" class="img-fluid" alt="">
          </div>

        </div>

      </div>
    </section><!-- End Clients Section -->

    <!-- ======= About Section ======= -->
    <section id="about" class="about">
      <div class="container">

        <div class="row content">
          <div class="col-lg-6" data-aos="fade-right" data-aos-delay="100">
            <h2>Nosotros</h2>
            <h3>Somos un buscador de viviendas de primer nivel en todo el país</h3>
          </div>
          <div class="col-lg-6 pt-4 pt-lg-0" data-aos="fade-left" data-aos-delay="200">
            <p>
              HouseHolds es un buscador de viviendas y terrenos que agiliza los procesos de comparación y reserva mostrando en tiempo real los precios y ofertas de más de 100.000 viviendas de 600 inmobiliarias en todo el país argentino.
            </p>
            <ul>
              <li><i class="ri-check-double-line"></i> Con más de 200 mil visitas al mes</li>
              <li><i class="ri-check-double-line"></i> Encontrá fácilmente tu vivienda ideal y compará precios de diferentes inmobiliarias</li>
              <li><i class="ri-check-double-line"></i> Desde casas quintas a monoambientes o terrenos listos para ser adquiridos</li>
            </ul>
            <p class="font-italic">
              ¡Comenzá la búsqueda de tu hogar soñado!.
            </p>
          </div>
        </div>

      </div>
    </section><!-- End About Section -->

    <!-- ======= Counts Section ======= -->
    <section id="counts" class="counts">
    	
    		<div class="container">
		        <div class="row counters">
		         	<div class="col-lg-3 col-6 text-center">
		            	<span data-toggle="counter-up">${contadores[3]}</span>
		            	<p>Inmobiliarias</p>
		          	</div>
		          	<div class="col-lg-3 col-6 text-center">
		            	<span data-toggle="counter-up">${contadores[1]}</span>
		            	<p>Departamentos</p>
		          	</div>
		          	<div class="col-lg-3 col-6 text-center">
		            	<span data-toggle="counter-up">${contadores[0]}</span>
		            	<p>Casas</p>
		          	</div>
		          	<div class="col-lg-3 col-6 text-center">
		            	<span data-toggle="counter-up">${contadores[2]}</span>
		            	<p>Terrenos</p>
		          	</div>
	        	</div>
	    	</div>
      
    </section><!-- End Counts Section -->

    <!-- ======= Viviendas Section ======= -->
    <section id="viviendas" class="portfolio">
      <div class="container">

        <div class="section-title" data-aos="fade-left">
          <h2>Buscá tu Vivienda</h2>
          <p>Podrás buscar tu vivienda ideal al mejor precio aplicando los filtros que desees a continuación:</p>
        </div>

        <div class="row" data-aos="fade-up" data-aos-delay="100">
			<form:form action="filtro-propiedad" method="POST" modelAttribute="propiedadFiltro" class="col-lg-12">
				<div class="row justify-content-center">					

					<div class="col-lg-5">
						<form:select path="condicion" id="condicion" type="text" class="form-control">
							<form:option value="null">Todas</form:option>
			    			<form:option value="venta">En venta</form:option>
			    			<form:option value="alquiler">En alquiler</form:option>
						</form:select>
					</div>
					<div class="col-lg-3" id="btnBusquedaComun">
						<button  class="btn btn-buscar" Type="Submit">Buscar</button>
					</div>
				</div>
				<div class="row justify-content-center" style="margin-top:20px">
					<div class="col-lg-3">
						<a onclick="mostrarBtnBusqueda()" id="txtBusqueda" data-toggle="collapse" href="#colapsarBusquedaAvanzada" role="button" aria-expanded="false" aria-controls="colapsarBusquedaAvanzada">
						    Ver Búsqueda Avanzada
					  	</a>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-lg-12 collapse" id="colapsarBusquedaAvanzada">
						<div class="row">
							<div class="col-lg-6">
								<label>Ambientes</label>

								<form:select path="ambiente" id="ambiente" type="text" class="form-control">
									<form:option value="null">Todas</form:option>
					    			<form:option value="monoambiente">Monoambiente</form:option>
					    			<form:option value="dos ambientes">2 Ambientes</form:option>
					    			<form:option value="tres ambientes">3 Ambientes</form:option>
					    			<form:option value="cuatro ambientes">4 Ambientes</form:option>
								</form:select>
							</div>
							<div class="col-lg-6">
								<label>Precio</label>
								<div class="input-group">
								  <div class="input-group-prepend">
								  </div>
								  <form:input path="precioMin" type="number" class="form-control" placeholder="Mínimo"></form:input>
								  <form:input path="precioMax" type="number" class="form-control" placeholder="Máximo"></form:input>
								</div>
								<c:if test="${not empty error}">
									<h4><span>${error}</span></h4>
									<br>
								</c:if>	
							</div>
						</div>
					</div>
				</div>
				<div class="row justify-content-center" style="margin-top:20px; display:none" id="btnBusquedaAvanzada">
					<div class="col-lg-2 offset-5">
						<button  class="btn btn-buscar" Type="Submit">Buscar</button>
					</div>
				</div>
			</form:form>				
		</div>
        <div class="row" data-aos="fade-up" data-aos-delay="100" style="margin-bottom:100px">
          <div class="col-lg-12 d-flex justify-content-center">
            
          </div>
        </div>

        <div class="row portfolio-container" data-aos="fade-up" data-aos-delay="200" style="min-height: 500px; height:auto!important">          
          <table class="table table-striped">
		    <tbody>    
		    <c:forEach items= "${propiedad}" var="propiedad">
			    <form:form action="fav-propiedad" method="POST" modelAttribute="favorito">		    	
			    	<div class="col-lg-4 col-md-6 portfolio-item filter-web" style="position: absolute; left: 380px; top: 0px;">
			            <div class="portfolio-wrap">
			              <img src="img/portfolio/${propiedad.imagenUrl}" class="img-fluid" alt="">
			              
			              <div class="portfolio-info">
			                <h4>${propiedad.ambiente}, ${propiedad.detalle}</h4>
			                <p>${propiedad.direccion}, ${propiedad.localidad}</p>	
			                <p>${propiedad.condicion}, <i>${propiedad.precio}</i></p>
			                <c:choose>
							    <c:when test="${usuarioBuscado.id == null}">
							    </c:when>    
							    <c:otherwise>
							     	<button type="Submit" class="btn btn-favear" style="margin-left: 20px; margin-top:10px; margin-bottom:10px">Agregar a Fav&nbsp&nbsp<i class="icofont-star"></i></button> 
									<button type="button" class="btn btn-buscar" data-toggle="modal" data-target="#myModal" 
										onclick="pasarDato('${propiedad.id}','${propiedad.ambiente}','${propiedad.detalle}','${propiedad.direccion}',
										'${propiedad.localidad}','${propiedad.fechaPublicada}','${propiedad.provincia}',
										'${propiedad.precio}','${propiedad.imagenUrl}','${propiedad.imagenUrl2}','${propiedad.imagenUrl3}',
										'${propiedad.imagenUrl4}','${propiedad.latitud}','${propiedad.longitud}', '${publicaciones}')">Ver Detalle</button>
							    </c:otherwise>
							</c:choose>				                          
			              </div>		              
			              <input id="idPropiedad" name="idPropiedad" type="hidden" value="${propiedad.id}">
			              <input id="idUsuario" name="idUsuario" type="hidden" value="${usuarioBuscado.id}">		             
			            </div>
			         </div>
			    </form:form>
				<form:form action="detalle-propiedad" method="POST" modelAttribute="detalle">	
					<input id="id" name="id" type="hidden" value="${propiedad.id}">
					<!-- button type="Submit" class="btn btn-favear" style="margin-left: 20px; margin-top:10px;">DETALLE<i class="icofont-star"></i></button-->	    	
				</form:form>
		    </c:forEach>
		    </tbody>
		</table>
        </div>
      </div>
    </section>
	<!--MODAL SECTION-->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<div class="pull-left" style="font-weight:bold">Detalle Propiedad</div>
					<button type="button" class="close" data-dismiss="modal"
						title="Close" style="color:#009970!important">X
						<span class="glyphicon glyphicon-remove"></span>
					</button>
				</div>
				<div class="modal-body">
					<!--begin carousel-->
					<div id="myGallery" class="carousel slide" data-ride="carousel">						
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img id="img1-modal" src="" alt="item0">
<!-- 								<div class="carousel-caption"></div> -->
							</div>
							<div class="item">
								<img id="img2-modal" src="" alt="item1">
<!-- 								<div class="carousel-caption"></div> -->
							</div>
							<div class="item">
								<img id="img3-modal" src="" alt="item2">
<!-- 								<div class="carousel-caption"></div> -->
							</div>
							<div class="item">
								<img id="img4-modal" src="" alt="item3">
<!-- 								<div class="carousel-caption"></div> -->
							</div>
							<!--end carousel-inner-->
						</div>
						<a class="carousel-control-prev" href="#myGallery" role="button" data-slide="prev">
						    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
						  </a>
						  <a class="carousel-control-next" href="#myGallery" role="button" data-slide="next">
						    <span class="carousel-control-next-icon" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						  </a>
<!-- 						<a class="left carousel-control" href="#myGallery" role="button" -->
<!-- 							data-slide="prev"> <span -->
<!-- 							class="glyphicon glyphicon-chevron-left"></span></a> <a -->
<!-- 							class="right carousel-control" href="#myGallery" role="button" -->
<!-- 							data-slide="next"> <span -->
<!-- 							class="glyphicon glyphicon-chevron-right"></span></a> -->
					</div>
					<hr>
					<div class="row">
						<div class="col-12">
							<h5>Sobre la vivienda</h5>							
						</div>
					</div>
					<div class="row">
						<div class="col-4 offset-3">
							<p id="modal-ambiente"></p>
						</div>
						<div class="col-2">
							<p id="modal-detalle"></p>
						</div>						
					</div>
					<div class="row">
						<div class="col-12" style="text-align:center">
							<p id="modal-direccion"></p>
						</div>
					</div>
					<p id=publicaciones></p>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Publicado</th>
								<th scope="col">Precio</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td id="modal-fechapublicada"></td>
								<td id="modal-precio"></td>								
							</tr>
						</tbody>
					</table>
					<div id="map_canvas"></div>
					<!--end modal-body-->
				</div>
				
				<div class="modal-footer">
					<div class="pull-right"></div>
					<input id="modal_idPropiedad" name="modal_idPropiedad" value="">
					<button class="btn btn-sm btn-warning" style="color:white!important" type="submit">Enviar
						a email</button>
					<button class="btn btn-sm btn-danger" type="button" data-dismiss="modal">Salir</button>
					<!--end modal-footer-->
				</div>
				<!--end modal-content-->
			</div>
			<!--end modal-dialoge-->
		</div>
		<!--end myModal-->
	</div>
	<!-- END MODAL SECTION -->
	<!-- VIVIENDAS NUEVAS SECTION -->
	<section id="viviendasNuevas" class="portfolio">
    	<div class="container">
              <div class="section-title" data-aos="fade-left">
                  <h2>Viviendas nuevas</h2>
              </div>
              <div class="row portfolio-container" data-aos="fade-up" data-aos-delay="200" style="min-height: 500px; height:auto!important">
                  <table class="table table-striped">
                      <tbody>
                      <c:forEach items= "${propiedadNueva}" var="propiedadNueva">
                          <div class="col-lg-4 col-md-6 portfolio-item filter-web" style="position: absolute; left: 380px; top: 0px;">
                              <div class="portfolio-wrap">
                                  <img src="img/portfolio/${propiedadNueva.imagenUrl}" class="img-fluid" alt="">
                                  <div class="portfolio-info">
                                      <h4>${propiedadNueva.ambiente}, ${propiedadNueva.detalle}</h4>
                                      <p>${propiedadNueva.direccion}, ${propiedadNueva.localidad}</p>
                                      <p>${propiedadNueva.condicion}, <i>${propiedadNueva.precio}</i></p>
                                  </div>
                              </div>
                          </div>
                      </c:forEach>
                      </tbody>
                  </table>
              </div>
          </div>
    </section><!-- End Portfolio Section -->
	<!-- END VIVIENDAS NUEVAS SECTION -->
  </main><!-- End #main -->

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
        <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
        <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
        <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
        <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
        <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
      </div>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

  <!-- Vendor JS Files -->
  
  <script src="vendor/jquery/jquery.min.js"></script>
<!--   <script src="vendor/bootstrap/js/bootstrap.js"></script> -->
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="vendor/jquery.easing/jquery.easing.min.js"></script>
<!--   <script src="vendor/php-email-form/validate.js"></script> -->
  <script src="vendor/waypoints/jquery.waypoints.min.js"></script>
  <script src="vendor/counterup/counterup.min.js"></script>
  <script src="vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="vendor/venobox/venobox.min.js"></script>
  <script src="vendor/owl.carousel/owl.carousel.min.js"></script>l
  <script src="vendor/aos/aos.js"></script>

  <!-- Template Main JS File -->
  <script src="js/main.js"></script>
  <script src="js/myjs.js"></script>
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyA7CHxy-Z9ayDwj1yb8qshjMSXGXTAkKd0&sensor=false"></script>
</body>

</html>

