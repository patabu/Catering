package com.docente.dto;

import java.util.HashMap;
import java.util.Set;

import com.docente.model.Buffet;
import com.docente.model.Chef;
import com.docente.model.Piatto;

public class BuffetDTO {

	private String nome;
	private String descrizione;
	private Long chefId;
	private HashMap<String, String> chefs;
	
	public BuffetDTO() { 
		
	}
	
	public BuffetDTO(Buffet buffet, Set<Chef> chefs) {
		this.nome = buffet.getNome();
		this.descrizione = buffet.getDescrizione();
		this.chefId = buffet.getChef().getId();
		this.setChefsFromSet(chefs);
	}

	public BuffetDTO(String nome, String descrizione, Long chefId, HashMap<String, String> chefs) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.chefId = chefId;
		this.chefs = chefs;
	}

	public BuffetDTO(Set<Chef> allChefs) {
		this.setChefsFromSet(allChefs);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getChefId() {
		return chefId;
	}

	public void setChefId(Long chefId) {
		this.chefId = chefId;
	}

	public HashMap<String, String> getChefs() {
		return chefs;
	}

	public void setChefs(HashMap<String, String> chefs) {
		this.chefs = chefs;
	}
	
	private void setChefsFromSet(Set<Chef> allChefs) {
		this.chefs = new HashMap<String, String>();
		for (Chef c : allChefs) {
			chefs.put(c.getId().toString(), c.getNome() + " " + c.getCognome());
		}
	}

	@Override
	public String toString() {
		return "BuffetDTO [nome=" + nome + ", descrizione=" + descrizione + ", chefId=" + chefId + ", chefs=" + chefs
				+ "]";
	}
	
	
	
}
