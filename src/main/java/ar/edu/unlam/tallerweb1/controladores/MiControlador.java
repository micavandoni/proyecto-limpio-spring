package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MiControlador {
	
	@RequestMapping(path="/saludar", method= RequestMethod.GET)
	public ModelAndView saludar(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido) {
		
		ModelMap model = new ModelMap();
		model.put("nombre", nombre.toUpperCase());
		model.put("apellido", apellido.toUpperCase());
		
		return new ModelAndView("saludo", model);
		
	}
	
	//con GET
	
	// PathVariable
	@RequestMapping("saludar/{nombre}/con-apellido/{apellido}")
	public ModelAndView saludo(@PathVariable String nombre, @PathVariable String apellido) {
			
		ModelMap modelo1 = new ModelMap();
		modelo1.put("nombre", nombre);
		modelo1.put("apellido", apellido);
		return new ModelAndView("saludo", modelo1);
	}
	
}