package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedad;

@Controller
public class ControladorPerfil {

	// private  ServicioPropiedad servicioPropiedad;
	@RequestMapping("/perfil")
    public ModelAndView perfil(HttpServletRequest request){
    	
		HttpSession session = request.getSession();	
    	session.getAttribute("usuarioBuscado");
    	
        ModelMap model = new ModelMap();        
        
        return new ModelAndView("perfil", model);
    }
	
	@RequestMapping(path = "/irAPropiedad", method = RequestMethod.GET)
    public ModelAndView irAPropiedad(HttpServletRequest request) {
    	
    	return new ModelAndView("redirect:/propiedad");
    	
    }

}






















