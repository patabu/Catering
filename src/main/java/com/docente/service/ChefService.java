package com.docente.service;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docente.model.Chef;
import com.docente.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired private ChefRepository chefRepository;

	public Set<Chef> getAllChefs() {
		Set<Chef> chefs = new HashSet<Chef>();
		for (Chef chef : chefRepository.findAll()) {
			chefs.add(chef);
		}
		return chefs;
	}
	
	public Chef getById(Long id) {
		Optional<Chef> chef = this.chefRepository.findById(id);
		if (chef.isPresent())
			return chef.get();
		return null;
	}
		
	@Transactional
	public Chef saveChef(Chef chef) {
		return this.chefRepository.save(chef);
	}
	
	public void deleteChefById(Long id) {
		this.chefRepository.deleteById(id);
	}
	
	public boolean alreadyExists(Chef chef) {
		String nome = chef.getNome();
		String cognome = chef.getCognome();
		for (Chef c : this.chefRepository.findAll()) {
			if (c.getNome().equals(nome) && c.getCognome().equals(cognome))
				return true;
		}
		return false;
	}
	
}
