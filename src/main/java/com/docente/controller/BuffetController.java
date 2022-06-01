package com.docente.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.docente.dto.BuffetDTO;
import com.docente.model.Buffet;
import com.docente.service.ChefService;
import com.docente.service.BuffetService;

@Controller
public class BuffetController {

	@Autowired BuffetService buffetService; 
	@Autowired ChefService chefService;
	
	
	/*@PostMapping("/buffet/add")
	public String addBuffet(@Valid @ModelAttribute("buffetDTO") BuffetDTO buffetDTO, Model model) {
		Buffet buffet = this.buffetService.saveBuffet(buffetDTO);
		if (buffet != null) {
			model.addAttribute("buffet", buffet); 
			return "buffet.html";
		}
		return "buffetForm.html";  
	}*/
	
	@PostMapping("/buffet/add")
	public String addBuffet(@ModelAttribute("buffet") Buffet buffet, Model model) {
		this.buffetService.saveBuffet(buffet);
		model.addAttribute("buffets", this.buffetService.getAllBuffets());
		return "buffets.html";
	}
	
	@GetMapping("/buffet/form/add")
	public String getFormAddBuffet(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs", this.chefService.getAllChefs());
		return "buffetForm.html";
	}
	
	/*@GetMapping("/buffet/form/add")
	public String getFormAddBuffet(Model model) {
		model.addAttribute("buffetDTO", new BuffetDTO(this.chefService.getAllChefs()));
		return "buffetForm.html";
	}*/
	 
	@GetMapping("/buffet/get/all")
	public String getAllBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.getAllBuffets());
		return "buffets.html";
	}
	
	@GetMapping("/buffet/get/chef={chef_id}")
	public String getAllBuffetsByChefId(@PathVariable("chef_id") Long chef_id, Model model) {
		model.addAttribute("buffets", this.buffetService.getAllBuffetsByChefId(chef_id));
		return "buffets.html";
	}
	
	@GetMapping("/buffet/get/{id}")
	public String getBuffetById(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.getBuffetById(id);
		if (buffet != null) {
			model.addAttribute("buffet", buffet);
			return "buffet.html";
		}
		return "index.html";
	}
	
	/*@GetMapping("/buffet/form/modify/{id}")
	public String getFormModifyBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.getBuffetById(id); 
		if (buffet != null) {
			model.addAttribute("buffetDTO", new BuffetDTO(buffet, this.chefService.getAllChefs()));
			return "buffetForm.html";
		}
		return "index.html";
	}*/
	
	@PostMapping("/buffet/delete/{id}")
	public String deleteBuffetById(@PathVariable("id") Long id, Model model) {
		this.buffetService.deleteBuffetById(id);
		model.addAttribute("buffets", this.buffetService.getAllBuffets());
		return "buffets.html";
	}
	
}
