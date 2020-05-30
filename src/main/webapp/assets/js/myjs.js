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