/**
 * 
 */
function propiedad(latitud, longitud){
	var myCenter = new google.maps.LatLng(latitud, longitud);
	var marker = new google.maps.Marker({position: myCenter,});
	var mapOptions = {center: myCenter,zoom: 16,mapTypeId: google.maps.MapTypeId.ROADMAP,panControl: true,zoomControl: true,scaleControl: true,};
	var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	marker.setMap(map);
}
