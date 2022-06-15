package com.spring.service;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Chef;
import com.spring.model.Ingrediente;
import com.spring.model.Piatto;
import com.spring.repository.ChefRepository;
import com.spring.repository.IngredienteRepository;

@Service
public class ChefService {

	@Autowired private PiattoService piattoService;
	@Autowired private ChefRepository chefRepository;
	@Autowired private IngredienteRepository ingredienteRepository;

	public Set<Chef> getAllChefs() {
		Set<Chef> chefs = new HashSet<Chef>();
		for (Chef chef : chefRepository.findAll()) {
			chefs.add(chef);
		}
		return chefs;
	}
	
	public Chef getChefById(Long id) {
		return this.chefRepository.findById(id).get();
	}
		
	@Transactional
	public Chef saveChef(Chef chef) {
		return this.chefRepository.save(chef);
	}
	
	@Transactional
	public void deleteChefById(Long id) {
		Chef c = this.chefRepository.findById(id).get();
		for (Piatto p : c.getPiatti()) {
			this.piattoService.deletePiattoById(p.getId());
		}
		this.chefRepository.deleteById(id);
	}
	
}
