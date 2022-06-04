package com.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

}
