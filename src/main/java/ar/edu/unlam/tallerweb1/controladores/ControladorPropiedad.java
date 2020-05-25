package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedad;

@Controller
public class ControladorPropiedad {

	private  ServicioPropiedad servicioPropiedad;
	
	@Autowired
	public  ControladorPropiedad(ServicioPropiedad servicioPropiedad) {
		this.servicioPropiedad = servicioPropiedad;
	}


    @RequestMapping("/propiedad")
    public ModelAndView propiedades(){
        ModelMap model = new ModelMap();
        Propiedad propiedadFiltro = new Propiedad();
        List<Propiedad> listaPropiedad = servicioPropiedad.consultarPropiedad();
        model.put("propiedad", listaPropiedad);
        model.put("propiedadFiltro", propiedadFiltro);
    return new ModelAndView("propiedad", model);
    }
    
    @RequestMapping(path = "/filtro-propiedad", method = RequestMethod.POST)
    public ModelAndView filtraPropiedades(@ModelAttribute("propiedad") Propiedad propiedad, HttpServletRequest request){
        ModelMap model = new ModelMap();
        Propiedad propiedadFiltro = new Propiedad();
        List<Propiedad> listaPropiedad = servicioPropiedad.consultarPropiedadFilter(propiedad);
        model.put("propiedadFiltro", propiedadFiltro);
        model.put("propiedad", listaPropiedad);
    return new ModelAndView("propiedad", model);
    }
    
    @RequestMapping(path = "/viviendasNuevas", method = RequestMethod.POST)
    public ModelAndView listarNuevasViviendas(@ModelAttribute("propiedad") Propiedad propiedad, HttpServletRequest request){
        ModelMap model = new ModelMap();
        Propiedad propiedadNueva = new Propiedad();
        List<Propiedad> listaNuevasPropiedades = servicioPropiedad.consultarNuevasPropiedades();
       
        model.put("propiedadNueva", listaNuevasPropiedades);
    	return new ModelAndView("propiedadNueva", model);
    }

}






















