package com.docente.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docente.model.Ingrediente;
import com.docente.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired private IngredienteRepository ingredienteRepository;
	
	public Set<Ingrediente> getAllIngredienti() {
		Set<Ingrediente> ingredienti = new HashSet<Ingrediente>();
		for (Ingrediente ingrediente : ingredienteRepository.findAll()) {
			ingredienti.add(ingrediente);
		}
		return ingredienti;
	}
	
	public Ingrediente saveIngrediente(Ingrediente ingrediente) {
		return this.ingredienteRepository.save(ingrediente);
	}

	public Ingrediente getIngredienteById(Long id) {
		Optional<Ingrediente> ingredienteOpt = this.ingredienteRepository.findById(id);
		if (ingredienteOpt.isPresent()) {
			return ingredienteOpt.get();
		}
		return null;
	}

	public void deleteIngrediente(Long id) {
		this.ingredienteRepository.deleteById(id);
	}
	
}
