package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedad;


@Controller
public class ControladorPerfil {


    private ServicioPropiedad servicioPropiedad;

    @Autowired
    public  ControladorPerfil  (ServicioPropiedad servicioPropiedad) {

        this.servicioPropiedad = servicioPropiedad;
    }
    
	@RequestMapping("/perfil")
    public ModelAndView perfil(HttpServletRequest request){
		
		ModelMap model = new ModelMap();
		
		Set<Propiedad> propiedadesUsuarios = new HashSet<>();
    	
		HttpSession session = request.getSession();	
    	session.getAttribute("usuarioBuscado");
    	Usuario usuario = (Usuario) session.getAttribute("usuarioBuscado");
    	
    	propiedadesUsuarios = servicioPropiedad.propiedadesFavoritasDeUnUsuario(usuario);
        model.put("propiedadesFavs", propiedadesUsuarios);
        
        return new ModelAndView("perfil", model);
    }
	
	@RequestMapping(path = "/irAPropiedad", method = RequestMethod.GET)
    public ModelAndView irAPropiedad(HttpServletRequest request) {
    	
    	return new ModelAndView("redirect:/propiedad");
    	
    }

}






















