package com.example.demo.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.models.entity.Persona;
import com.example.demo.app.models.service.IPersonaService;

@Controller
@RequestMapping("/views/imc")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping("/")
	public String listarPersonas(Model model) {
		
		List<Persona> listadoPersonas = personaService.listarTodos();
		
		model.addAttribute("titulo", "Lista de Personas");
		model.addAttribute("personas", listadoPersonas);
		
		return "/views/imc/listar";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		
		Persona persona = new Persona();
		
		
		model.addAttribute("titulo", "Formulario Personas");
		model.addAttribute("persona", persona);
		
		return "/views/imc/frmCrear";
	}
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Persona persona, BindingResult result, Model model) {
		
		
		float est = Float.parseFloat(persona.getEstatura());
		float pes = Float.parseFloat(persona.getPeso());
		
		float imc = (pes / (est*est));
		
		persona.setImc(imc);
		
		personaService.guardar(persona);
		
		System.out.println("se pudo");
		
		return "redirect:/views/imc/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idPersona, Model model) {
		
		Persona persona = personaService.buscarPorId(idPersona);
		
		
		model.addAttribute("titulo", "Formulario Editar Personas");
		model.addAttribute("persona", persona);
		
		return "/views/imc/frmCrear";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long idPersona) {
		
		personaService.eliminar(idPersona);
		
		return "redirect:/views/imc/";
	}
}
