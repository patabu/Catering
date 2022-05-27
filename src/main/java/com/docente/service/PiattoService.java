package com.docente.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docente.model.Piatto;
import com.docente.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired private PiattoRepository piattoRepository;
	
	public List<Piatto> getAllPiatti() {
		List<Piatto> piatti = new ArrayList<Piatto>();
		for (Piatto piatto : piattoRepository.findAll()) {
			piatti.add(piatto);
		}
		return piatti;
	}
	
	public void savePiatto(Piatto piatto) {
		piattoRepository.save(piatto);
	}
	
}
