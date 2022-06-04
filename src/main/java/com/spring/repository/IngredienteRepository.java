package com.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

}
