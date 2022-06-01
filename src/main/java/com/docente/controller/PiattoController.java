package com.docente.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.docente.dto.PiattiSelectorDTO;
import com.docente.dto.PiattoDTO;
import com.docente.model.Buffet;
import com.docente.model.Ingrediente;
import com.docente.model.Piatto;
import com.docente.service.BuffetService;
import com.docente.service.ChefService;
import com.docente.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired PiattoService piattoService; 
	@Autowired ChefService chefService;
	@Autowired BuffetService buffetService;
	 
	
	/*@PostMapping("/piatto/add")
	public String addPiatto(@Valid @ModelAttribute("piattoDTO") PiattoDTO piattoDTO, Model model) {
		Piatto piatto = this.piattoService.savePiatto(piattoDTO);
		if (piatto != null) {
			model.addAttribute("piatto", piatto); 
			return "piatto.html";
		}
		return "piattoForm.html";  
	}*/
	
	@PostMapping("/piatto/add")
	public String addPiatto(@ModelAttribute("piatto") Piatto piatto, Model model) {  
		
		System.out.println("ID: " + piatto.getId()); 
		System.out.println("Nome: " + piatto.getNome());
		System.out.println("Desc: " + piatto.getDescrizione());
		System.out.println("Chef nome: " + piatto.getChef().getNome());
		for (Buffet b : piatto.getBuffets()) {
			System.out.println("Buffet nome: " + b.getNome());
		}
		for (Ingrediente i : piatto.getIngredienti()) {
			System.out.println("Ingrediente nome: " + i.getNome());
		}
		
		this.piattoService.savePiatto(piatto);
		model.addAttribute("piatti", this.piattoService.getAllPiatti());
		return "piatti.html";
	}
	 
	@GetMapping("/piatto/get/all")
	public String getAllPiatti(Model model) {
		model.addAttribute("piatti", this.piattoService.getAllPiatti());
		return "piatti.html";
	}
	
	@GetMapping("/piatto/get/chef={chefId}")
	public String getAllPiattiByChefId(@PathVariable("chefId") Long chefId, Model model) {
		model.addAttribute("piatti", this.piattoService.getAllPiattiByChefId(chefId));
		return "piatti.html"; 
	}
	
	@GetMapping("/piatto/form/get/all/chef={chefId}buffet={buffetId}")
	// "/buffet/add/piatti/chef={chefId}buffet={buffetId}
	public String getFormSelectPiattiByChefId(@PathVariable("chefId") Long chefId, @PathVariable("buffetId") Long buffetId, Model model) {    
		Buffet buffet = this.buffetService.getBuffetById(buffetId);
		PiattiSelectorDTO piattiSelectorDTO = new PiattiSelectorDTO(buffetId, this.piattoService.getAllPiattiByChefId(chefId), buffet.getPiatti());
		model.addAttribute("piattiSelectorDTO", piattiSelectorDTO);
		return "piattiSelector.html"; 
	}
	
	@PostMapping("/buffet/{buffetId}/add/piatti")
	public String addPiattiToBuffet(@PathVariable("buffetId") Long buffetId, @ModelAttribute("piattiSelectorDTO") PiattiSelectorDTO dto, Model model) {
		Buffet buffet = this.piattoService.addPiattiToBuffet(dto);
		model.addAttribute("buffet", buffet);
		return "buffet.html";
	}
	
	@GetMapping("/piatto/get/{id}")
	public String getPiattoById(@PathVariable("id") Long id, Model model) { 
		Piatto piatto = this.piattoService.getPiattoById(id);
		if (piatto != null) {
			model.addAttribute("piatto", piatto);
			return "piatto.html";
		}
		return "index.html";
	}
	
	/*@GetMapping("/piatto/form/add")
	public String getFormAddPiatto(Model model) {
		model.addAttribute("piattoDTO", new PiattoDTO(this.chefService.getAllChefs()));
		model.addAttribute("modifyForm", false); 
		return "piattoForm.html";
	}*/
	
	@GetMapping("/piatto/form/add")
	public String getFormAddPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("chefs", this.chefService.getAllChefs());
		model.addAttribute("modifyForm", false); 
		return "piattoForm.html"; 
	}
	
	@GetMapping("/piatto/form/modify/{id}")
	public String getFormModifyPiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = this.piattoService.getPiattoById(id);
		if (piatto != null) {
			model.addAttribute("piattoDTO", new PiattoDTO(piatto, this.chefService.getAllChefs()));
			model.addAttribute("modifyForm", true); 
			return "piattoForm.html";
		}
		return "index.html";
	}
	
	@PostMapping("/piatto/delete/{id}")
	public String deletePiattoById(@PathVariable("id") Long id, Model model) {
		this.piattoService.deletePiattoById(id);
		model.addAttribute("piatti", this.piattoService.getAllPiatti());
		return "piatti.html";
	}
	
}
