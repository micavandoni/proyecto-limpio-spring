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
			btnRegistrar.style.display = "block";
			mensaje.style.display = "none";
		} else {
			btnRegistrar.style.display = "none";
			mensaje.style.display = "block";
			console.log('mal');
		}
	} else {
		console.log('mal2');
		btnRegistrar.style.display = "none";
		mensaje.style.display = "block";
	}
		
	
	
}