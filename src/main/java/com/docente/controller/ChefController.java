package com.docente.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.docente.model.Chef;
import com.docente.service.ChefService;

@Controller
public class ChefController {

	@Autowired ChefService chefService;
	
	@PostMapping("/chef/add")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) { 
		/*
		 *  Questo metodo validate, che abbiamo creato nella classe ChefValidator
		 *  inserirà all'interno del param bindingResult un errore nel caso questo Chef già esista
		*/
		//this.chefValidator.validate(chef, bindingResult);
		if (!bindingResult.hasErrors()) { // I dati inseriti sono corretti
			if (chef.getId() != null)
				model.addAttribute("modified", true);
			this.chefService.saveChef(chef); //Salvo lo chef
			model.addAttribute("chef", chef); //Aggiungo al template il model chef, con i dati inseriti inizialmente nella form
			return "chef.html"; //Torno il template
		}
		return "chefForm.html"; //Torno il template della form poiché sono stati rilevati errori
	}
	
	@GetMapping("/chef/form/add")
	public String getFormAddChef(Model model) {   
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}
	
	@GetMapping("/chef/form/modify/{id}")
	public String getFormModifyChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.getById(id));
		model.addAttribute("modifyForm", true); 
		return "chefForm.html";
	}
	
	@GetMapping("/chef/get/all")
	public String getAllChefs(Model model) {
		model.addAttribute("chefs", this.chefService.getAllChefs());
		return "chefs.html";
	}
	
	@GetMapping("/chef/get/{id}")
	public String getChefById(@PathVariable("id") Long id, Model model) {
		Chef chef = this.chefService.getById(id);
		model.addAttribute("chef", chef);
		return "chef.html";
	}
	
	@PostMapping("/chef/delete/{id}")
	public String deleteChefById(@PathVariable("id") Long id, Model model) {
		this.chefService.deleteChefById(id);
		model.addAttribute("chefs", this.chefService.getAllChefs());
		return "chefs.html";
	}
	
	
	
}
