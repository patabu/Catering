package com.docente.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.docente.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {

	@Query(value = "SELECT * FROM Piatto p WHERE p.chef_id = ?1", nativeQuery = true)
	Set<Piatto> findByChefId(Long chef_id);
	
}
