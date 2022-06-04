package com.spring.service;

import java.util.Set;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Chef;
import com.spring.repository.ChefRepository;

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
	
	public Chef getChefById(Long id) {
		return this.chefRepository.findById(id).get();
	}
		
	@Transactional
	public Chef saveChef(Chef chef) {
		return this.chefRepository.save(chef);
	}
	
	public void deleteChefById(Long id) {
		this.chefRepository.deleteById(id);
	}
	
}
