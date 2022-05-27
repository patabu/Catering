package com.docente.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docente.model.Ingrediente;
import com.docente.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired private IngredienteRepository ingredienteRepository;
	
	public List<Ingrediente> getAllIngredienti() {
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for (Ingrediente ingrediente : ingredienteRepository.findAll()) {
			ingredienti.add(ingrediente);
		}
		return ingredienti;
	}
	
	public void saveIngrediente(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}
	
}
