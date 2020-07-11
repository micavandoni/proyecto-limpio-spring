package ar.edu.unlam.tallerweb1.controladores;


import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unlam.tallerweb1.clases.Generico;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedad;

@Controller
public class ControladorPropiedad {

	private ServicioPropiedad servicioPropiedad;
	private ServicioLogin servicioLogin;
	
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

        HttpSession session = request.getSession();
        session.getAttribute("usuarioBuscado");
        Usuario usuario = (Usuario) session.getAttribute("usuarioBuscado");

        servicioPropiedad.guardarFavoritoSeleccionado(favoritoSeleccionado, usuario);
        
        List<Propiedad> listaPropiedad = new ArrayList<Propiedad>();

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
    
    public Boolean condicionValida(Propiedad propiedad) {
    	
		if(propiedad.getCondicion()!=null && propiedad.getCondicion().equalsIgnoreCase("null")) {
			propiedad.setCondicion(null);	
		}
		if(propiedad.getAmbiente()!=null && propiedad.getAmbiente().equalsIgnoreCase("null")) {
			propiedad.setAmbiente(null);
		}
    	 return true;
    }
    
    public void validarRango(Propiedad propiedad) throws Exception {
    	
		if(propiedad.getPrecioMin()!=null && propiedad.getPrecioMax()!=null) {
			if(propiedad.getPrecioMin() > propiedad.getPrecioMax()) {
				
				throw new RuntimeException("Precio Maximo debe ser mayor a Precio Minimo!!!");
			}
		}
    }
    
    @RequestMapping(value = "/envioMail/{mail}", method = RequestMethod.GET)
    public ModelAndView envioMail(@PathVariable("mail") String mail, HttpServletRequest request) {

 	   HttpSession session = request.getSession();
       Usuario usuario = (Usuario)session.getAttribute("usuarioBuscado");
       
       ModelMap model = new ModelMap();

       String remitente = usuario.getEmail();
       String emailDestinatario = mail;
       String asunto = remitente + "Me interesa tu casa capo!!!!!";
       String cuerpo = asunto;

       enviarConGMail(emailDestinatario, asunto, cuerpo);
       
       return new ModelAndView("partidos", model);

    }
    
    public  void enviarConGMail(String destinatario, String asunto, String cuerpo) {
    	   // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
    	   // remitente tambi�n.
    	   String remitente = "householdstw1@gmail.com"; // Para la direcci�n nomcuenta@gmail.com

    	   java.util.Properties props = System.getProperties();
    	   props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
    	   props.put("mail.smtp.user", remitente);
    	   props.put("mail.smtp.clave", "house2020"); // La clave de la cuenta
    	   props.put("mail.smtp.auth", "true"); // Usar autenticaci�n mediante usuario y clave
    	   props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
    	   props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
    	   



    	   Session session = Session.getDefaultInstance(props);
    	   MimeMessage message = new MimeMessage(session);

    	   try {
    	      message.setFrom(new InternetAddress(remitente));
    	      message.addRecipients(Message.RecipientType.TO, destinatario); // Se podr�an a�adir varios de la misma
    	                                                      // manera
    	      message.setSubject(asunto);
    	      message.setText(cuerpo);
    	      Transport transport = session.getTransport("smtp");
    	      transport.connect("smtp.gmail.com", remitente, "house2020");
    	      transport.sendMessage(message, message.getAllRecipients());
    	      transport.close();
    	   } catch (MessagingException me) {
    	      me.printStackTrace(); // Si se produce un error
    	   }
    	}

    @RequestMapping(path = "/crear", method = RequestMethod.GET)
    public ModelAndView crear() {
        servicioPropiedad.crearEventos();
        return new ModelAndView("redirect:/login");
    }

}