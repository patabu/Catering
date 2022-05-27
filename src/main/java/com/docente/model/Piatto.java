package com.docente.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Column(length = 255)
	@NotBlank
	private String descrizione;
	
	//Molti piatti possono essere presenti in più buffet
	@ManyToMany
	private List<Buffet> buffet;
	
	//Molti piatti possono avere gli stessi ingredienti
	@ManyToMany(mappedBy = "piatti")
	private List<Ingrediente> ingredienti;
	
	public Piatto() {}

	public Piatto(Long id, @NotBlank String nome, @NotBlank String descrizione, List<Buffet> buffet,
			List<Ingrediente> ingredienti) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.buffet = buffet;
		this.ingredienti = ingredienti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Buffet> getBuffet() {
		return buffet;
	}

	public void setBuffet(List<Buffet> buffet) {
		this.buffet = buffet;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	
	
}
