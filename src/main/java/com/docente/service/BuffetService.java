package com.docente.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docente.model.Buffet;
import com.docente.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired private BuffetRepository buffetRepository;
	
	public List<Buffet> getAllBuffets() {
		List<Buffet> buffets = new ArrayList<Buffet>();
		for (Buffet buffet : buffetRepository.findAll()) {
			buffets.add(buffet);
		}
		return buffets;
	}
	
	public void saveBuffet(Buffet buffet) {
		buffetRepository.save(buffet);
	}
	
}
