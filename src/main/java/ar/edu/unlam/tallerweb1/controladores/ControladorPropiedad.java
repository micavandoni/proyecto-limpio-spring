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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.clases.Generico;
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
               
        List<Propiedad> listaPropiedad = servicioPropiedad.consultarPropiedad();
        model.put("propiedad", listaPropiedad);
   
        loadGenericModel(model);
        
        return new ModelAndView("propiedad", model);
    }
    
    @RequestMapping(path = "/filtro-propiedad", method = RequestMethod.POST)
    public ModelAndView filtraPropiedades(@ModelAttribute("propiedad") Propiedad propiedad, HttpServletRequest request){
    	
        ModelMap model = new ModelMap();

        List<Propiedad> listaPropiedad = new ArrayList<Propiedad>();

		try {
			condicionValida(propiedad);
			validarRango(propiedad);
			listaPropiedad = servicioPropiedad.consultarPropiedadFilter(propiedad);
			
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}

        model.put("propiedad", listaPropiedad);       
        loadGenericModel(model);
        
		HttpSession session = request.getSession();			
		session.setAttribute("filtroSel", propiedad);
        
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
    public ModelAndView FavPropiedad(@ModelAttribute("favorito") Generico favoritoSeleccionado, HttpServletRequest request) {
    	
    	ModelMap model = new ModelMap();

        servicioPropiedad.guardarFavoritoSeleccionado(favoritoSeleccionado); 
        
        List<Propiedad> listaPropiedad = new ArrayList<Propiedad>();
        
        HttpSession session = request.getSession();
        
    	if (session.getAttribute("filtroSel") != null) {
    		Propiedad propFiltro = new Propiedad();
    		propFiltro = (Propiedad)session.getAttribute("filtroSel");
    		listaPropiedad = servicioPropiedad.consultarPropiedadFilter(propFiltro);
    		
    	} else {
    		listaPropiedad = servicioPropiedad.consultarPropiedad();
    	}
        
    	model.put("propiedad", listaPropiedad); 
        loadGenericModel(model);
        
        return new ModelAndView("propiedad", model);
    }
    
    private void loadGenericModel(ModelMap model) {
    	
        Propiedad propiedadFiltro = new Propiedad();
        Generico favorito = new Generico();
        
        List<Propiedad> listaPropiedades = servicioPropiedad.consultarNuevasPropiedades();
        List listaContadores = servicioPropiedad.listaContadores();
        
        model.put("propiedadNueva", listaPropiedades);
        model.put("propiedadFiltro", propiedadFiltro);
        model.put("favorito", favorito);
        model.put("contadores", listaContadores);
    	
    }
    
    private void condicionValida(Propiedad propiedad) {
    	
		if(propiedad.getCondicion().equalsIgnoreCase("null")) {
			propiedad.setCondicion(null);	
		}
		if(propiedad.getAmbiente().equalsIgnoreCase("null")) {
			propiedad.setAmbiente(null);
		}
    	
    }
    
    public void validarRango(Propiedad propiedad) throws Exception {
    	
		if(propiedad.getPrecioMin()!=null && propiedad.getPrecioMax()!=null) {
			if(propiedad.getPrecioMin() > propiedad.getPrecioMax()) {
				
				throw new RuntimeException("Precio Maximo debe ser mayor a Precio Minimo!!!");
			}
			
		}
    }

    @RequestMapping(path = "/crear", method = RequestMethod.GET)
    public ModelAndView crear() {
        servicioPropiedad.crearEventos();
        return new ModelAndView("redirect:/login");
    }

}