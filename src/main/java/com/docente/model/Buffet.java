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
public class Buffet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Column(length = 255)
	@NotBlank
	private String descrizione;
	
	//Molti buffet fanno parte di uno chef
	@ManyToOne
	private Chef chef;
	
	//Molti buffet possono avere più piatti
	@ManyToMany(mappedBy = "buffet")
	private List<Piatto> piatti;
	
	public Buffet() {}

	public Buffet(Long id, @NotBlank String nome, @NotBlank String descrizione, Chef chef, List<Piatto> piatti) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.chef = chef;
		this.piatti = piatti;
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

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}
	
}
