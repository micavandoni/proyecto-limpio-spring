package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class ControladorPropiedad {

    @Inject
    ServicioPropiedad servicioPropiedad;

    @RequestMapping(path = "/propiedades", method = RequestMethod.GET)
    public ModelAndView propiedades(){
        ModelMap model = new ModelMap();
        List<Propiedad> listaPropiedad =servicioPropiedad.consultarPropiedad();
        model.put("propiedades", listaPropiedad);
    return new ModelAndView("propiedades", model);
    }
    
    @RequestMapping(path = "/propiedad", method = RequestMethod.GET)
    public ModelAndView propiedad() {
    	ModelMap model = new ModelMap();
    	
    	return new ModelAndView("propiedad", model);
    }
}
