package com.docente.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docente.model.Chef;
import com.docente.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired private ChefRepository chefRepository;

	public List<Chef> getAllChefs() {
		List<Chef> chefs = new ArrayList<Chef>();
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
	public void saveChef(Chef chef) {
		System.out.println(chef.getId());
		if (chef.getId() == null) this.chefRepository.save(chef);
		else {
			Optional<Chef> chefOpt = this.chefRepository.findById(chef.getId());
			if (chefOpt.isPresent()) {
				Chef chefToUpdate = chefOpt.get();
				chefToUpdate.setNome(chef.getNome());
				chefToUpdate.setCognome(chef.getCognome());
				chefToUpdate.setNazionalita(chef.getNazionalita());
				this.chefRepository.save(chefToUpdate);
			}
		}
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
