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
function pasarDato(ambiente,detalle,direccion,localidad,fechapublicada,provincia,precio,imagenUrl,imagenUrl2, imagenUrl3, imagenUrl4){
	//var valor=id;
	var ambiente=ambiente;
	var detalle=detalle;
	var direccion=direccion;
	var localidad =localidad;
	var fechapublicada=fechapublicada;
	var precio=precio;
	var provincia=provincia;
	
	document.getElementById("img1-modal").src="img/portfolio/".concat(imagenUrl);
	document.getElementById("img2-modal").src="img/portfolio/".concat(imagenUrl2);
	document.getElementById("img3-modal").src="img/portfolio/".concat(imagenUrl3);
	document.getElementById("img4-modal").src="img/portfolio/".concat(imagenUrl4);
	document.getElementById('modal-ambiente').value=ambiente;
	document.getElementById('modal-detalle').value=detalle;
	document.getElementById('modal-direccion').value=direccion;
	document.getElementById('modal-localidad').value=localidad;
	document.getElementById('modal-fechapublicada').value=fechapublicada;
	document.getElementById('modal-precio').value=precio;
	document.getElementById('modal-provincia').value=provincia;
	
	$('#myModal').modal('show');

}
