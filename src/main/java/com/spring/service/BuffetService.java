package com.spring.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Buffet;
import com.spring.model.Piatto;
import com.spring.repository.BuffetRepository;
import com.spring.repository.PiattoRepository;

@Service
public class BuffetService {

	@Autowired private BuffetRepository buffetRepository;
	@Autowired private PiattoRepository piattoRepository;
	
	public Set<Buffet> getAllBuffets() {
		Set<Buffet> buffets = new HashSet<Buffet>();
		for (Buffet buffet : buffetRepository.findAll()) {
			buffets.add(buffet);
		}
		return buffets;
	}	

	public Set<Buffet> getAllBuffetsByChefId(Long chefId) {
		return this.buffetRepository.findBuffetsByChefId(chefId);
	}

	public Buffet getBuffetById(Long id) {
		return this.buffetRepository.findById(id).get();
	}

	@Transactional
	public void deleteBuffetById(Long id) {
		Buffet b = this.buffetRepository.findById(id).get();
		for (Piatto p : this.piattoRepository.findAll()) {
			if (b.getPiatti().contains(p)) {
				p.removeBuffet(b);
				this.piattoRepository.save(p);
			}
		}
		this.buffetRepository.deleteById(id);
	}

	@Transactional
	public Buffet saveBuffet(Buffet buffet) {
		return this.buffetRepository.save(buffet);
	}
}
