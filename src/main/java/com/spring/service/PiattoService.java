package com.spring.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.PiattiSelectorDTO;
import com.spring.model.Buffet;
import com.spring.model.Piatto;
import com.spring.repository.BuffetRepository;
import com.spring.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired private BuffetService buffetService;
	@Autowired private PiattoRepository piattoRepository;
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
		return this.piattoRepository.findById(id).get();
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
