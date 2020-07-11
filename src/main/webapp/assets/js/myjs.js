function mostrarBtnBusqueda() {
	
	var btnBusquedaComun = document.getElementById('btnBusquedaComun');
	var btnBusquedaAvanzada = document.getElementById('btnBusquedaAvanzada');
	var texto = document.getElementById('txtBusqueda');
	
	if (btnBusquedaAvanzada.style.display === "none") {
		btnBusquedaAvanzada.style.display = "block";
		btnBusquedaComun.style.display = "none";
		texto.innerHTML = "Cerrar Búsqueda Avanzada";
	  } else {
		  btnBusquedaAvanzada.style.display = "none";
		  btnBusquedaComun.style.display = "block";
		  texto.innerHTML = "Ver Búsqueda Avanzada";
	  }
}

function favear() {
	var iconFav = document.getElementById('iconFav');
	
	if(iconFav.style.color === "white") {
		iconFav.style.color = "yellow";
		
	}
	else {
		iconFav.style.color = "white";
	}
}
function pasarDato(idPropiedad,ambiente,detalle,direccion,localidad,fechapublicada,provincia,precio,imagenUrl,imagenUrl2, imagenUrl3, imagenUrl4, latitud, longitud, publicaciones){
	//var valor=id;
	var ambiente=ambiente;
	var detalle=detalle;
	var direccion=direccion;
	var localidad =localidad;
	var fechapublicada=fechapublicada;
	var precio=precio;
	var provincia=provincia;
	var publicaciones=publicaciones;
	
	document.getElementById("img1-modal").src="img/portfolio/".concat(imagenUrl);
	document.getElementById("img2-modal").src="img/portfolio/".concat(imagenUrl2);
	document.getElementById("img3-modal").src="img/portfolio/".concat(imagenUrl3);
	document.getElementById("img4-modal").src="img/portfolio/".concat(imagenUrl4);
	document.getElementById('modal-ambiente').innerHTML =' Ambientes: ' + ambiente;
	document.getElementById('modal-detalle').innerHTML='A ' + detalle;
	document.getElementById('modal-direccion').innerHTML='Dirección: ' + direccion +' - ' + localidad + ' - ' + provincia;
	//document.getElementById('modal-localidad').innerHTML=localidad;
	document.getElementById('modal-fechapublicada').innerHTML=fechapublicada;
	document.getElementById('modal-precio').innerHTML=precio;
	//document.getElementById('modal-provincia').innerHTML=provincia;
	document.getElementById('modal_idPropiedad').innerHTML=idPropiedad;
	document.getElementById('publicaciones').innerHTML = publicaciones;
		
	var myCenter=new google.maps.LatLng(latitud, longitud);
	var marker = new google.maps.Marker({position: myCenter,});
	var mapOptions = {center: myCenter,zoom: 16,mapTypeId: google.maps.MapTypeId.ROADMAP,panControl: true,zoomControl: true,scaleControl: true,};
	var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	marker.setMap(map);

	$('#myModal').modal('show');
}


function validarContrasenia(){
	var pass1 = document.getElementById('password').value;
	var pass2 = document.getElementById('repetirPassword').value;
	var btnRegistrar = document.getElementById('registrar');
	var mensaje = document.getElementById('mensaje');
	
	var longPass1 = pass1.length;
	var longPass2 = pass2.length;
	
	if (longPass2 == longPass2 ){
		if(pass1 === pass2){
			console.log(longPass1);
			btnRegistrar.disabled = false;
			mensaje.style.display = "none";
		} else {
			btnRegistrar.disabled = true;
			mensaje.style.display = "block";
		}
	} else {
		btnRegistrar.disabled = false;
		mensaje.style.display = "block";
	}
}
	
