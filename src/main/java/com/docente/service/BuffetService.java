package com.docente.service;

import java.util.HashSet;
import java.util.Set;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docente.dto.BuffetDTO;
import com.docente.model.Buffet;
import com.docente.model.Chef; 
import com.docente.repository.BuffetRepository;
import com.docente.repository.ChefRepository;

@Service
public class BuffetService {

	@Autowired private BuffetRepository buffetRepository;
	@Autowired private ChefRepository chefRepository;
	
	public Set<Buffet> getAllBuffets() {
		Set<Buffet> buffets = new HashSet<Buffet>();
		for (Buffet buffet : buffetRepository.findAll()) {
			buffets.add(buffet);
		}
		return buffets;
	}
	
	public Buffet saveBuffet(BuffetDTO buffetDTO) {
		
		// Controllo che lo Chef inserito esista
		Optional<Chef> chefOpt = this.chefRepository.findById(buffetDTO.getChefId());
		if (chefOpt.isPresent()) {
			Buffet buffet = new Buffet();
			Chef chef = chefOpt.get();
			
			// Dal DTO creo l'oggetto Buffet e lo salvo
			buffet.setNome(buffetDTO.getNome());
			buffet.setDescrizione(buffetDTO.getDescrizione());
			buffet.setChef(chef);
			this.buffetRepository.save(buffet);
			
			// Dal DTO aggiungo il Buffet appena creato all'oggetto Chef salvandolo
			Set<Buffet> buffets = chef.getBuffets();
			buffets.add(buffet);
			chef.setBuffets(buffets);
			this.chefRepository.save(chef);
			
			return buffet;
		}
		/* 
		 * Lo Chef per qualche motivo non è stato inserito correttamente
		 * => non salvo il Buffet
		*/
		return null;
	}
	

	public Set<Buffet> getAllBuffetsByChefId(Long chefId) {
		return this.buffetRepository.findBuffetsByChefId(chefId);
	}

	public Buffet getBuffetById(Long id) {
		Optional<Buffet> buffetOpt = this.buffetRepository.findById(id);
		if (buffetOpt.isPresent()) return buffetOpt.get();
		return null;
	}

	public void deleteBuffetById(Long id) {
		this.buffetRepository.deleteById(id);
	}

	@Transactional
	public Buffet saveBuffet(Buffet buffet) {
		return this.buffetRepository.save(buffet);
	}
}
