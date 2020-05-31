package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
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
@SessionAttributes({"usuarioBuscado"})
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
        MiControlador favorito = new MiControlador();
        
        List<Propiedad> listaPropiedad = servicioPropiedad.consultarPropiedad();
        List<Propiedad> listaPropiedades = servicioPropiedad.consultarNuevasPropiedades();
        //Integer listaContadores = servicioPropiedad.listaContadores();
        
        model.put("propiedadNueva", listaPropiedades);
        model.put("propiedad", listaPropiedad);
        model.put("propiedadFiltro", propiedadFiltro);
        model.put("favorito", favorito);
        //model.put("contadores", listaContadores);
        
        return new ModelAndView("propiedad", model);
    }
    
    @RequestMapping(path = "/filtro-propiedad", method = RequestMethod.POST)
    public ModelAndView filtraPropiedades(@ModelAttribute("propiedad") Propiedad propiedad, HttpServletRequest request){
    	
        ModelMap model = new ModelMap();
        
        Propiedad propiedadFiltro = new Propiedad();        
        List<Propiedad> listaPropiedad = servicioPropiedad.consultarPropiedadFilter(propiedad);
        MiControlador favorito = new MiControlador();
        
        model.put("propiedadFiltro", propiedadFiltro);
        model.put("propiedad", listaPropiedad);       
        model.put("favorito", favorito);
        
        return new ModelAndView("propiedad", model);
    }
    
    @RequestMapping(path = "/loguearse", method = RequestMethod.GET)
    public ModelAndView irALoginOPerfil(HttpServletRequest request) {
    	HttpSession session = request.getSession();	
    	
    	if (session.getAttribute("usuarioBuscado") == null) {
    		return new ModelAndView("redirect:/login");
    	} else {
    		return new ModelAndView("redirect:/perfil");
    	}
    	
    }
    
    @RequestMapping(path = "/fav-propiedad", method = RequestMethod.POST)
    public ModelAndView FavPropiedad(@ModelAttribute("favorito") MiControlador favoritoSeleccionado, HttpServletRequest request) {
    	
    	HttpSession session = request.getSession();	
    	session.getAttribute("usuarioBuscado");
    	
    	ModelMap model = new ModelMap();
    	
        Propiedad propiedadFiltro = new Propiedad();
        Favorito favorito2 = new Favorito();
        List<Propiedad> listaPropiedades = servicioPropiedad.consultarNuevasPropiedades();
        List<Propiedad> listaPropiedad = servicioPropiedad.consultarPropiedad();
        
        favorito2.setIdPropiedad(favoritoSeleccionado.idPropiedad);
        favorito2.setIdUsuario(favoritoSeleccionado.idUsuario);        
        
        servicioPropiedad.favPropiedad(favorito2);        
        
        model.put("propiedadNueva", listaPropiedades);        
        model.put("propiedad", listaPropiedad);
        model.put("favorito", favorito2);
        model.put("propiedadFiltro", propiedadFiltro);
        
        return new ModelAndView("propiedad", model);
    }

}






















