package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorLogin {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor como mecanismo de inyección de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un paquete de los indicados en
	// applicationContext.xml
	private ServicioLogin servicioLogin;
	

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin){
		this.servicioLogin = servicioLogin;
	}

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			HttpSession session = request.getSession();			
			session.setAttribute("usuarioBuscado", usuarioBuscado);			
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			return new ModelAndView("redirect:/propiedad");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("propiedad");
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path="/registro", method = RequestMethod.GET)
	public ModelAndView irARegistro() {
		return new ModelAndView("registrar");
	}
	
	@RequestMapping(path="/registrar", method = RequestMethod.POST)
	public ModelAndView registrarse(@ModelAttribute("usuario") Usuario usuario) {
		ModelMap model = new ModelMap();
		Usuario usuarioNuevo = new Usuario();
		Usuario usuarioBuscado = servicioLogin.consultarUsuarioExistente(usuario);
		
		if(usuarioBuscado == null) {
			usuarioNuevo.setNombre(usuario.nombre);
			usuarioNuevo.setEmail(usuario.email);
			usuarioNuevo.setPassword(usuario.password);
			
			
		    String emailDestinatario = usuario.email;
		    String asunto = "Te damos la bienvenida a HouseHold";
		    String cuerpo = "Ahora podr�s obtener y vender la propiedad que desees";
		     
			servicioLogin.registrarUsuario(usuarioNuevo);
			enviarMail(emailDestinatario, asunto, cuerpo);
			
			model.put("error", "Usuario registrado correctamente");
			
		} else {
			model.put("error", "El email ingresado ya esta registrado");
		}		
		
		return new ModelAndView("registrar", model);
	}
	public  void enviarMail(String destinatario, String asunto, String cuerpo) {
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
	
	
	
	
}
