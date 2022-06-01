package com.docente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docente.dto.BuffetDTO;
import com.docente.model.Buffet;
import com.docente.model.Ingrediente;
import com.docente.service.IngredienteService;

@Controller
public class IngredienteController {

	@Autowired IngredienteService ingredienteService;
	
	@GetMapping("/ingrediente/get/all")
	public String getAllIngredienti(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.getAllIngredienti());
		return "ingredienti.html";
	}
	
	@GetMapping("/ingrediente/get/{id}")
	public String getIngredienteById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.getIngredienteById(id));
		return "ingrediente.html";
	}
	
	@GetMapping("/ingrediente/form/add")
	public String getFormIngredienteAdd(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingredienteForm.html";
	}
	
	@GetMapping("/ingrediente/form/modify/{id}")
	public String getFormIngredienteModify(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = this.ingredienteService.getIngredienteById(id); 
		if (ingrediente != null) {
			model.addAttribute("ingrediente", ingrediente);
			return "ingredienteForm.html";
		}
		return "index.html";
	}
	
	@PostMapping("/ingrediente/add")
	public String addIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente, Model model) {
		this.ingredienteService.saveIngrediente(ingrediente);
		if (ingrediente != null) {
			model.addAttribute("ingrediente", ingrediente); 
			return "ingrediente.html";
		}
		return "ingredienteForm.html";  
	}
	
	@GetMapping("/ingrediente/delete/{id}")
	public String deleteIngrediente(@PathVariable("id") Long id, Model model) {
		this.ingredienteService.deleteIngrediente(id);
		model.addAttribute("ingredienti", this.ingredienteService.getAllIngredienti());
		return "ingredienti.html";
	}
	
}
