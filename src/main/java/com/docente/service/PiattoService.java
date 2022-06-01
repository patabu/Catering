package com.docente.service;

import java.util.HashSet;
import java.util.Set;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docente.dto.PiattiSelectorDTO;
import com.docente.dto.PiattoDTO;
import com.docente.model.Buffet;
import com.docente.model.Chef;
import com.docente.model.Piatto;
import com.docente.repository.BuffetRepository;
import com.docente.repository.ChefRepository;
import com.docente.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired private BuffetService buffetService;
	@Autowired private PiattoRepository piattoRepository;
	@Autowired private ChefRepository chefRepository;
	@Autowired private BuffetRepository buffetRepository;
	
	public Set<Piatto> getAllPiatti() {
		Set<Piatto> piatti = new HashSet<Piatto>();
		for (Piatto piatto : piattoRepository.findAll()) {
			piatti.add(piatto);
		}
		return piatti;
	}
	
	public Set<Piatto> getAllPiattiByChefId(Long chef_id) {
		return this.piattoRepository.findByChefId(chef_id);
	}
	
	public Piatto getPiattoById(Long id) {
		Optional<Piatto> piatto = this.piattoRepository.findById(id);
		if (piatto.isPresent()) return piatto.get();
		return null;
	}
	
	@Transactional
	public Piatto savePiatto(PiattoDTO piattoDTO) {
		
		// Controllo che lo Chef inserito esista
		Optional<Chef> chefOpt = this.chefRepository.findById(piattoDTO.getChefId());
		if (chefOpt.isPresent()) {
			Piatto piatto = new Piatto();
			Chef chef = chefOpt.get();
			
			// Dal DTO creo l'oggetto Piatto e lo salvo
			piatto.setNome(piattoDTO.getNome());
			piatto.setDescrizione(piattoDTO.getDescrizione());
			piatto.setChef(chef);
			this.piattoRepository.save(piatto);
			
			// Dal DTO aggiungo il Piatto appena creato all'oggetto Chef salvandolo
			Set<Piatto> piatti = chef.getPiatti();
			piatti.add(piatto);
			chef.setPiatti(piatti);
			this.chefRepository.save(chef);
			
			return piatto;
		}
		/* 
		 * Lo Chef per qualche motivo non è stato inserito correttamente
		 * => non salvo il Piatto
		*/
		return null;
	}
	
	@Transactional
	public Piatto savePiatto(Piatto piatto) {
		return this.piattoRepository.save(piatto);
	}
	
	public void deletePiattoById(Long id) {
		this.piattoRepository.deleteById(id);
	}
	
	@Transactional
	public Buffet addPiattiToBuffet(PiattiSelectorDTO dto) {
		// Retrieve del buffet
		Buffet buffet = this.buffetService.getBuffetById(dto.getBuffetId());
		// Per ogni piatto dello chef (Piatto piatto: piatti)
		for (Piatto piatto : dto.getPiatti()) {
			// Controllo se il piatto è stato inserito (piattiSelected.contains(piatto))
			if (dto.getPiattiSelected().contains(piatto)) {
				// Controllo sul piatto se tra i buffets, il buffet in questione non era già stato inserito (if !piatto.buffets.contains(buffet))
				if (!piatto.getBuffets().contains(buffet)) {
					// Inserisco il buffet in piatto.buffets e salvo (piatto.buffets.add(buffet))
					piatto.addBuffet(buffet);
					this.piattoRepository.save(piatto);
					// Inserisco il piatto in buffet.piatti e salvo (buffet.piatti.add(piatto))
					buffet.addPiatto(piatto);
					this.buffetRepository.save(buffet);
				}
				// Se invece era già stato inserito => tutto okay
			} else { // Se non è stato inserito (è stato deselezionato)
				// Controllo sul piatto se tra i buffets, il buffet in questione era già stato inserito (if piatto.buffets.contains(buffet))
				if (piatto.getBuffets().contains(buffet)) {
					/*
					 * Nel caso in cui il piatto era stato già inserito, e lo deseleziono tra le checkbox, devo fare in modo di rimuovere la reference dalla lista
					 */
					// Rimuovo il buffet in piatto.buffets (piatto.buffets.remove(buffet))
					piatto.removeBuffet(buffet);
					this.piattoRepository.save(piatto);
					// Rimuovo il piatto in buffet.piatti (buffet.piatti.remove(piatto))
					buffet.removePiatto(piatto);
					this.buffetRepository.save(buffet);
				}
				// Se invece non era stato inserito => tutto okay
			}
		}
		return buffet;
	}
}
