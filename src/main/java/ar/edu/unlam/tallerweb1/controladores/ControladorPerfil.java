package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.servicios.ServicioFavorito;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


@Controller
public class ControladorPerfil {

    private ServicioFavorito servicioFavorito;
    private ServicioPropiedad servicioPropiedad;

    @Autowired
    public  ControladorPerfil  (ServicioFavorito servicioFavorito, ServicioPropiedad servicioPropiedad) {
        this.servicioFavorito = servicioFavorito;
        this.servicioPropiedad = servicioPropiedad;
    }
    
	@RequestMapping("/perfil")
    public ModelAndView perfil(HttpServletRequest request){
    	
		HttpSession session = request.getSession();	
    	session.getAttribute("usuarioBuscado");
    	ModelMap model = new ModelMap();  
    	
    	Usuario usuario = (Usuario) session.getAttribute("usuarioBuscado");
    	List<Favorito> propiedadesFavoritas = servicioFavorito.propiedadesFavoritas(usuario);
    	List<Propiedad> propiedadesUsuarios = servicioPropiedad.propiedadesFavoritasDeUnUsuario(propiedadesFavoritas);
        model.put("propiedadesFavs", propiedadesUsuarios);
        
        return new ModelAndView("perfil", model);
    }
	
	@RequestMapping(path = "/irAPropiedad", method = RequestMethod.GET)
    public ModelAndView irAPropiedad(HttpServletRequest request) {
    	
    	return new ModelAndView("redirect:/propiedad");
    	
    }

}






















